package dstudio.com.healthway.ui.camera;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import dstudio.com.healthway.R;
import dstudio.com.healthway.util.PermissionsDelegate;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.FotoapparatSwitcher;
import io.fotoapparat.error.CameraErrorCallback;
import io.fotoapparat.hardware.CameraException;
import io.fotoapparat.hardware.provider.CameraProviders;
import io.fotoapparat.parameter.LensPosition;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.parameter.update.UpdateRequest;
import io.fotoapparat.photo.BitmapPhoto;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;
import io.fotoapparat.result.PendingResult;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.view.CameraView;

import static io.fotoapparat.log.Loggers.fileLogger;
import static io.fotoapparat.log.Loggers.logcat;
import static io.fotoapparat.log.Loggers.loggers;
import static io.fotoapparat.parameter.selector.AspectRatioSelectors.standardRatio;
import static io.fotoapparat.parameter.selector.FlashSelectors.autoFlash;
import static io.fotoapparat.parameter.selector.FlashSelectors.autoRedEye;
import static io.fotoapparat.parameter.selector.FlashSelectors.off;
import static io.fotoapparat.parameter.selector.FlashSelectors.torch;
import static io.fotoapparat.parameter.selector.FocusModeSelectors.autoFocus;
import static io.fotoapparat.parameter.selector.FocusModeSelectors.continuousFocus;
import static io.fotoapparat.parameter.selector.FocusModeSelectors.fixed;
import static io.fotoapparat.parameter.selector.LensPositionSelectors.lensPosition;
import static io.fotoapparat.parameter.selector.PreviewFpsRangeSelectors.rangeWithHighestFps;
import static io.fotoapparat.parameter.selector.Selectors.firstAvailable;
import static io.fotoapparat.parameter.selector.SensorSensitivitySelectors.highestSensorSensitivity;
import static io.fotoapparat.parameter.selector.SizeSelectors.biggestSize;
import static io.fotoapparat.result.transformer.SizeTransformers.scaled;

/**
 * Created by janwelcris on 12/8/2017.
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.camera_view)
    CameraView cameraView;

    @BindView(R.id.zoomSeekBar)
    SeekBar seekBar;

    @BindView(R.id.torchSwitch)
    SwitchCompat torchSwitch;

    @BindView(R.id.switchCamera)
    View buttonSwitchCamera;

    @BindView(R.id.button_capture)
    Button buttonCapture;

    @BindView(R.id.no_permission)
    TextView textNoPermission;

    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);
    private boolean hasCameraPermission;

    private FotoapparatSwitcher fotoapparatSwitcher;
    private Fotoapparat frontFotoapparat;
    private Fotoapparat backFotoapparat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);



        hasCameraPermission = permissionsDelegate.hasCameraPermission();

        if (hasCameraPermission) {
            cameraView.setVisibility(View.VISIBLE);
        } else {
            cameraView.setVisibility(View.GONE);
            permissionsDelegate.requestCameraPermission();
        }
        setupFotoapparat();
        focusOnLongClick();
        toggleTorchOnSwitch();
        zoomSeekBar();

        buttonSwitchCamera.setVisibility(canSwitchCameras() ? View.VISIBLE : View.GONE);
        buttonSwitchCamera.setOnClickListener(this);

        buttonCapture.setOnClickListener(this);


    }

    private void setupFotoapparat() {
        frontFotoapparat = createFotoapparat(LensPosition.FRONT);
        backFotoapparat = createFotoapparat(LensPosition.BACK);
        fotoapparatSwitcher = FotoapparatSwitcher.withDefault(backFotoapparat);
    }

    private void zoomSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fotoapparatSwitcher
                        .getCurrentFotoapparat()
                        .setZoom(progress / (float) seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void toggleTorchOnSwitch() {
        torchSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fotoapparatSwitcher.getCurrentFotoapparat()
                        .updateParameters(
                                UpdateRequest.builder()
                                        .flash(isChecked ? torch() : off())
                                        .build()
                        );
            }
        });
    }


    private void focusOnLongClick() {
        cameraView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fotoapparatSwitcher.getCurrentFotoapparat().autoFocus();

                return true;
            }
        });
    }


    private boolean canSwitchCameras() {
        return frontFotoapparat.isAvailable() == backFotoapparat.isAvailable();
    }

    private Fotoapparat createFotoapparat(LensPosition position) {
        return Fotoapparat
                .with(this)
                .cameraProvider(CameraProviders.v2(CameraActivity.this))
                .into(cameraView)
                .previewScaleType(ScaleType.CENTER_CROP)
                .photoSize(standardRatio(biggestSize()))
                .lensPosition(lensPosition(position))
                .focusMode(firstAvailable(
                        continuousFocus(),
                        autoFocus(),
                        fixed()
                ))
                .flash(firstAvailable(
                        autoRedEye(),
                        autoFlash(),
                        torch(),
                        off()
                ))
                .previewFpsRange(rangeWithHighestFps())
                .sensorSensitivity(highestSensorSensitivity())
                .frameProcessor(new SampleFrameProcessor())
                .logger(loggers(
                        logcat(),
                        fileLogger(this)
                ))
                .cameraErrorCallback(new CameraErrorCallback() {
                    @Override
                    public void onError(CameraException e) {
                        Toast.makeText(CameraActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .build();
    }

    private void takePicture() {
        PhotoResult photoResult = fotoapparatSwitcher.getCurrentFotoapparat().takePicture();

        photoResult.toBitmap(scaled(0.25f))
                .whenAvailable(new PendingResult.Callback<BitmapPhoto>() {
                    @Override
                    public void onResult(BitmapPhoto result) {
                        ByteArrayOutputStream stream = new  ByteArrayOutputStream();
                        result.bitmap.compress(Bitmap.CompressFormat.PNG,100, stream );
                        byte [] b = stream.toByteArray();
                        String temp = Base64.encodeToString(b, Base64.DEFAULT);
                        Toast.makeText(CameraActivity.this, ""+ temp, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void switchCamera() {
        if (fotoapparatSwitcher.getCurrentFotoapparat() == frontFotoapparat) {
            fotoapparatSwitcher.switchTo(backFotoapparat);
        } else {
            fotoapparatSwitcher.switchTo(frontFotoapparat);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (hasCameraPermission) {
            fotoapparatSwitcher.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (hasCameraPermission) {
            fotoapparatSwitcher.stop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            fotoapparatSwitcher.start();
            cameraView.setVisibility(View.VISIBLE);
            enableViews(true);
        }
        else{
            cameraView.setVisibility(View.GONE);
            textNoPermission.setVisibility(View.VISIBLE);
            enableViews(false);
            permissionsDelegate.requestCameraPermission();
        }
    }

    private void enableViews(boolean isBoolean){
        torchSwitch.setEnabled(isBoolean);
        buttonCapture.setEnabled(isBoolean);
        buttonSwitchCamera.setEnabled(isBoolean);
        seekBar.setEnabled(isBoolean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switchCamera:
                switchCamera();
                break;
            case R.id.button_capture:
                takePicture();
                break;
        }
    }

    private class SampleFrameProcessor implements FrameProcessor {

        @Override
        public void processFrame(Frame frame) {
        }

    }

}
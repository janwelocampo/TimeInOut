package dstudio.com.healthway.dagger;

import android.app.Application;
import android.content.Context;

/**
 * Created by janwelcris on 10/19/2017.
 */

public class AndroidMVPDaggerApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        AndroidMVPDaggerApplication app = (AndroidMVPDaggerApplication) context.getApplicationContext();
        if (app.component == null) {
            app.component = app.createComponent();
        }
        return app.component;
    }
}
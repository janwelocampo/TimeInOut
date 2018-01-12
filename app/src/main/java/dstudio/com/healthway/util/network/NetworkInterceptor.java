package dstudio.com.healthway.util.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by janwelcris on 10/19/2017.
 */

public class NetworkInterceptor implements Interceptor {
    private Context mContext;

    public NetworkInterceptor(Context context) {
        mContext = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkMonitor.isNetworkAvailable(mContext)) {
            throw new NoNetworkException();
        }

        return chain.proceed(chain.request());
    }
}
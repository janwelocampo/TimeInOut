package dstudio.com.healthway.dagger;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dstudio.com.healthway.retrofit.APIInterface;
import dstudio.com.healthway.util.AppUtils;
import dstudio.com.healthway.util.network.NetworkInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by janwelcris on 10/19/2017.
 */
@Module
public class APIModule implements AppUtils {

    private static final String NAME_BASE_URL = BASE_URL;

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }


    @Provides
    OkHttpClient provideOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .addInterceptor(new NetworkInterceptor(context)).build();
    }


    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideCallAdapterFactory(){return RxJavaCallAdapterFactory.create();}


    @Provides
    @Singleton
    Retrofit provideRetrofit( Converter.Factory converter, RxJavaCallAdapterFactory factory, @Named(NAME_BASE_URL) String baseUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(factory)
                .build();
    }

    @Provides
    @Singleton
    APIInterface provideRetrofitAPI(Retrofit retrofit) {
        return retrofit.create(APIInterface.class);
    }

}

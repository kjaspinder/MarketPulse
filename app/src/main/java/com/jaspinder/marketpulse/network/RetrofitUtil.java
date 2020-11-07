package com.jaspinder.marketpulse.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jaspinder.marketpulse.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static Api api;

    public static Api getInstance() {
        if (api == null) {
            api = getApiWithTimeout(false);
        }

        return api;
    }

    private static Api getApiWithTimeout(boolean isShortTimeout) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(isShortTimeout ? 30 : 1, isShortTimeout ? TimeUnit.SECONDS : TimeUnit.MINUTES)
                .readTimeout(isShortTimeout ? 30 : 10, isShortTimeout ? TimeUnit.SECONDS : TimeUnit.MINUTES)
                .writeTimeout(isShortTimeout ? 30 : 5, isShortTimeout ? TimeUnit.SECONDS : TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create())
                )
                .client(builder.build())
                .build();

        return retrofit.create(Api.class);
    }
}

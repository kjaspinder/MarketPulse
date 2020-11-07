package com.jaspinder.marketpulse.network;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;



import com.jaspinder.marketpulse.utils.Constants;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    private String error = Constants.ERROR;

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            onSuccessData(response.body());
        } else {
            try {
                if (response.errorBody() != null) {
                    error = response.errorBody().string();
                }
            } catch (Exception e) {
                Log.e(Constants.LOG, e.getMessage());
            }

            if (TextUtils.isEmpty(error)) {
                error = Constants.ERROR;
            }

            if (response.code() == HttpURLConnection.HTTP_CONFLICT
                    && response.raw().request().url().toString().contains(Api.MESSAGES_NEW_ENDPOINT_NAME)) {
                onFailure(error);
            } else {
                onFailure(error);
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        error = t.getMessage();

        if (TextUtils.isEmpty(error)) {
            error = Constants.ERROR;
        }

        onFailure(error);
    }

    public abstract void onSuccessData(T responseData);

    public abstract void onFailure(String error);

}

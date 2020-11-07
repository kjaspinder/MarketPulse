package com.jaspinder.marketpulse.network;

import com.jaspinder.marketpulse.data.ScanData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String MESSAGES_NEW_ENDPOINT_NAME = "messages_requiring_action";

    @GET("data")
    Call<List<ScanData>> getScanData();

}

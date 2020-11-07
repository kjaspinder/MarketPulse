package com.jaspinder.marketpulse.network;

import android.util.Log;

import com.jaspinder.marketpulse.data.ScanData;
import com.jaspinder.marketpulse.events.DataError;
import com.jaspinder.marketpulse.events.ScanDataEvent;
import com.jaspinder.marketpulse.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class GetScanNetworkData {

    public static void  getScanNetworkData(){
        RetrofitUtil.getInstance().getScanData().enqueue(new ApiCallback<List<ScanData>>() {
            @Override
            public void onSuccessData(List<ScanData> responseData) {
                Log.d(Constants.LOG, responseData.toString());
                EventBus.getDefault().post(new ScanDataEvent(responseData));
            }

            @Override
            public void onFailure(String error) {
                Log.d(Constants.LOG, error);
                EventBus.getDefault().post(new DataError());
            }
        });
    }
}

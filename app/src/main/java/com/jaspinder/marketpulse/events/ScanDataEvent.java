package com.jaspinder.marketpulse.events;

import com.jaspinder.marketpulse.data.ScanData;

import java.util.List;

public class ScanDataEvent {

    private List<ScanData> data;

    public ScanDataEvent(List<ScanData> data) {
        this.data = data;
    }

    public List<ScanData> getData() {
        return data;
    }

    public void setData(List<ScanData> data) {
        this.data = data;
    }
}

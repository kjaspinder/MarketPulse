package com.jaspinder.marketpulse.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.jaspinder.marketpulse.R;
import com.jaspinder.marketpulse.events.DataError;
import com.jaspinder.marketpulse.events.ScanDataEvent;
import com.jaspinder.marketpulse.network.GetScanNetworkData;
import com.jaspinder.marketpulse.ui.adapter.ScanListAdapter;
import com.jaspinder.marketpulse.utils.Helper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.no_network)
    TextView no_network;

    ScanListAdapter scanListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(!Helper.isNetworkConnected(this)){
            recyclerView.setVisibility(View.GONE);
            no_network.setVisibility(View.VISIBLE);
            return;
        }

        EventBus.getDefault().register(this);
        GetScanNetworkData.getScanNetworkData();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        scanListAdapter = new ScanListAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(scanListAdapter);
        recyclerView.setHasFixedSize(true);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataFetched(ScanDataEvent event){
        if(scanListAdapter != null){
            scanListAdapter.setData(event.getData());
            scanListAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataFetched(DataError event){
        recyclerView.setVisibility(View.GONE);
        no_network.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

package com.jaspinder.marketpulse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaspinder.marketpulse.R;
import com.jaspinder.marketpulse.data.ScanData;
import com.jaspinder.marketpulse.ui.adapter.DetailListAdapter;
import com.jaspinder.marketpulse.ui.adapter.ScanListAdapter;
import com.jaspinder.marketpulse.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanDetailActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_scan_item)
    RecyclerView recyclerView;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.tag)
    TextView tag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);
        ButterKnife.bind(this);

        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        ScanData data = (ScanData) bundle.getSerializable(Constants.scan_details);
        if(data == null){
            return;
        }
        name.setText(data.getName());
        tag.setText(data.getTag());
        if(data.getColor().equals("red")){
            tag.setTextColor(getResources().getColor(R.color.red));
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        DetailListAdapter detailListAdapter = new DetailListAdapter(this, data.getCriteria());
        recyclerView.setAdapter(detailListAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

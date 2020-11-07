package com.jaspinder.marketpulse.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaspinder.marketpulse.R;
import com.jaspinder.marketpulse.data.ScanData;
import com.jaspinder.marketpulse.ui.ScanDetailActivity;
import com.jaspinder.marketpulse.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ScanListAdapter extends RecyclerView.Adapter<ScanListAdapter.ViewHolder> {
    private Context c;
    private List<ScanData> data;

    public ScanListAdapter(Context c, List<ScanData> items){
        this.c = c;
        this.data = new ArrayList<>();
        data.addAll(items);

    }

    public void setData(List<ScanData> items){
        this.data.clear();
        this.data = new ArrayList<>();
        data.addAll(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.scan_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(data == null || data.size() == 0){
            return;
        }

        ScanData datap = data.get(position);

        holder.name.setText(datap.getName());
        holder.tag.setText(datap.getTag());
        if(datap.getColor().equals("red")){
            holder.tag.setTextColor(c.getResources().getColor(R.color.red));
        }

    }

    @Override
    public int getItemCount() {
        if(data == null) return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView tag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tag = itemView.findViewById(R.id.tag);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Log.d(Constants.LOG, ""+getAdapterPosition());
            Intent i = new Intent(c, ScanDetailActivity.class);
            Bundle b = new Bundle();
            b.putSerializable(Constants.scan_details,data.get(getAdapterPosition()));
            i.putExtras(b);
            c.startActivity(i);
        }
    }
}

package com.jaspinder.marketpulse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaspinder.marketpulse.R;
import com.jaspinder.marketpulse.data.Criteria;
import com.jaspinder.marketpulse.data.ScanData;

import java.util.ArrayList;
import java.util.List;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.ViewHolder> {

    private Context c;
    private List<Criteria> data;

    public DetailListAdapter(Context c, List<Criteria> items){
        this.c = c;
        this.data = new ArrayList<>();
        data.addAll(items);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.detail_list_item, parent, false);
        return new DetailListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(data == null || data.size() == 0){
            return;
        }

        Criteria datap = data.get(position);

        holder.detail.setText(datap.getText());
    }

    @Override
    public int getItemCount() {
        if(data == null) return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}

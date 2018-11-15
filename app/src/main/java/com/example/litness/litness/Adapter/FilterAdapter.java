package com.example.litness.litness.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.litness.litness.R;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder>{

    public List<String> filterList;

    public FilterAdapter(){
        filterList = new ArrayList<>();
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filters, parent, false);
        return new FilterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        holder.tv.setText(filterList.get(position));
        holder.fl.setOnClickListener(v->
            System.out.println(filterList.get(holder.getAdapterPosition())));
        }

    @Override
    public int getItemCount() {
        return filterList.size();
    }
    class FilterViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fl;
        TextView tv;

        public FilterViewHolder(View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.adapter_container);
            tv = itemView.findViewById(R.id.adapter_alt_filter);
        }
    }
}

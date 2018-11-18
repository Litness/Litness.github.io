package com.example.litness.litness.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.litness.litness.Bar.Day;
import com.example.litness.litness.R;

import java.util.ArrayList;
import java.util.List;

public class DayDialogAdapter extends RecyclerView.Adapter<DayDialogAdapter.CardDialogViewHolder> {

    private Context ctx;
    private List<Day> data;

    public DayDialogAdapter(Context c) {
        ctx = c;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public CardDialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardDialogViewHolder(LayoutInflater.from(ctx).inflate(R.layout.adapter_day_dialog,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardDialogViewHolder holder, int position) {
        Day d = data.get(position);

        holder.tvTitle.setText(d.day);
        for(String s : d.events)
            holder.tvText1.setText(s);
        for(String s : d.specials)
            holder.tvText2.setText(s);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CardDialogViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle,tvText1, tvText2;

        CardDialogViewHolder(View v) {
            super(v);
            tvText1.findViewById(R.id.dialogcard_alt_title);
            tvText1.findViewById(R.id.dialogcard_alt_1);
            tvText2.findViewById(R.id.dialogcard_alt_2);
        }
    }

    public List<Day> getData() {
        return this.data;
    }
}

package com.example.litness.litness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.litness.litness.Bar;
import com.example.litness.litness.BarDisplayActivity;
import com.example.litness.litness.Client;
import com.example.litness.litness.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BarCardAdapter extends RecyclerView.Adapter<BarCardAdapter.BarViewHolder> {

    private Context ctx;
    private List<Bar> data;

    public BarCardAdapter(Context c) {
        ctx = c;
        data = new ArrayList<>();
    }

    public void updateBars(Collection<Bar> c) {
        data = new ArrayList<>(c);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BarViewHolder(LayoutInflater.from(ctx).inflate(R.layout.adapter_bar_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BarViewHolder holder, int position) {
        Bar b = data.get(position);
        System.out.println(b.getBarName());

        holder.textViewBarName.setText(b.getBarName());
        holder.textViewWait.setText(b.getWait());
        holder.textViewCover.setText(b.getCover());
        holder.textViewTime.setText(b.getEvents());

        holder.cardContainer.setOnClickListener(v -> {
            //get the bar clicked on
            Client.activeBar = data.get(holder.getAdapterPosition());
            ctx.startActivity(new Intent(ctx, BarDisplayActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BarViewHolder extends RecyclerView.ViewHolder{

        private CardView cardContainer;
        private TextView textViewBarName, textViewWait, textViewCover,  textViewTime;

        BarViewHolder(View itemView) {
            super(itemView);
            cardContainer = itemView.findViewById(R.id.barcard_cv);
            textViewBarName = itemView.findViewById(R.id.barcard_alt_name);
            textViewWait = itemView.findViewById(R.id.barcard_alt_wait);
            textViewCover = itemView.findViewById(R.id.label_bar_card_cover);
            textViewTime = itemView.findViewById(R.id.barcard_icon);
        }
    }

    public List<Bar> getData() {
        return this.data;
    }
}

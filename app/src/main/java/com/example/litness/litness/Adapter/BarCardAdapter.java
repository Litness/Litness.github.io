package com.example.litness.litness.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.litness.litness.Bar;
import com.example.litness.litness.BarDisplayActivity;
import com.example.litness.litness.Client;
import com.example.litness.litness.R;

import java.util.ArrayList;
import java.util.Calendar;
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

        holder.tagTainer.removeAllViews();
        //get all the events for the day I just set to zero for easy loading
        if(b.days.get(0)/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1*/ != null) {
            for (String cat : b.days.get(0)/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1]*/.events) {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(ctx).inflate(R.layout.adapter_events, null, false);
                ((TextView) v.findViewById(R.id.adapter_alt_event)).setText(cat);
                holder.tagTainer.addView(v);
            }

            //get all the specials for the day
            for (String cat : b.days.get(0)/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1]*/.specials) {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(ctx).inflate(R.layout.adapter_specials, null, false);
                ((TextView) v.findViewById(R.id.adapter_alt_special)).setText(cat);
                holder.tagTainer.addView(v);
            }
        }
        //make the specials invisible if there are none
        else {
            holder.tagTainer.setVisibility(View.GONE);
        }

        holder.tvBarName.setText(b.barName);
        holder.tvWaitTime.setText(b.wait);

        //don't show if if there is no wait
        if(b.wait.equals(""))
            holder.tvWaitTime.setVisibility(View.GONE);

        //don't show under cover if there isn't one
        if(b.coverUnder.length() > 1)
            holder.tvCover.setText(b.coverOver + " | " + b.coverUnder);
        else
            holder.tvCover.setText(b.coverOver);

        switch (b.litness) {
            case "1":
                holder.imgLit.setImageResource(R.drawable.meter_1);
                break;
            case "2":
                holder.imgLit.setImageResource(R.drawable.meter_2);
                break;
            case "3":
                holder.imgLit.setImageResource(R.drawable.meter_3);
                break;
            case "4":
                holder.imgLit.setImageResource(R.drawable.meter_4);
                break;
            default:
                holder.imgLit.setImageResource(R.drawable.meter_5);
                break;
        }

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

        LinearLayout tagTainer;
        private CardView cardContainer;
        private TextView tvBarName, tvWaitTime, tvCover;
        private ImageView imgLit;

        BarViewHolder(View v) {
            super(v);
            tagTainer = v.findViewById(R.id.barcard_container_tags);
            cardContainer = itemView.findViewById(R.id.barcard_cv);
            tvBarName = itemView.findViewById(R.id.barcard_alt_name);
            tvWaitTime = itemView.findViewById(R.id.barcard_alt_wait);
            tvCover = itemView.findViewById(R.id.barcard_alt_cover);
            imgLit = itemView.findViewById(R.id.barcard_img_litness);
        }
    }

    public List<Bar> getData() {
        return this.data;
    }
}

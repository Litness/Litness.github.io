package com.example.litness.litness.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.litness.litness.BarObject;
import com.example.litness.litness.R;

import java.util.List;

public class BarCardAdapter extends RecyclerView.Adapter<BarCardAdapter.MyViewHolder> {

    Context mCtx;
    List<BarObject> mData;

    public BarCardAdapter(Context mCtx, List<BarObject> mData) {
        this.mCtx = mCtx;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mCtx).inflate(R.layout.adapter_bar_card,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);


/*        vHolder.singleReportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx,DisplayBarInfo.class);
                BarObject r = mData.get(vHolder.getAdapterPosition());
                intent.putExtra("bar name", mData.get(vHolder.getAdapterPosition()).getReportId() );
                mCtx.startActivity(intent);
            }
        });*/

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Add "RPT: " in front of report ID
        holder.textViewBarName.setText(mData.get(position).getBarName());
        holder.textViewWait.setText(mData.get(position).getWait());
        holder.textViewCover.setText(mData.get(position).getCover());
        holder.textViewTime.setText(mData.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout singleReportCard;
        private TextView textViewBarName, textViewWait, textViewCover,  textViewTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            singleReportCard = itemView.findViewById(R.id.layout_single_bar_card);
            textViewBarName = itemView.findViewById(R.id.label_bar_card_name);
            textViewWait = itemView.findViewById(R.id.label_bar_card_wait);
            textViewCover = itemView.findViewById(R.id.label_bar_card_cover);
            textViewTime = itemView.findViewById(R.id.label_bar_card_icon);
        }
    }
}

package com.example.ccnu_station.Call;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ccnu_station.R;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.CallViewHolder>{
    private ArrayList<CallItem> itemList;

    public CallAdapter(ArrayList<CallItem> itemList) {
        this.itemList = itemList;
    }
    @Override
    public CallAdapter.CallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_item, parent, false);
        return new CallAdapter.CallViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CallAdapter.CallViewHolder holder, int position) {
        CallItem item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textPlace.setText(item.getPlace());
        holder.textTime.setText(item.getTextTime());
        holder.textReq.setText(item.getReq());
        holder.time.setText(item.getTime());
        Log.i("AVATAR",item.getAvatar());
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatar())
                .circleCrop()
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<CallItem> itemList) {
        this.itemList = itemList;
    }

    public static class CallViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textPlace;
        public TextView textTime;
        public TextView textReq;
        public ImageView avatar;
        public TextView time;
        public CallViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.texttime);
            textTitle = itemView.findViewById(R.id.textCallTitle);
            textPlace = itemView.findViewById(R.id.textCallPlace);
            avatar = itemView.findViewById(R.id.imgavatar);
            textTime = itemView.findViewById(R.id.textCalltime);
            textReq = itemView.findViewById(R.id.textCallReq);
        }
    }
}

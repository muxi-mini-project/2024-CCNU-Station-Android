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
import com.example.ccnu_station.Record.RecordAdapter;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.CallViewHolder>{
    private ArrayList<CallItem> itemList;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onAvatarClick(String personal_id);
    }

    public CallAdapter(ArrayList<CallItem> itemList,OnItemClickListener listener) {
        this.itemList = itemList;
        mListener = listener;
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
        holder.textPlace.setText(item.getWhere());
        holder.textTime.setText(item.getActivityTime());
        holder.textReq.setText(item.getRequest());
        holder.time.setText(item.getPostTime());
        Log.i("AVATAR",item.getHeadimage());
        Glide.with(holder.itemView.getContext())
                .load(item.getHeadimage())
                .circleCrop()
                .into(holder.avatar);
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAvatarClick(itemList.get(holder.getAdapterPosition()).getPosterid());
            }
        });
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

package com.example.ccnu_station.Chat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ccnu_station.Call.CallAdapter;
import com.example.ccnu_station.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private ArrayList<ChatItem> itemList;
    private ChatAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onAvatarClick(String personal_id);
    }

    public ChatAdapter(ArrayList<ChatItem> itemList, ChatAdapter.OnItemClickListener listener) {
        this.itemList = itemList;
        mListener = listener;
    }
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatAdapter.ChatViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ChatAdapter.ChatViewHolder holder, int position) {
//        ChatItem item = itemList.get(position);
//        holder.textTitle.setText(item.getTitle());
//        Glide.with(holder.itemView.getContext())
//                .load(item.getHeadimage())
//                .circleCrop()
//                .into(holder.avatar);
//        holder.avatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onAvatarClick(itemList.get(holder.getAdapterPosition()).getPosterid());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<ChatItem> itemList) {
        this.itemList = itemList;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textPlace;
        public TextView textTime;
        public TextView textReq;
        public ImageView avatar;
        public TextView time;
        public ChatViewHolder(View itemView) {
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

package com.example.ccnu_station.Record;

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
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{
    private ArrayList<Item> itemList;

    public RecordAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new RecordViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textContent.setText(item.getText());
        holder.time.setText(item.getTime());
        if(!item.getAvatar().equals("")&&!item.getAvatar().substring(0,7).equals("http://")) {
            item.setAvatar("http://"+item.getImage1());
        }
        if(!item.getImage1().equals("")&&!item.getImage1().substring(0,7).equals("http://")) {
            item.setImage1("http://"+item.getImage1());
        }
        Log.i("AVATAR",item.getAvatar());
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatar())
                .circleCrop()
                .into(holder.avatar);
        Glide.with(holder.itemView.getContext())
                .load(item.getImage1())
                .into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textContent;
        public ImageView avatar;
        public ImageView picture;
        public TextView time;
        public RecordViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.texttime);
            textTitle = itemView.findViewById(R.id.texttitle);
            textContent = itemView.findViewById(R.id.textcontent);
            avatar = itemView.findViewById(R.id.imgavatar);
            picture = itemView.findViewById(R.id.imgpicture);
        }
    }
}

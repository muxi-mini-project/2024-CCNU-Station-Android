package com.example.ccnu_station.Achievements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccnu_station.R;

import java.util.List;

public class Achievement_Adapter extends RecyclerView.Adapter<Achievement_Adapter.Achievement_ViewHolder> {

    private OnItemClickListener mListener;
    private List<Achievement> data;

    public Achievement_Adapter(List<Achievement> data ,OnItemClickListener listener) {
        mListener =listener;
        this.data = data;
    }

    @NonNull
    @Override
    ///创建ViewHolder
    public Achievement_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item, parent, false);
        return new Achievement_ViewHolder(view);
    }

    //在onBindViewHolder方法中，获取了data 对象，该对象包含有关列表项的信息。
    // 在这个方法内部将这些信息绑定到 ViewHolder 中的视图组件上
    @Override
    public void onBindViewHolder(@NonNull Achievement_ViewHolder holder, int position) {
        Achievement achievement = data.get(position);
        holder.achievement_title.setText(achievement.getTitle());
        holder.achievement_isfinished.setChecked(achievement.isIsfinished());


        holder.achievement_isfinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAchieveClick(data.get(holder.getAdapterPosition()).getAchievementId());
                ////
//                int position = holder.getAdapterPosition();
//                setcheckboxbackgruond(holder,position);
                ////

                //
            }
        });

    }
    public interface OnItemClickListener {
        void onAchieveClick(int achID);
    }

    //////
    private OnItemClickListener listener;

    // 提供一个公共方法来设置监听器
    public void setOnItemCheckChangeListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    /////
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class Achievement_ViewHolder extends RecyclerView.ViewHolder {
        TextView achievement_title;
        CheckBox achievement_isfinished;

        public Achievement_ViewHolder(@NonNull View itemView) {
            super(itemView);
            achievement_title = itemView.findViewById(R.id.title_textview);
            achievement_isfinished = itemView.findViewById(R.id.isfinished_checkbox);
        }
    }

//    private void setcheckboxbackgruond(@NonNull Achievement_ViewHolder holder, int position){
//        if (Listener != null && position != RecyclerView.NO_POSITION) {
//            Achievement clickedItem = data.get(position);
//            Listener.onAchieveClick(clickedItem.getAchievementId());
//            // 切换CheckBox的图片
//            if (clickedItem.isIsfinished()) {
//                holder.achievement_isfinished.setButtonDrawable(R.drawable.achicheckboxfininished);
//            } else {
//                holder.achievement_isfinished.setButtonDrawable(R.drawable.achecheckboxunfinished);
//            }
//        }
//    }

}
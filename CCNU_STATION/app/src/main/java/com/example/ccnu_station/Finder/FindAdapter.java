package com.example.ccnu_station.Finder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.example.ccnu_station.Call.CallAdapter;
import com.example.ccnu_station.R;
import java.util.ArrayList;
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder>{
    private ArrayList<FindItem> itemList;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onAvatarClick(String personal_id);
    }

    public FindAdapter(ArrayList<FindItem> itemList,OnItemClickListener listener) {
        this.itemList = itemList;
        mListener = listener;
    }
    @Override
    public FindAdapter.FindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_item, parent, false);
        return new FindAdapter.FindViewHolder(view);
    }
    @Override
    public void onBindViewHolder(FindAdapter.FindViewHolder holder, int position) {
        FindItem item = itemList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getHeadImage())
                .circleCrop()
                .into(holder.avatar);
        Glide.with(holder.itemView.getContext())
                .load(item.getImage())
                .into(holder.picture);
        holder.time.setText(item.getTime());
        holder.title.setText(item.getTitle());
        holder.clue.setText(item.getClue());
        holder.ddl.setText(item.getDeadline());
        holder.thing.setText(item.getThing());
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAvatarClick(itemList.get(holder.getAdapterPosition()).getPoster());
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<FindItem> itemList) {
        this.itemList = itemList;
    }

    public static class FindViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView title;
        public TextView clue;
        public ImageView avatar;
        public ImageView picture;
        public TextView thing;
        public TextView ddl;
        public FindViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.texttime);
            title = itemView.findViewById(R.id.textFindTitle);
            clue = itemView.findViewById(R.id.textFindClue);
            avatar = itemView.findViewById(R.id.imgavatar);
            picture = itemView.findViewById(R.id.imgpicture);
            thing = itemView.findViewById(R.id.textFindThing);
            ddl = itemView.findViewById(R.id.textFindDDL);
        }
    }
}

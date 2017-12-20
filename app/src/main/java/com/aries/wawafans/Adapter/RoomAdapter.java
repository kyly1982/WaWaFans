package com.aries.wawafans.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.wawafans.Bean.Machine;
import com.aries.wawafans.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by kyly on 2017/12/12.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    public interface OnItemClicedListener {
        void onItemClicked(Machine machine);
    }

    private ArrayList<Machine> rooms = new ArrayList(20);
    private OnItemClicedListener listener;

    public RoomAdapter(ArrayList<Machine> rooms, OnItemClicedListener listener) {
        this.rooms = rooms;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        if (null != view) {
            return new ViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!rooms.isEmpty() && rooms.size() > position) {
            final Machine room = rooms.get(position);
            if (null == holder.cover.getTag() || room.getId() != (int) holder.cover.getTag()) {
                holder.bg.setBackgroundResource(0 == position % 2 ? R.drawable.room_bg_orange : R.drawable.room_bg_pink);
                holder.name.setText(room.getName());
                holder.desc.setText(room.getDesc());
                holder.price.setText(room.getPrice() + "");
                switch (room.getState()) {
                    case 0:
                        //offline
                        holder.status.setText("离线");
                        break;
                    case 1:
                        //ready
                        holder.status.setText("空闲中");
                        break;
                    case 2:
                        //busy
                        holder.status.setText("游戏中");
                        break;
                }
                if (null != room.getCover() && TextUtils.isEmpty(room.getCover()) && room.getCover().length() > 10) {
                    Glide.with(holder.itemView.getContext())
                            .load(room.getCover())
                            .into(holder.cover);
                }
                holder.cover.setTag(room.getId());
                holder.itemView.setTag(room);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != listener) {
                            listener.onItemClicked(room);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc, status, price;
        public ImageView cover, bg;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.subname);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.stare);
            cover = itemView.findViewById(R.id.cover);
            bg = itemView.findViewById(R.id.bg);
        }
    }
}

package com.aries.wawafans.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.wawafans.Bean.Record;
import com.aries.wawafans.R;
import com.aries.wawafans.UTILS.TimeUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by kyly on 2017/12/19.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private ArrayList<Record> records;
    private Context context;

    public void setData(ArrayList<Record> records, boolean isAppend) {
        if (null == records) {
            return;
        }
        if (null == this.records) {
            this.records = new ArrayList<>(records.size());
            this.records.addAll(records);
            notifyDataSetChanged();
        } else {
            if (isAppend) {
                this.records.clear();
                this.records.addAll(records);
                notifyDataSetChanged();
            } else {
                int start = records.size();
                this.records.addAll(records);
                notifyItemRangeInserted(start, records.size());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return null != view ? new ViewHolder(view) : null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (null != records && !records.isEmpty() && position < records.size()) {
            Record record = records.get(position);
            if (null != record && (null == holder.itemCover.getTag() || (int) holder.itemCover.getTag() != record.getId())) {
                Glide.with(context).load(record.getItemCover()).into(holder.itemCover);
                holder.itemName.setText(record.getItemName() + "");
                holder.time.setText(TimeUtil.getFriendTime(record.getTime()));
                holder.itemCover.setTag(record.getId());
            }
        }
    }

    @Override
    public int getItemCount() {
        return null == records ? 0 : records.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemCover;
        private TextView itemName, time;

        public ViewHolder(View itemView) {
            super(itemView);
            itemCover = itemView.findViewById(R.id.itemCover);
            itemName = itemView.findViewById(R.id.itemName);
            time = itemView.findViewById(R.id.time);
        }
    }
}

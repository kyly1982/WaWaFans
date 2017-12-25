package com.aries.wawafans.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.wawafans.Bean.Price;
import com.aries.wawafans.R;

import java.util.ArrayList;

/**
 * Created by kyly on 2017/12/25.
 */

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.ViewHolder> {
    private ArrayList<Price> prices;
    private int selectedIndex = -1;
    private View selectedView = null;
    private OnItemSelectedListener listener = null;

    public interface OnItemSelectedListener {
        void onItemSelected(Price price);
    }


    public PriceAdapter(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public void setData(ArrayList<Price> prices) {
        if (null == this.prices) {
            this.prices = prices;
        } else {
            this.prices.clear();
            this.prices.addAll(prices);
        }
        notifyDataSetChanged();
    }

    public Price getPrice() {
        if (-1 == selectedIndex) {
            return null;
        } else {
            return prices.get(selectedIndex);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Price price = prices.get(position);
        holder.rmb.setText(price.getRmb() + "");
        holder.coin.setText(price.getCoin() + "");
        holder.icon.setBackgroundResource(R.mipmap.ic_star);
//        if (selectedIndex == position){
//            holder.itemView.setBackgroundResource(R.drawable.bg_price_select);
//            selectedView = holder.itemView;
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == selectedView || selectedView.getId() != v.getId()) {
                    holder.itemView.setBackgroundResource(R.drawable.bg_price_select);
                    holder.icon.setBackgroundResource(R.mipmap.ic_star);
                    selectedView = v;
                    selectedIndex = position;
//                    if (null != listener){
//                        listener.onItemSelected(null);
//                    }
                } else {
                    selectedView.setBackgroundResource(R.drawable.bg_price_normal);
                    selectedView = null;
                    selectedIndex = -1;
//                    if (null != listener){
//                        listener.onItemSelected(price);
//                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != prices ? prices.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView coin, rmb;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            coin = itemView.findViewById(R.id.coin);
            rmb = itemView.findViewById(R.id.rmb);
            icon = itemView.findViewById(R.id.icon);
        }
    }

}

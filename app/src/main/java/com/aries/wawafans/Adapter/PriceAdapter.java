package com.aries.wawafans.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        if (position != selectedIndex) {
            holder.item_bg.setBackgroundResource(R.drawable.bg_price_normal);
            holder.icon.setBackgroundResource(R.mipmap.ic_star);
        } else {
            holder.item_bg.setBackgroundResource(R.drawable.bg_price_select);
            holder.icon.setBackgroundResource(R.mipmap.ic_star);
            selectedIndex = -1;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex == position) {
                    selectedIndex = -1;
                } else {
                    selectedIndex = position;
                }
                notifyDataSetChanged();
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
        private View item_bg;

        public ViewHolder(View itemView) {
            super(itemView);
            coin = itemView.findViewById(R.id.coin);
            rmb = itemView.findViewById(R.id.rmb);
            icon = itemView.findViewById(R.id.icon);
            item_bg = itemView.findViewById(R.id.bg_price);
        }
    }

}

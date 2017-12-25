package com.aries.wawafans.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aries.wawafans.Adapter.PriceAdapter;
import com.aries.wawafans.Bean.Price;
import com.aries.wawafans.R;

import java.util.ArrayList;

public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView priceList;
    private TextView coin;

    private ArrayList<Price> prices;
    private PriceAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == priceList) {
            initView();
        }
        showData();
    }

    private void initView() {
        priceList = findViewById(R.id.pricelist);
        coin = findViewById(R.id.coin);
        findViewById(R.id.wxpay).setOnClickListener(this);
        findViewById(R.id.alipay).setOnClickListener(this);


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        priceList.setLayoutManager(manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);
    }

    private void loadData() {
        prices = new ArrayList<>(5);
        Price priceA = new Price(10000, 188);
        Price priceB = new Price(5000, 100);
        Price priceC = new Price(2000, 50);
        Price priceD = new Price(1000, 30);
        Price priceE = new Price(200, 10);
        prices.add(priceA);
        prices.add(priceB);
        prices.add(priceC);
        prices.add(priceD);
        prices.add(priceE);
        showData();
    }

    private void showData() {
        if (null == prices) {
            loadData();
        } else {
            if (null == adapter) {
                adapter = new PriceAdapter(prices);
                priceList.setAdapter(adapter);
            } else {
                adapter.setData(prices);
            }
        }
    }

    //todo 支付，1为支付宝，0为微信
    private void pay(int type, Price price) {

    }

    @Override
    public void onClick(View v) {
        Price price = adapter.getPrice();
        if (null != price) {
            switch (v.getId()) {
                case R.id.wxpay:
                    pay(0, price);
                    break;
                case R.id.alipay:
                    pay(1, price);
                    break;
            }
        } else {
            showMessage("未选择充值金额！");
        }
    }
}

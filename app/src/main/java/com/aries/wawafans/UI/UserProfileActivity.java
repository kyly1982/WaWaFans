package com.aries.wawafans.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.wawafans.Adapter.RecordAdapter;
import com.aries.wawafans.Bean.Bill;
import com.aries.wawafans.Bean.Record;
import com.aries.wawafans.Bean.User;
import com.aries.wawafans.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserProfileActivity extends BaseActivity implements View.OnClickListener {
    private ImageView cover;
    private TextView name, winCount, coin;
    private RecyclerView recordList;

    private RecordAdapter adapter;
    private Bill bill;
    private ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == recordList) {
            initView();
        }
        loadData();
//        showData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange:
                Intent intent = new Intent(this, ExchangeActivity.class);
                startActivity(intent);
                break;
            case R.id.recharge:
                Intent intent1 = new Intent(this, RechargeActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);

        cover = findViewById(R.id.cover);
        name = findViewById(R.id.name);
        winCount = findViewById(R.id.winCount);
        coin = findViewById(R.id.coin);
        recordList = findViewById(R.id.recordList);

        findViewById(R.id.exchange).setOnClickListener(this);
        findViewById(R.id.recharge).setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recordList.setLayoutManager(manager);
    }

    private void showData() {
        if (null == records) {
            loadData();
            return;
        }
        if (null == adapter) {
            adapter = new RecordAdapter();
            recordList.setAdapter(adapter);
        }
        adapter.setData(records, false);

        if (null == cover.getTag()) {
            User user = application.getUser();
            if (null != user) {
                Glide.with(this)
                        .load(user.getPortrait())
                        .into(cover);
                name.setText(user.getNick());
                cover.setTag(user);
            }
        }
        if (null != bill) {
            winCount.setText(bill.getWinCount() + "");
            coin.setText(bill.getAvailable() + "");
        }
    }

    private void loadData() {
        if (null == bill) {
            bill = new Bill(320, 150, 170, 0, 3, 3, 50, 6, 3, 3);
            records = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                Record record = new Record(i, (i + 7) % 2, System.currentTimeMillis() - (60000 * i), "第" + i + "个物品", "http://www.qqzhi.com/uploadpic/2015-01-22/022222987.jpg");
                records.add(record);
            }

        }
        showData();
    }
}

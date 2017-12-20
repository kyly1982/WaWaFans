package com.aries.wawafans.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.wawafans.Adapter.RecordAdapter;
import com.aries.wawafans.Bean.Record;
import com.aries.wawafans.R;

import java.util.ArrayList;

public class UserActivity extends BaseActivity {
    private ImageView cover;
    private TextView name, winCount, coin;
    private RecyclerView recordList;

    private RecordAdapter adapter;
    private ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (null == recordList) {
            initView();
        }
        showData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_recharge:
                Intent intent = new Intent(this, RechargeActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {


        cover = findViewById(R.id.cover);
        name = findViewById(R.id.name);
        winCount = findViewById(R.id.winCount);
        coin = findViewById(R.id.coin);
        recordList = findViewById(R.id.winList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recordList.setLayoutManager(manager);
    }

    private void showData() {
        if (null == records) {
            loadData();
        } else {
            if (null == adapter) {
                adapter = new RecordAdapter();
                adapter.setData(records, false);
            }
        }
    }

    private void loadData() {

    }
}

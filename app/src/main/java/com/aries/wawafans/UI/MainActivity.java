package com.aries.wawafans.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.wawafans.Adapter.RoomAdapter;
import com.aries.wawafans.Bean.Machine;
import com.aries.wawafans.MyApplication;
import com.aries.wawafans.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, RoomAdapter.OnItemClicedListener, DrawerLayout.DrawerListener {

    private RecyclerView roomListView;
    private DrawerLayout drawer;
    private ArrayList<Machine> rooms;
    private ImageView cover;
    private TextView name;
    private MenuItem loginMenuItem;

    private RoomAdapter adapter;

    private final int REQUEST_LOGIN_SIDEMENU = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == roomListView) {
            initView();
        }
        showData();
    }

    @Override
    public void onBackPressed() {
        if (null == drawer) {
            drawer = findViewById(R.id.drawer_layout);
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_me) {
            //if (application.isLogin()) {
                Intent intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
            //}
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //关闭侧边栏
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.nav_recharge:
                intent.setClass(this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_reward:
                intent.setClass(this, InviteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_code:
                intent.setClass(this, CodeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_feedback:
                intent.setClass(this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_exit:
                if (application.isLogin()) {
                    application.handleExie();
                } else {
                    intent.setClass(this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_LOGIN_SIDEMENU);
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //侧滑事件监听
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        Log.e("DRL", "onDrawerOpened");
        showUserInfo();
        changeMenuItem();
    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        drawer.addDrawerListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        if (null != headerLayout) {
            cover = headerLayout.findViewById(R.id.cover);
            name = headerLayout.findViewById(R.id.name);
        }

        navigationView.setNavigationItemSelectedListener(this);

        loginMenuItem = navigationView.getMenu().findItem(R.id.nav_exit);
        roomListView = findViewById(R.id.roomlist);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        roomListView.setLayoutManager(manager);
    }


    private void loadData() {
        String watchVideo = "rtmp://12701.liveplay.myqcloud.com/live/12701_c5e4ad7ebdf111e792905cb9018cf0d4";
        String realVideo = "rtmp://192.168.1.52/live";
//        Machine one = new Machine(1, "第一个", "1111111", "192.168.1.51", 8888, (short) 5);
//        Machine two = new Machine(2, "第二个", "222222", "192.168.1.51", 8888, (short) 6);
//        Machine three = new Machine(3, "第三个", "33333", "192.168.1.52", 8888, (short) 7);
//        Machine four = new Machine(4, "第四个", "444444", "192.168.1.53", 8888, (short) 8);
//        Machine five = new Machine(5, "第五个", "55555", "192.168.1.51", 8888, (short) 9);
        Machine one = new Machine(1, "第一个", "1111111", "kyly1982.vicp.io", 40000, (short) 5);
        Machine two = new Machine(2, "第二个", "222222", "kyly1982.vicp.io", 40001, (short) 6);
        Machine three = new Machine(3, "第三个", "33333", "kyly1982.vicp.io", 40002, (short) 7);
        Machine four = new Machine(4, "第四个", "444444", "kyly1982.vicp.io", 40003, (short) 3);
        Machine five = new Machine(5, "第五个", "55555", "kyly1982.vicp.io", 40003, (short) 2);
        one.setWatchVideo(watchVideo);
        two.setWatchVideo(watchVideo);
        three.setWatchVideo(watchVideo);
        four.setWatchVideo(watchVideo);
        five.setWatchVideo(watchVideo);
        one.setRealTimeVideo(realVideo);
        two.setRealTimeVideo(realVideo);
        three.setRealTimeVideo(realVideo);
        four.setRealTimeVideo(realVideo);
        five.setRealTimeVideo(realVideo);
        one.setState((short) 2);
        two.setState((short) 1);
        three.setState((short) 1);
        rooms = new ArrayList<>(5);
        rooms.add(one);
        rooms.add(two);
        rooms.add(three);
        rooms.add(four);
        rooms.add(five);
        showData();
    }

    private void showData() {
        if (null == rooms || rooms.isEmpty()) {
            loadData();
        }
        if (null == adapter) {
            adapter = new RoomAdapter(rooms, this);
            roomListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void showUserInfo() {
        if (application.isLogin()) {
            Glide.with(this)
                    .load(application.getUser().getPortrait())
                    .into(cover);
            name.setText(application.getUser().getNick() + "");
            cover.setTag(application.getUser().getId());
        } else {
            cover.setBackgroundResource(R.mipmap.ic_launcher_round);
            cover.setTag(0);
            name.setText("未登录!");
        }
    }

    private void changeMenuItem() {
        loginMenuItem.setTitle(application.isLogin() ? "退出登录" : "点击登录");
    }

    @Override
    public void onItemClicked(Machine machine) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("room", machine);
        switch (machine.getState()) {
            case 0:
                showMessage("此机当前不可用，请选择其它机器！");
                break;
            case 1:
                Intent intent = new Intent(this, PlayActivity.class);
                bundle.putSerializable("room", machine);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 2:
                Intent intent1 = new Intent(this, ReadyActivity.class);
                bundle.putSerializable("room", machine);
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
        }
    }
}

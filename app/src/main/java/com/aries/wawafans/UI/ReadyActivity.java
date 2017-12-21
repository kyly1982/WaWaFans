package com.aries.wawafans.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aries.wawafans.Bean.Machine;
import com.aries.wawafans.R;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.rtmp.TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;

public class ReadyActivity extends BaseActivity implements View.OnClickListener {

    private TXLivePlayer liveplayer;
    private TXCloudVideoView videoView;
    private TextView price, playCount, winCount;
    private Button coin;

    private Machine room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        room = (Machine) getIntent().getExtras().getSerializable("room");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == videoView) {
            initView();
        }
        if (null != room) {
            showData();
        }
    }

    @Override
    protected void onDestroy() {
        if (null != liveplayer) {
            liveplayer.stopPlay(true);
        }
        videoView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coin:
                handleCoin();
                break;
            case R.id.start:
                startPlay();
                break;
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);

        videoView = findViewById(R.id.videoVeiw);

        price = findViewById(R.id.stare);
        playCount = findViewById(R.id.playCoun);
        winCount = findViewById(R.id.winCount);

        findViewById(R.id.coin).setOnClickListener(this);
        findViewById(R.id.start).setOnClickListener(this);

        liveplayer = new TXLivePlayer(this);
        liveplayer.setPlayerView(videoView);
        liveplayer.setRenderMode(RENDER_MODE_ADJUST_RESOLUTION);//图像等比例缩放，剧中显示，不会裁剪掉图像
        liveplayer.enableHardwareDecode(true);

    }

    private void showData() {
        price.setText(room.getPrice() + "");
        playCount.setText(999 + "");
        winCount.setText("16");
        playVideo();
    }

    private void playVideo() {
        String videoUrl = room.getWatchVideo();//处理路径
        if (!TextUtils.isEmpty(videoUrl) && videoUrl.length() > 10) {
            liveplayer.startPlay(videoUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP_ACC);//低延迟使用PLAY_TYPE_LIVE_RTMP_ACC
        }
    }

    private void handleCoin() {
        Intent intent = new Intent(this, RechargeActivity.class);
        startActivity(intent);
    }

    private void startPlay() {
        Intent intent = new Intent(this, PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("room", room);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

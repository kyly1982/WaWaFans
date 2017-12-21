package com.aries.wawafans.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.aries.wawafans.Bean.Command;
import com.aries.wawafans.Bean.Machine;
import com.aries.wawafans.R;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.rtmp.TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;

public class PlayActivity extends BaseActivity implements View.OnTouchListener {


    public native int initConnection(int id, String host, int port, int pwd);

    public native int sendActionCommand(int userId, int command);

    static {
        System.loadLibrary("native-lib");
    }


    private TXLivePlayer liveplayer;
    private TXCloudVideoView videoView;
    private TextView tink;

    private Machine room;

    private int time;
    private boolean active = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        room = (Machine) getIntent().getExtras().getSerializable("room");
        time = room.getTime();
        initConnection(room.getId(), room.getHost(), room.getPort(), room.getKey());//初始化连接
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == videoView) {
            initView();
        }
        if (null != room) {
            playVideo();
        }
    }

    @Override
    protected void onDestroy() {
        if (null != liveplayer) {
            liveplayer.stopPlay(true);
            videoView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleAction(v, false);
                break;
            case MotionEvent.ACTION_UP:
                handleAction(v, true);
                break;
        }
        return false;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    handler.removeMessages(2);
                    break;
                case 1:
                    handler.sendEmptyMessageDelayed(2, 1000);
                    break;
                case 2:
                    time -= 1;
                    tink.setText(time + "");
                    if ((0 == time)) {
                        sendCommand(Command.START_GRAB);
                        active = false;
                        getResult();
                    } else {
                        handler.sendEmptyMessageDelayed(2, 1000);
                    }
                    break;
            }
        }
    };

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);

        videoView = findViewById(R.id.videoVeiw);
        tink = findViewById(R.id.roundTime);
        tink.setText(room.getTime() + "");
        findViewById(R.id.left).setOnTouchListener(this);
        findViewById(R.id.right).setOnTouchListener(this);
        findViewById(R.id.front).setOnTouchListener(this);
        findViewById(R.id.back).setOnTouchListener(this);
        findViewById(R.id.grap).setOnTouchListener(this);

        liveplayer = new TXLivePlayer(this);
        liveplayer.setPlayerView(videoView);
        liveplayer.setRenderMode(RENDER_MODE_ADJUST_RESOLUTION);//图像等比例缩放，剧中显示，不会裁剪掉图像
        liveplayer.enableHardwareDecode(true);
    }

    private void handleAction(View view, boolean isStop) {
        if (active) {
            if (isStop) {
                sendCommand(Command.STOP_MOVE);
            } else {
                switch (view.getId()) {
                    case R.id.left:
                        sendCommand(Command.MOVE_LEFT);
                        break;
                    case R.id.right:
                        sendCommand(Command.MOVE_RIGHT);
                        break;
                    case R.id.front:
                        sendCommand(Command.MOVE_FRONT);
                        break;
                    case R.id.back:
                        sendCommand(Command.MOVE_BACK);
                        break;
                    case R.id.grap:
                        active = false;
                        handler.sendEmptyMessage(0);
                        sendCommand(Command.START_GRAB);
                        break;
                }
            }
        }
    }

    private void sendCommand(Command command) {
        sendActionCommand(36, command.rawValue());
    }

    private void playVideo() {
        handler.sendEmptyMessage(1);
        String videoUrl = room.getRealTimeVideo();//处理路径
        if (!TextUtils.isEmpty(videoUrl) && videoUrl.length() > 10) {
            liveplayer.startPlay(videoUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP_ACC);//低延迟使用PLAY_TYPE_LIVE_RTMP_ACC
        }
    }

    private void getResult() {

    }

    private void showWin() {

    }

    private void showFail() {

    }
}

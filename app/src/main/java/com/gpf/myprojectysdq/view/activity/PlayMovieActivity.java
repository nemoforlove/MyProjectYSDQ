package com.gpf.myprojectysdq.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.bean.Video;
import com.gpf.myprojectysdq.utils.TimeUtils;
import com.gpf.myprojectysdq.weight.MyVideoView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.VideoView;

public class PlayMovieActivity extends BaseActivity implements View.OnClickListener {

    private final int PROGRESS = 0X11;// 更新播放进度
    private final int HIDE_MEDIA_CONTROLLER = 0X12;// 控制控制面板的隐藏和显示
    private final int FULL_SCREEN = 1;// 全屏尺寸
    private final int DEFAULT_SCREEN = 2;// 默认尺寸
    @ViewInject(R.id.vv)
    private MyVideoView vvPlay;
    @ViewInject(R.id.btnVoice)
    private Button btnVoice;
    @ViewInject(R.id.sbVoice)
    private SeekBar sbVoice;
    @ViewInject(R.id.tvTime)
    private TextView tvTime;
    @ViewInject(R.id.tvName22)
    private TextView tvName;
    @ViewInject(R.id.tvCurrentTime)
    private TextView tvCurrentTime;
    @ViewInject(R.id.tvTotalTime)
    private TextView tvTotalTime;
    @ViewInject(R.id.sbVideo)
    private SeekBar sbVideo;
    @ViewInject(R.id.btnBack)
    private Button btnBack;
    @ViewInject(R.id.btnPre)
    private Button btnPre;
    @ViewInject(R.id.btnPlayOrPause)
    private Button btnPlayOrPause;
    @ViewInject(R.id.btnNext)
    private Button btnNext;
    @ViewInject(R.id.btnSwitchScreen)
    private Button btnSwitchScreen;
    @ViewInject(R.id.tvDianLiang)
    private TextView tvDianLiang;
    @ViewInject(R.id.myController)
    private RelativeLayout myController;// 控制面板所在的布局
    @ViewInject(R.id.btnCollection)
    private ImageButton btnCollection;
    @ViewInject(R.id.ll_buffer)
    private LinearLayout ll_buffer;// 缓冲提示布局
    @ViewInject(R.id.ll_loading)
    private LinearLayout ll_loading;// 缓冲提示布局
    // 系统声音管理者
    private AudioManager am;
    // 当前的声音值
    private int currentVoice;
    // 最大的声音值，系统声音的范围：0~15，共15个等级
    private int maxVoice;
    // 是否为静音，默认不是静音
    private boolean isMute = false;
    // 传入进来的视频集合
    private ArrayList<Video> list;
    // 传过来的位置
    private int position;
    // 其他应用调用
    private Uri uri;
    // 定义手势识别器：1.定义 2.实例化 3.关联activity的onTouch方法
    private GestureDetector detector;
    // 标记是否显示控制面板
    private boolean isShowMediaController = false;
    // 是否全屏
    private boolean isFullScreen;
    // 当前手机屏幕的宽
    private int screenWidth = 0;
    // 当前手机屏幕的高
    private int screenHeight = 0;
    // 视频的真实宽
    private int videoWidth;
    // 视频的真实高
    private int videoHeight;
    // 电量广播接收者
    private MyReceiver receiver;
    private boolean isUserSystem = false;// 是否使用系统的监视视频卡的方法，默认使用我们自己实现的
    private long preCurrentPosition;// 上一次的播放进度
    // 处理消息对象
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HIDE_MEDIA_CONTROLLER:
                    // 隐藏控制面板
                    hideMediaController();
                    break;
                case PROGRESS:
                    // 得到当前进度，设置给seekbar
                    long currentPosition = vvPlay.getCurrentPosition();
                    sbVideo.setProgress((int) currentPosition);
                    // 更新当前播放进度
                    tvCurrentTime.setText(TimeUtils.formatTime(currentPosition));
                    // 设置系统时间
                    tvTime.setText(getSystemTime());

                    // 设置网络视频的缓冲进度，本地视频无缓冲，缓冲也是每隔一秒更新一次
                    if (isNetUri) {
                        // 缓冲的实现算法
                        int buffer = vvPlay.getBufferPercentage();// 得到缓冲，缓冲范围：0~100
                        int totalBuffer = buffer * sbVideo.getMax();// 得到总的缓冲
                        int progress = totalBuffer / 100;// 最终缓冲值
                        sbVideo.setSecondaryProgress(progress);
                    } else {
                        sbVideo.setSecondaryProgress(0);
                    }


                    // 自实现监听卡
                    if(!isUserSystem && vvPlay.isPlaying()){
                    int buffer = (int) (currentPosition - preCurrentPosition);
                        if(buffer < 500){
                            // 视频卡了
                            ll_buffer.setVisibility(View.VISIBLE);
                        }else{
                            // 视频不卡
                            ll_buffer.setVisibility(View.GONE);
                        }
                    }else{
                        ll_buffer.setVisibility(View.GONE);
                    }

                    preCurrentPosition = currentPosition;// 必须放在用之后
                    // 3.每秒更新一次
                    mHandler.removeMessages(PROGRESS);// 移除之前的消息
                    mHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
                    break;
            }
        }
    };
    private boolean isNetUri;// 是否为网络资源
    // 获取系统时间的方法
    private String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }


    @Override
    public int setLayout() {
        return R.layout.activity_play_movie;
    }

    @Override
    public void initView() {
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        x.view().inject(this);
        // 实例化am，并通过am获取当前音量和最大音量
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        currentVoice = am.getStreamVolume(AudioManager.STREAM_MUSIC);// 当前
        maxVoice = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大
        // 设置音量进度条的最大值和当前值
        sbVoice.setMax(maxVoice);
        sbVoice.setProgress(currentVoice);
        // 第二步：实例化手势识别器,少第三部不起作用
        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            // 长按 --- 设置视频的暂停和播放
            @Override
            public void onLongPress(MotionEvent e) {
                startAndPause();
                super.onLongPress(e);
            }

            // 单机 --- 控制控制面板的显示和隐藏
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (isShowMediaController) {
                    hideMediaController();//隐藏
                    // 移除隐藏控制面板的消息
                    mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
                } else {
                    showMediaController();//显示
                    // 显示之后定时发送消息：5秒之后隐藏
                    // 发送隐藏控制面板的消息
                    mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
                }
                return super.onSingleTapConfirmed(e);
            }

            // 双击 --- 默认尺寸和全屏之间的切换
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // 全屏和默认尺寸之间的切换
                setFullScreenAndDefault();
                return super.onDoubleTap(e);
            }
        });
        // 注册电量广播
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        // 电量变化的广播
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        // 电量低的广播
//        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        registerReceiver(receiver, intentFilter);
    }

    private void setFullScreenAndDefault() {
        if (isFullScreen) {
            // 设置为默认
            setVideoStyle(DEFAULT_SCREEN);
        } else {
            // 设置为全屏
            setVideoStyle(FULL_SCREEN);
        }
    }

    private void setVideoStyle(int full_screen) {
        switch (full_screen) {
            case FULL_SCREEN:
                // 设置视频的大小 -- 屏幕有多大就设置多大
                vvPlay.setVideoSize(screenWidth, screenHeight);
                // 切换按钮的图片 --- 缩小
                btnSwitchScreen.setBackgroundResource(R.mipmap.ys_player_small);
                // 修改标记
                isFullScreen = true;
                break;
            case DEFAULT_SCREEN:
                // 设置视频的默认大小 -- 直到某一个边充满屏幕为默认大小而不是视频的真实大小为默认
                // 系统ViewView类中自有的算法
                // 视频真实的宽高
                int mVideoWidth = videoWidth;
                int mVideoHeight = videoHeight;
                // 手机屏幕的宽高
                int width = screenWidth;
                int height = screenHeight;
                if (mVideoWidth * height < width * mVideoHeight) {
                    //Log.i("@@@", "image too wide, correcting");
                    width = height * mVideoWidth / mVideoHeight;
                } else if (mVideoWidth * height > width * mVideoHeight) {
                    //Log.i("@@@", "image too tall, correcting");
                    height = width * mVideoHeight / mVideoWidth;
                }
                vvPlay.setVideoSize(width, height);
                // 切换按钮的图片 --- 扩大
                btnSwitchScreen.setBackgroundResource(R.mipmap.ys_player_full);
                // 修改标记
                isFullScreen = false;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // 有释放资源操作时放在super的前面，先释放子类，在释放父类，防止出现空指针
        // onCreate初始化时先初始化父类，再初始化子类，即super在前面
        // 取消注册
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int dianliang = intent.getIntExtra("level", 0);// 范围0~100，默认为0
            // 设置电量
            tvDianLiang.setText("电量：" + dianliang + "%");
        }
    }

    @Override
    public void getMethod() {

    }

    @Override
    public void setStyle() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void initData() {
        uri = getIntent().getData();
        list = (ArrayList<Video>) getIntent().getSerializableExtra("list");
        position = getIntent().getIntExtra("position", 0);
        if (list != null && list.size() > 0) {
//            Log.d("test","================================"+list.size());
            Video video = list.get(position);
            tvName.setText(video.getName());// 设置视频名称
            isNetUri = TimeUtils.isNetUri(video.getPath()); // 修改标记，用来标识是否为网络资源
            vvPlay.setVideoPath(video.getPath());// 设置视频路径
        } else if (uri != null) {
            tvName.setText(uri.toString());// 设置视频名称
            isNetUri = TimeUtils.isNetUri(uri.toString()); // 修改标记，用来标识是否为网络资源
            vvPlay.setVideoURI(uri);
        } else {
            Toast.makeText(PlayMovieActivity.this, "文件没找到或者已经删除！", Toast.LENGTH_SHORT).show();
        }
        // 设置刚进来时的按钮状态
        setButtonState();
        // 得到当前屏幕的宽高 -- 已过时
//        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
//        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        // 得到屏幕宽高的最新方式
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    @Override
    public void setListener() {
        // 按钮设置监听
        btnBack.setOnClickListener(this);
        btnPre.setOnClickListener(this);
        btnPlayOrPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnSwitchScreen.setOnClickListener(this);
        btnVoice.setOnClickListener(this);
        // 准备好的监听
        vvPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                videoWidth = mp.getVideoWidth();// 视频的真实宽
                videoHeight = mp.getVideoHeight();// 视频的真实高
                vvPlay.start();// 开始播放
                // 刚进来时默认隐藏控制面板
                hideMediaController();
                // 关联长度
                int duration = (int) vvPlay.getDuration();
                sbVideo.setMax(duration);
                tvTotalTime.setText(TimeUtils.formatTime(mp.getDuration()));
                // 发消息更新进度
                mHandler.sendEmptyMessage(PROGRESS);
                // 设置尺寸(视频真实的宽和高)
//                vvPlay.setVideoSize(mp.getVideoWidth(),mp.getVideoHeight());
                // 设置第一次进去以默认尺寸播放
                setVideoStyle(DEFAULT_SCREEN);

                // 准备好之后隐藏加载提示
                ll_loading.setVisibility(View.GONE);

            }
        });

        // 设置控制器
//        vvPlay.setMediaController(new MediaController(this));

        // 播放出错的监听
        vvPlay.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(PlayMovieActivity.this, "播放出错了！", Toast.LENGTH_SHORT).show();
                finish();
                return true;// return false会向下传递然后弹出错误对话框，返回true则不会
            }
        });
        // 播放完成监听
        vvPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                Toast.makeText(PlayMovieActivity.this, "播放完成了！", Toast.LENGTH_SHORT).show();
                playNextVideo();
            }
        });
        // 播放进度监听
        sbVideo.setOnSeekBarChangeListener(new VideoSeekBarListener());
        // 音量进度监听
        sbVoice.setOnSeekBarChangeListener(new VoiceSeekBarListener());

        // 监听视频播放卡的监听，给用户提示
        if(isUserSystem){
            vvPlay.setOnInfoListener(new MyInfoListener());
        }
    }

    class MyInfoListener implements OnInfoListener {

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            switch (what) {
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:// 视频卡了或者拖动卡了
                    // 显示缓冲进度提示
                    ll_buffer.setVisibility(View.VISIBLE);
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:// 卡结束了
                    // 隐藏进度提示
                    ll_buffer.setVisibility(View.GONE);
                    break;
            }
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVoice:
                updateVoice(currentVoice, isMute);
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnPre:
                playPreVideo();
                break;
            case R.id.btnPlayOrPause:
                startAndPause();
                break;
            case R.id.btnNext:
                playNextVideo();
                break;
            case R.id.btnSwitchScreen:
                setFullScreenAndDefault();
                break;
        }
        // 先移除再发送
        mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
        mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
    }

    // 播放上一个的方法
    private void playPreVideo() {
        if (list != null && list.size() > 0) {
            // 播放上一个
            position--;
            if (position >= 0) {
                ll_loading.setVisibility(View.VISIBLE);
                Video video = list.get(position);
                tvName.setText(video.getName());
                isNetUri = TimeUtils.isNetUri(video.getPath()); // 修改标记，用来标识是否为网络资源
                vvPlay.setVideoPath(video.getPath());
                setButtonState();
            }
        } else if (uri != null) {
            // 播放的是网络视频，那么设置上一个和下一个按钮均为不可用
            setBtnEnable(false);
        }
    }

    private void startAndPause() {
        if (vvPlay.isPlaying()) {
            // 正在播放，点击暂停，按钮显示图标为播放
            vvPlay.pause();
            btnPlayOrPause.setBackgroundResource(R.mipmap.player_play);
        } else {
            // 没有播放，点击播放，按钮图标为暂停
            vvPlay.start();
            btnPlayOrPause.setBackgroundResource(R.mipmap.player_pause);
        }
    }

    // 播放下一个的方法
    private void playNextVideo() {
        if (list != null && list.size() > 0) {
            // 播放下一个
            position++;
            if (position < list.size()) {
                ll_loading.setVisibility(View.VISIBLE);
                Video video = list.get(position);
                tvName.setText(video.getName());
                isNetUri = TimeUtils.isNetUri(video.getPath()); // 修改标记，用来标识是否为网络资源
                vvPlay.setVideoPath(video.getPath());
                setButtonState();
            }
        } else if (uri != null) {
            // 播放的是网络视频，那么设置上一个和下一个按钮均为不可用
            setBtnEnable(false);
        }
    }

    private void setButtonState() {
        if (list != null && list.size() > 0) {
            // 区分三种情况
            if (list.size() == 1) {
                // 两个按钮置灰
                setBtnEnable(false);
            } else if (list.size() == 2) {
                if (position == 0) {
                    btnPre.setEnabled(false);
                    // 同时将下一个设为true
                    btnNext.setEnabled(true);
                } else if (position == list.size() - 1) {
                    btnNext.setEnabled(false);
                    // 同时将上一个设为true
                    btnPre.setEnabled(true);
                }
            } else {
                if (position == 0) {
                    btnPre.setEnabled(false);
                } else if (position == list.size() - 1) {
                    btnNext.setEnabled(false);
                } else {
                    setBtnEnable(true);
                }
            }
        } else if (uri != null) {
            // 两个按钮置灰
            setBtnEnable(false);
        }
    }

    private void setBtnEnable(boolean flag) {
        // 设置两个按钮是否可用，包括设置点击的效果图
        btnNext.setEnabled(flag);
        btnPre.setEnabled(flag);
    }

    // 播放进度条监听 ---- 防止用户点击屏幕使控制面板出现之后点击控制面板的图标超过5秒隐藏掉，影响用户体验
    public class VideoSeekBarListener implements SeekBar.OnSeekBarChangeListener {

        // 滑动时会回调，自动更新时也是回调该方法，与人为滑动的区别是fromUser的值是true还是false
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                vvPlay.seekTo(progress);
            }
        }

        // 手指触碰时触发
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
        }

        // 手指离开触发
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
        }
    }

    // 音量进度条监听
    public class VoiceSeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                if (progress > 0) {
                    isMute = false;
                    // 设置图片
                    btnVoice.setBackgroundResource(R.mipmap.ys_voice_img);
                } else {
                    isMute = true;
                    btnVoice.setBackgroundResource(R.mipmap.ys_voice_no_bg);
                }
                // 更新声音进度的方法
                updateVoice(progress, isMute);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
        }
    }

    // 更新音量进度条的方法
    private void updateVoice(int progress, boolean isMute) {
        if (isMute) {
            // 是静音时
            am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 1);
            sbVoice.setProgress(0);
            // 修改图片为静音时图片
            btnVoice.setBackgroundResource(R.mipmap.ys_voice_no_bg);
        } else {
            // 非静音时
            //1：调出系统的音量界面   0：不调出，只显示我们自己的
            am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            sbVoice.setProgress(progress);
            currentVoice = progress;
            btnVoice.setBackgroundResource(R.mipmap.ys_voice_img);
        }

    }

    private float startY;// 开始滑动的位置
    private float touchRang;// 屏幕的高(是竖屏的宽)
    private int mVol;// 当一按下时的音量，不能使用currentVoice变量，因为该值一直在变化，所以额外定义

    // 手势识别器的关键第三部:将activity的点击事件关联识别器
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        // 与屏幕上下滑动调节音量相关
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:// 按下
                //按下的时候记录y坐标
                startY = event.getY();
                // 得到按下时的当前音量
                mVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                // 得到横屏时的高,即宽高的最小值，横屏和竖屏时的宽高不一样，相反的
                touchRang = Math.min(screenWidth, screenHeight);
                // 移除隐藏的消息
                mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
                break;
            case MotionEvent.ACTION_MOVE://移动
                // 记录相关的值
                float endY = event.getY();
                // 移动的距离，上滑音量增大，值为正值
                // 下滑音量减小，值为负值，公式如下：
                // 滑动距离 / 屏幕的高度 = 改变的音量 / 系统总音量
                // 最终音量 = 按下时的当前音量 + 改变的音量
                float distanceY = startY - endY;// 滑动距离
                // 改变的音量 = (滑动距离 / 屏幕的高度) * 系统总音量
                float change = (distanceY / touchRang) * maxVoice;
                // 最终音量 = 按下时的当前音量 + 改变的音量
                int voice = (int) Math.min(Math.max(mVol + change, 0), maxVoice);
                // 改变之后判断是否变成了静音
                if (change != 0) {
                    if (voice == 0) {
                        isMute = true;
                    } else {
                        isMute = false;
                    }
                    updateVoice(voice, isMute);
                }
                break;
            case MotionEvent.ACTION_UP://松开
                // 重新发送隐藏消息
                mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
                break;
        }
        return super.onTouchEvent(event);
    }

    // 显示控制面板
    private void showMediaController() {
        myController.setVisibility(View.VISIBLE);
        isShowMediaController = true;
    }

    // 隐藏控制面板
    private void hideMediaController() {
        myController.setVisibility(View.GONE);
        isShowMediaController = false;
    }

    // 关联手机自带音量调节物理按键和播放器音量按键,一起控制
    // 判断里面返回false，将出现两个音量调节进度条，一个系统的一个我们自己的
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            // 当按下物理音量减
            currentVoice--;
            updateVoice(currentVoice, false);
            mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
            mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            // 当按下物理音量加
            currentVoice++;
            updateVoice(currentVoice, false);
            mHandler.removeMessages(HIDE_MEDIA_CONTROLLER);
            mHandler.sendEmptyMessageDelayed(HIDE_MEDIA_CONTROLLER, 5000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.BannerResponse;
import com.demo.downrefresh.netapi.GetBanner;
import com.demo.downrefresh.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author w.w
 *         banner图构造
 */
public class Main36APPStoreActivity extends Activity {
    private TextView tv;
    private double[] nimeide = {0.75, 0.7, 0.2, 0.61, 0.523, 0.2415, 0.369};
    private Random mRandom = new Random();

    private static final String url = "http://192.168.3.200/nsmapi/h5sources/superstar/video/12.mp4";

    private VideoView mVideoview;
    private TextView player;
    private RelativeLayout ctrlLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main36);

        tv = (TextView) findViewById(R.id.tv);

        tv.setText(String.format("击败了 %s 的用户", (int) (0.2 * 100)));

        mVideoview = (VideoView) findViewById(R.id.videoview);
        player = (TextView) findViewById(R.id.player);
        ctrlLayout = (RelativeLayout) findViewById(R.id.ctrl_layout);

        mVideoview.setVideoURI(Uri.parse(url));
        mVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("hehe", "videoview 准备完毕");
//                int i = new Random().nextInt(mVideoview.getDuration());
//                Log.i("hehe", "随机定位为: " + i);
                mVideoview.seekTo(1);
            }
        });
        mVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("hehe", "videoview 播放完毕");

                player.setVisibility(View.VISIBLE);
                player.setText("播放");
            }
        });
        mVideoview.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.i("hehe", "videoview info: 值为 " + what + "  ----  " + extra);
                return false;
            }
        });
        mVideoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("hehe", "videoview 错误: 值为 " + what + "  ----  " + extra);
                return false;
            }
        });

        myColtral myColtral = new myColtral(this);
        mVideoview.setMediaController(myColtral);

//        ctrlLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mVideoview.isPlaying()) {
//                    mVideoview.pause();
//                    player.setVisibility(View.VISIBLE);
//                    player.setText("播放");
//                } else {
//                    player.setVisibility(View.INVISIBLE);
//                    mVideoview.start();
//                }
//            }
//        });

    }

    public void click(View view) {
//        goToMarket(this, "com.neishenme.what");
//        launchAppDetail("com.neishenme.what", null);
        int i = mRandom.nextInt(nimeide.length);
        Log.i("hehe", nimeide[i] + "");
//        tv.setText(String.format("击败了 %s 的用户", (int) (nimeide[i] * 100)));
        tv.setText("击败了 " + (int) (nimeide[i] * 100) + "% 的用户");
        mVideoview.setVideoURI(Uri.parse(url));
    }

    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class  myColtral extends MediaController{

        public myColtral(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public myColtral(Context context) {
            super(context);
        }

        public myColtral(Context context, boolean useFastForward) {
            super(context, useFastForward);
        }


    }

}

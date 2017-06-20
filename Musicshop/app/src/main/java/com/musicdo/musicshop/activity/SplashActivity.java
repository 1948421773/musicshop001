package com.musicdo.musicshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.musicdo.musicshop.R;

/**
 * 名 称 ：
 * 描 述 ：
 * 创建者：  张海明
 * 创建时间：2017/6/14.
 * 版 本 ：
 * 备 注 ：
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
//        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);
        boolean isFirstOpen=true;
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
//        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

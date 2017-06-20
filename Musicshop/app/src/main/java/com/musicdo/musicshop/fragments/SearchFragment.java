package com.musicdo.musicshop.fragments;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by adilsoomro on 8/19/16.
 */
public class SearchFragment extends BaseFragment {

    private final static String TAG = HomeFragment.class.getSimpleName();

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        Log.e(TAG,"搜索面的Fragment的UI被初始化了");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("搜索");
        Log.e(TAG,"搜索面的Fragment的数据被初始化了");
    }
}

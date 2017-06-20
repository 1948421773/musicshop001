package com.musicdo.musicshop.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.musicdo.musicshop.R;
import com.musicdo.musicshop.fragments.BaseFragment;
import com.musicdo.musicshop.fragments.HomeFragment;
import com.musicdo.musicshop.fragments.SearchFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout frameLayout;
    RadioGroup rgMain;
    //装fragment的实例集合
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    //缓存Fragment或上次显示的Fragment
    private BaseFragment tempFragment;
    ImageView Qrcode,iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        init();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();
        switchFragment(null,fragments.get(0));
    }

    private void initListener() {
        rgMain.check(R.id.rb_home);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home: //首页
                        position = 0;
                        break;
                    case R.id.rb_type: //分类
                        position = 1;
                        break;
                    case R.id.rb_community: //发现
                        position = 2;
                        break;
                    case R.id.rb_cart: //购物车
                        position = 3;
                        break;
                    case R.id.rb_user: //个人中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置得到相应的Fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数: 上次显示的Fragment
                 * 第二个参数: 当前正要显示的Fragment
                 */
                switchFragment(tempFragment,baseFragment);
            }
        });
    }

    /**
     * 切换Fragment
     * @param fragment
     * @param nextFragment
     */
    private void switchFragment(BaseFragment fragment,BaseFragment nextFragment){
        if (tempFragment != nextFragment){
            tempFragment = nextFragment;
            if (nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()){
                    //隐藏当前的Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    /**
     * 添加的时候按照顺序
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new HomeFragment());
    }

    /**
     * 根据位置得到对应的 Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void init() {
        frameLayout=(FrameLayout) findViewById(R.id.frameLayout);
        rgMain=(RadioGroup) findViewById(R.id.rg_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}

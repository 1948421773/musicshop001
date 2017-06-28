package com.musicdo.musicshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 名 称 ：
 * 描 述 ：
 * 创建者：  张海明
 * 创建时间：2017/6/26.
 * 版 本 ：
 * 备 注 ：
 */

public class HomeLinearLayout extends LinearLayout {
    private double xDistance,yDistance  ;
    private float xStart ,yStart,xEnd ,yEnd;
    public HomeLinearLayout(Context context) {
        super(context);
    }

    public HomeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HomeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xStart = ev.getX();
                yStart = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                xEnd = ev.getX();
                yEnd = ev.getY();
                break;
            default:break;
        }
        xDistance = Math.abs(xEnd-xStart);
        yDistance = Math.abs(yEnd-yStart);
        if(xDistance>yDistance){
            return false;  //拦截事件向下分发
        }else{
            return false;
        }
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            return false;
//        } else {
//            return true;
//        }
    }*/
}

package com.musicdo.musicshop.loopswitch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.musicdo.loopswitch.AutoLoopSwitchBaseAdapter;
import com.musicdo.loopswitch.AutoLoopSwitchBaseView;


/**
 * @author ryze
 * @since 1.0  2016/07/17
 */
public class AutoSwitchView extends AutoLoopSwitchBaseView {
  private Context context;
  private double xDistance,yDistance  ;
  private float xStart ,yStart,xEnd ,yEnd;

  public AutoSwitchView(Context context) {
    super(context);
    this.context=context;
  }

  public AutoSwitchView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context=context;
  }

  public AutoSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context=context;
  }

  public AutoSwitchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    this.context=context;
  }


  @Override
  protected void onSwitch(int index, Object o) {
    LoopModel model = (LoopModel) o;
    if (model != null) {
    }
  }

  @Override
  protected View getFailtView() {
    return null;
  }

  @Override
  protected long getDurtion() {
    return 3000;
  }

  @Override
  public void setAdapter(AutoLoopSwitchBaseAdapter adapter) {
    super.setAdapter(adapter);
    mHandler.sendEmptyMessage(LoopHandler.MSG_REGAIN);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()){
      case MotionEvent.ACTION_UP:
        Log.i("AutoSwitchView","onTouchEvent-----ACTION_UP");
        break;
      case MotionEvent.ACTION_DOWN:
        Log.i("AutoSwitchView","onTouchEvent-----ACTION_DOWN");
        break;
      default:break;
    }
    return false;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    switch (ev.getAction()){
      case MotionEvent.ACTION_DOWN:
        Log.i("AutoSwitchView","2dispatchTouchEvent-----ACTION_DOWN");
        break;
      case MotionEvent.ACTION_UP:
        Log.i("AutoSwitchView","dispatchTouchEvent-----ACTION_UP");
        break;
      default:break;
    }
    return super.dispatchTouchEvent(ev);
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
    if(xDistance>yDistance)
      return true;  //拦截事件向下分发
    return false;

  }*/

}
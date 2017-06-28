package com.musicdo.musicshop.loopswitch;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.musicdo.loopswitch.AutoLoopSwitchBaseAdapter;

import java.util.List;


/**
 * @author ryze
 * @since 1.0  2016/07/17
 */
public class AutoSwitchAdapter extends AutoLoopSwitchBaseAdapter {

  private Context mContext;

  private List<LoopModel> mDatas;

  public AutoSwitchAdapter() {
    super();
  }

  public AutoSwitchAdapter(Context mContext, List<LoopModel> mDatas) {
    this.mContext = mContext;
    this.mDatas = mDatas;
  }

  @Override
  public int getDataCount() {
    return mDatas == null ? 0 : mDatas.size();
  }

  @Override
  public View getView(int position) {
    ImageView imageView = new ImageView(mContext);
    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
    LoopModel model = (LoopModel) getItem(position);
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    imageView.setImageResource(model.getResId());

    imageView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
          case MotionEvent.ACTION_UP:
            Log.i("AutoSwitchAdapter","imageView^^^^^^onTouch^^^^ACTION_UP");
            break;
          case MotionEvent.ACTION_DOWN:
            Log.i("AutoSwitchAdapter","imageView^^^^^^onTouch^^^^ACTION_DOWN");
            break;
          default:break;
        }
        return false;
      }
    });
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.i("AutoSwitchAdapter","imageView/////onClick");
      }
    });
    return imageView;
  }

  @Override
  public Object getItem(int position) {
    if (position >= 0 && position < getDataCount()) {

      return mDatas.get(position);
    }
    return null;
  }


  @Override
  public View getEmptyView() {
    return null;
  }

  @Override
  public void updateView(View view, int position) {

  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);
  }

}

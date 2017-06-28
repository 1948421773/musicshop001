package com.musicdo.musicshop.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.musicdo.libzxing.activity.CaptureActivity;
import com.musicdo.libzxing.encoding.EncodingUtils;
import com.musicdo.musicshop.R;
import com.musicdo.musicshop.adapter.MyAdapter;
import com.musicdo.musicshop.loopswitch.AutoSwitchAdapter;
import com.musicdo.musicshop.loopswitch.AutoSwitchView;
import com.musicdo.musicshop.loopswitch.LoopModel;
import com.musicdo.musicshop.view.JdRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * Created by adilsoomro on 8/19/16.
 */
public class HomeFragment extends BaseFragment {
    private final static String TAG = HomeFragment.class.getSimpleName();
    ImageView Qrcode,iv;
    private TextView textView;
    public View view;
    public ScrollView sv_home_layout;
    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private CheckBox mCheckBox;
    private float mLastX;
    private double xDistance, yDistance;
    private float mCurPosX, mPosX, xEnd, yEnd;
    private int startX;
    private int startY;
    private GestureDetector mGestureDetector;
    private int verticalMinistance = 100;            //水平最小识别距离
    private int minVelocity = 10;            //最小识别速度
    /**
     * 列表
     */
//    RecyclerView mRecyclerView;

    /**
     * 下拉刷新
     */
    JdRefreshLayout mLayout;

    /**
     * 布局管理器
     */
    RecyclerView.LayoutManager mManager;

    /**
     * 数据
     */
    private List<Object> mDatas;

    /**
     * 适配器
     */
    private MyAdapter mAdapter;
    private AutoSwitchView mAutoSwitchView;
    private AutoSwitchAdapter autoSwitchAdapter;

    @Override
    public View initView() {
        mGestureDetector = new GestureDetector(mContext, new LearnGestureListener());
        view= LayoutInflater.from(mContext).inflate(R.layout.home_layout,null);
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        sv_home_layout=(ScrollView)view.findViewById(R.id.sv_home_layout);

        mAutoSwitchView = (AutoSwitchView) view.findViewById(R.id.loopswitch);
        List<LoopModel> datas = new ArrayList<LoopModel>();
        LoopModel model = null;

        model = new LoopModel("第一张", R.mipmap.loop_1);
        datas.add(model);
        model = new LoopModel("第二张", R.mipmap.loop_2);
        datas.add(model);
        model = new LoopModel("第三张", R.mipmap.loop_3);
        datas.add(model);
        model = new LoopModel("第四张", R.mipmap.loop_4);
        datas.add(model);
        autoSwitchAdapter = new AutoSwitchAdapter(mContext.getApplicationContext(), datas);
        mAutoSwitchView.setAdapter(autoSwitchAdapter);
        mAutoSwitchView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*int action = event.getAction();

                if(action == MotionEvent.ACTION_DOWN) {
                    // 记录点击到ViewPager时候，手指的X坐标
                    mLastX = event.getX();
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    // 超过阈值
                    if(Math.abs(event.getX() - mLastX) > 60f) {
                        mLayout.setEnabled(false);
                        sv_home_layout.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if(action == MotionEvent.ACTION_UP) {
                    // 用户抬起手指，恢复父布局状态
                    sv_home_layout.requestDisallowInterceptTouchEvent(false);
                    mLayout.setEnabled(true);
                }
                return true;*/
                return mGestureDetector.onTouchEvent(event);
            }

        });
        mAutoSwitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HomeFragment", "testBtn---onClick...");
            }
        });
//        autoSwitchAdapter.notifyDataSetChanged();

        mDatas=new ArrayList<>();
        for(int i=0;i<5;i++){
            mDatas.add(new Object());
        }
        mLayout = (JdRefreshLayout) view.findViewById(R.id.test_recycler_view_frame);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.test_recycler_view);
        mManager = new LinearLayoutManager(mContext);
//        mRecyclerView.setLayoutManager(mManager);
        mAdapter=new MyAdapter(mDatas);
//        mRecyclerView.setAdapter(mAdapter);
        mLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                doSth();
            }
        });

        /*mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });*/
        Qrcode=(ImageView)view.findViewById(R.id.iv_qrcode);
        Qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                //生成二维码
//                Bitmap qrBitmap = generateBitmap("http://www.csdn.net",400, 400);
//                Bitmap addBitmap=addLogo(qrBitmap,BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                iv.setImageBitmap(addBitmap);
            }
        });
        iv=(ImageView)view.findViewById(R.id.iv);

        resultTextView=(TextView)view.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) view.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView)view.findViewById(R.id.iv_qr_image);
        mCheckBox = (CheckBox) view.findViewById(R.id.logo);

        Button scanBarCodeButton = (Button) view.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        Button generateQRCodeButton = (Button) view.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String contentString = qrStrEditText.getText().toString();
                if (!contentString.equals("")) {
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,
                            mCheckBox.isChecked() ?
                                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) :
                                    null);
                    qrImgImageView.setImageBitmap(qrCodeBitmap);
                } else {
                    Toast.makeText(mContext, "Text can not be empty", Toast.LENGTH_SHORT).show();
               }
            }
        });
        return view;
    }


    //设置手势识别监听器
    public class LearnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
                showToast("left");
            } else if (e2.getX() - e1.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
                showToast("right");
            } else if (e1.getY() - e2.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
                showToast("up");
            } else if (e2.getY() - e1.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
                showToast("down");
            }
            return true;
        }
        //此方法必须重写且返回真，否则onFling不起效
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
        public void showToast(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }



    /**
     * demo
     */
    private void doSth() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mDatas.add(new Object());
                mAdapter.notifyDataSetChanged();
                mLayout.refreshComplete();
            }
        }.execute();
    }

    private Bitmap generateBitmap(String content,int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap addLogo(Bitmap qrBitmap, Bitmap logoBitmap) {
        int qrBitmapWidth = qrBitmap.getWidth();
        int qrBitmapHeight = qrBitmap.getHeight();
        int logoBitmapWidth = logoBitmap.getWidth();
        int logoBitmapHeight = logoBitmap.getHeight();
        Bitmap blankBitmap = Bitmap.createBitmap(qrBitmapWidth, qrBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawBitmap(qrBitmap, 0, 0, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        float scaleSize = 1.0f;
        while ((logoBitmapWidth / scaleSize) > (qrBitmapWidth / 5) || (logoBitmapHeight / scaleSize) > (qrBitmapHeight / 5)) {
            scaleSize *= 2;
        }
        float sx = 1.0f / scaleSize;
        canvas.scale(sx, sx, qrBitmapWidth / 2, qrBitmapHeight / 2);
        canvas.drawBitmap(logoBitmap, (qrBitmapWidth - logoBitmapWidth) / 2, (qrBitmapHeight - logoBitmapHeight) / 2, null);
        canvas.restore();
        return blankBitmap;
    }

    @Override
    public void initData() {
        super.initData();

//        textView.setText("首页");
        Log.e(TAG,"主页面的Fragment的数据被初始化了");
    }

}

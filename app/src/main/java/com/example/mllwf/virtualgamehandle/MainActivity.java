package com.example.mllwf.virtualgamehandle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mllwf.virtualgamehandle.custorm.RockerView;

public class MainActivity extends AppCompatActivity {

    private RockerView rockerview;
    private ImageView ivUp;
    private ImageView ivDown;
    private boolean isOnTouch = false;
    private int touchType = 0; //0代表没有触摸，1代表向上方向键，2代表向下方向键
    private float pX;
    private float pY;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText("pX: " + pX + "  pY:  " + pY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_roaming_rocker);
        tv = (TextView) this.findViewById(R.id.tv_position);
        initRoamRocker();
        initDirctionTouch();
    }

    TextView tv;

    private void initRoamRocker() {

        rockerview = (RockerView) this.findViewById(R.id.rv_rocker);
        ivUp = (ImageView) this.findViewById(R.id.iv_up);
        ivDown = (ImageView) this.findViewById(R.id.iv_down);
        rockerview.setRockerChangeListener(new RockerView.RockerChangeListener() {
            @Override
            public void report(float x, float y) {
                //TODO: 手柄滑动
                pX = x;
                pY = y;
                handler.sendEmptyMessage(0);
            }
        });
    }

    private void initDirctionTouch() {
        ivUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    isOnTouch = true;
                    touchType = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    isOnTouch = false;
                    touchType = 0;
                }
                return true;
            }
        });
        ivDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    isOnTouch = true;
                    touchType = 2;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    isOnTouch = false;
                    touchType = 0;
                }
                return true;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isOnTouch && touchType != 0) {
                        if (touchType == 1) {
                            toUpAction();
                        } else if (touchType == 2) {
                            toDownAction();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 向上移动处理逻辑
     */
    private void toUpAction() {
        Log.e("MainActivity :::: ", "向上移动处理逻辑");
    }

    /**
     * 向下移动处理逻辑
     */
    private void toDownAction() {
        Log.e("MainActivity :::: ", "向下移动处理逻辑");
    }

}

package com.chaos.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.multiplestatusviewtest.R;

import static android.content.ContentValues.TAG;

public class DragFloatActionButton extends LinearLayout {
    private Context mContext;
    private int parentHeight;
    private int parentWidth;
    private Boolean needShowView; //true 就弹出view
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    public DragFloatActionButton(Context context, WindowManager manager, WindowManager.LayoutParams params) {
        super(context);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        parentHeight = displayMetrics.heightPixels;
        parentWidth = displayMetrics.widthPixels;

        this.mContext = context;
        this.mWindowManager = manager;
        this.mLayoutParams = params;
        setBackgroundResource(R.drawable.game_sdk_float_icon);
//        mHideHandler.postDelayed(hideRunable, 3000);
    }

    public DragFloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private int lastX;
    private int lastY;

    private boolean isDrag;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
//                ViewGroup parent;
//                if (getParent() != null) {
//                    parent = (ViewGroup) getParent();
//                    parentHeight = parent.getHeight();
//                    parentWidth = parent.getWidth();
//                }
                break;
            case MotionEvent.ACTION_MOVE:
                needShowView = false;
                if (parentHeight <= 0 || parentWidth == 0) {
                    isDrag = false;
                    break;
                } else {
                    isDrag = true;
                }
                int dx = rawX - lastX;
                int dy = rawY - lastY;

                //这里修复一些华为手机无法触发点击事件
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance == 0) {
                    isDrag = false;
                    break;
                }
                float x = getX() + dx;
                float y = getY() + dy;
                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > parentWidth - getWidth() ? parentWidth - getWidth() : x;
                y = getY() < 0 ? 0 : getY() + getHeight() > parentHeight ? parentHeight - getHeight() : y;
//                setX(x);
//                setY(y);

                lastX = rawX - getWidth() / 2;
                lastY = rawY - getHeight();
                mLayoutParams.x = (int) lastX;
                mLayoutParams.y = (int) lastY;
                mWindowManager.updateViewLayout(this, mLayoutParams);

//                Log.e("aa", "isDrag===" + isDrag + "getX=" + getX() + ";getY=" + getY() + ";parentWidth=" + parentWidth);
                break;
            case MotionEvent.ACTION_UP:
                if (!isNotDrag()) {
                    //恢复按压效果
                    setPressed(false);
                    if (rawX >= parentWidth / 2) {
                        //靠右吸附
//                        mLayoutParams.x = parentWidth-getWidth();
//                        mWindowManager.updateViewLayout(this, mLayoutParams);
                        animateTo(mLayoutParams.x,parentWidth - getWidth());
                        isLeftHide = false;
                    } else {
                        //靠左吸附
//                        mLayoutParams.x = 0;
//                        mWindowManager.updateViewLayout(this, mLayoutParams);
                        animateTo(mLayoutParams.x,0);
                        isLeftHide = true;
                    }

//                    Log.e(TAG, "needShowView:=== " + needShowView);
                    needShowView = true;
                    hideHandler.postDelayed(hideRunable, 2000);

                    //弹出详细按钮
                    View view = LayoutInflater.from(mContext).inflate(R.layout.layout_window_view,null);
//                    addView(view);
                    
//                    WindowManager.LayoutParams rootParams =  new WindowManager.LayoutParams();
//                    rootParams.width = view.getWidth();
//                    rootParams.height = view.getHeight();
//                    rootParams.format = PixelFormat.TRANSPARENT;
//                    rootParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//                    rootParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
//                    mWindowManager.addView(view,rootParams);

                }
                break;
        }
        //如果是拖拽则消s耗事件，否则正常传递即可。
        return !isNotDrag() || super.onTouchEvent(event);
    }

    private boolean isNotDrag() {
        return !isDrag && (getX() == 0
                || (getX() == parentWidth - getWidth()));
    }


    //计时隐藏
    public final static int MSG_HIDE_LEFT = 0x01;
    public final static int MSG_HIDE_RIGHT = 0x02;
    private Boolean isLeftHide; //用来判断是要左边隐藏还是右边

    @SuppressLint("HandlerLeak")
    private Handler hideHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_HIDE_LEFT:
                    animateTo(mLayoutParams.x,0 - getWidth() / 2);
                    break;
                case MSG_HIDE_RIGHT:
                    animateTo(mLayoutParams.x,parentWidth - getWidth()/2);
                    break;
            }

        }
    };

    private Runnable hideRunable = new Runnable() {

        @Override
        public void run() {
            Message message = new Message();
            if (isLeftHide) {
                message.what = MSG_HIDE_LEFT;
                hideHandler.sendMessage(message);
            } else {
                message.what = MSG_HIDE_RIGHT;
                hideHandler.sendMessage(message);
            }
        }
    };

    private void animateTo(int beginValue ,int endValue){
        Log.e(TAG, beginValue + " beginValue endValue=== " +endValue );
        ValueAnimator animator = ValueAnimator.ofInt(beginValue, endValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLayoutParams.x = (Integer) animation.getAnimatedValue();
                mWindowManager.updateViewLayout(DragFloatActionButton.this, mLayoutParams);
            }
        });
        animator.start();
    }

}

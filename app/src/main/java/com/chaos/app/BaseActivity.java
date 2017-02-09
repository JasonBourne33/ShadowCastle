package com.chaos.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.chaos.util.UIUtil;
import com.example.administrator.multiplestatusviewtest.R;

import java.util.ArrayList;

/**
 * @author A18ccms a18ccms_gmail_com
 * @ClassName: BaseActivity
 * @Description: TODO(Activity 基類)
 * @date 2014-8-28 下午1:51:45
 */
public class BaseActivity extends Activity implements OnClickListener {
    public static ArrayList<BackPressHandler> mListeners = new ArrayList<BackPressHandler>();
    public Dialog loadingDialog = null;
//    public User user;
//    private Logger logger = Logger.getLogger(BaseActivity.class);
    protected ImageButton backButton;
    protected Button rightButton;
    protected Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public Resources getResources() {
        // TODO Auto-generated method stub
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
//        user = SharedPreferencesUtil.getUserInfoFromShare(this);
//        if (mListeners.size() > 0)
//            for (BackPressHandler handler : mListeners) {
//                handler.activityOnResume();
//            }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
//        if (mListeners.size() > 0)
//            for (BackPressHandler handler : mListeners) {
//                handler.activityOnPause();
//            }
    }

    @Override
    protected void onDestroy() {
//		loadingDialog = null;
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity
        MyApplication.getInstance().finishActivity(this);

        System.gc();
    }

    public void initTopbar(String title) {
        View view = this.getWindow().getDecorView();
        View linearTopBar = view.findViewById(R.id.topbar);
        backButton = (ImageButton) linearTopBar.findViewById(R.id.back_btn);
        backButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });

        TextView textTitle = (TextView) linearTopBar.findViewById(R.id.title_tv);
        textTitle.setText(title);
        UIUtil.adaptationTopbarHeight(this);
    }

    protected void initRightButton(int resId) {
        View view = this.getWindow().getDecorView();
        rightButton = (Button) view.findViewById(R.id.topbar_skip_btn);
        if (rightButton == null)
            return;
        rightButton.setBackgroundResource(resId);
        rightButton.setText("");
        rightButton.setVisibility(View.VISIBLE);
        rightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setRightButtonListener();
            }
        });
    }

    protected void initRightButton() {
        View view = this.getWindow().getDecorView();
        rightButton = (Button) view.findViewById(R.id.topbar_skip_btn);
        if (rightButton == null)
            return;
        rightButton.setText("跳過");
        rightButton.setVisibility(View.VISIBLE);
        rightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                setRightButtonListener();
            }
        });
    }

    protected void setRightButtonListener() {

    }

    @Override
    public void onClick(View view) {

    }

//    public void initTopbar(int resId) {
//        initTopbar(getResources().getString(resId));
//    }
//
//    public boolean isShouldHideInput(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] leftTop = {0, 0};
//            // 获取输入框当前的location位置
//            v.getLocationInWindow(leftTop);
//            int left = leftTop[0];
//            int top = leftTop[1];
//            int bottom = top + v.getHeight();
//            int right = left + v.getWidth();
//            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
//                // 点击的是输入框区域，保留点击EditText的事件
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (this instanceof ShoppingCartActivity) {
//            return super.dispatchTouchEvent(ev);
//        }
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//
    public static abstract interface BackPressHandler {

        public abstract void activityOnResume();

        public abstract void activityOnPause();

    }
//
//    public ImageButton getBackButton() {
//        return backButton;
//    }
//
//    public void setBackButton(ImageButton backButton) {
//        this.backButton = backButton;
//    }
//
//    public Button getRightButton() {
//        return rightButton;
//    }
//
//    public void setRightButton(Button rightButton) {
//        this.rightButton = rightButton;
//    }
//
//    public Dialog getLoadingDialog() {
//        return loadingDialog;
//    }
//
//    public void setLoadingDialog(Dialog loadingDialog) {
//        this.loadingDialog = loadingDialog;
//    }
}

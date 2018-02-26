package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chaos.dialog.SetHeadPopWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetHeadViewActivity extends AppCompatActivity {

    private static final String TAG = "e";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SetHeadPopWindow setHeadPopWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_head_view);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.btn_showPopWindow)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_showPopWindow:
                Log.e(TAG, "onClick:btn_showPopWindow===  " );
                setBackgroundAlpha(0.5f);

                setHeadPopWindow = new SetHeadPopWindow(this);
                setHeadPopWindow.showAtLocation(toolbar, Gravity.BOTTOM,0,0);
                setHeadPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });
                break;
        }
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param alpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
//    public void setBackgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//        lp.alpha = bgAlpha;
//        this.getWindow().setAttributes(lp);
//    }
    public void setBackgroundAlpha(float alpha){
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = alpha;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
    }
}

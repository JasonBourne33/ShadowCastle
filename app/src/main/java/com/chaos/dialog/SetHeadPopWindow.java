package com.chaos.dialog;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.multiplestatusviewtest.R;

import static com.chaos.util.Common.TAG;

/**
 * Created by chaos on 2018/2/6 0006.
 */

public class SetHeadPopWindow extends PopupWindow implements View.OnClickListener{
    public SetHeadPopWindow(Activity context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindow_head, null);
        TextView tvAbum = (TextView) view.findViewById(R.id.tv_album);
        TextView tvTakePhoto = (TextView) view.findViewById(R.id.tv_takePhoto);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvAbum.setOnClickListener(this);
        tvTakePhoto.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //去掉popWindow 的默认灰色背景
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框

    }

//    @OnClick({R.id.tv_album, R.id.tv_takePhoto, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_album:
                break;
            case R.id.tv_takePhoto:
                break;
            case R.id.tv_cancel:
                Log.e(TAG, "onClick: tv_cancel=== " );
                this.dismiss();
                break;
        }
    }
}

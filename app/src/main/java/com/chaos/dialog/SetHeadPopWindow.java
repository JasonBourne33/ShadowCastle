package com.chaos.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.PopupWindow;

/**
 * Created by chaos on 2018/2/6 0006.
 */

public class SetHeadPopWindow extends PopupWindow {
    public SetHeadPopWindow(Activity context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.)
    }
}

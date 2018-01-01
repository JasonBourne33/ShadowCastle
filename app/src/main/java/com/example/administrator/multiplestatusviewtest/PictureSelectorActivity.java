package com.example.administrator.multiplestatusviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chaos.util.ToastUtils;

import butterknife.OnClick;
import butterknife.ButterKnife;


public class PictureSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selector);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_select})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_select:
                ToastUtils.showToast(this,"666",0);
            break;
        }
    }
}

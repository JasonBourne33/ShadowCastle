package com.example.administrator.multiplestatusviewtest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.multiplestatusviewtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GesturePwdActivity extends AppCompatActivity {


    @Bind(R.id.btn_setPwd)
    Button btnSetPwd;
    @Bind(R.id.btn_confirmPwd)
    Button btnConfirmPwd;
    @Bind(R.id.activity_gesture_pwd)
    LinearLayout activityGesturePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_pwd);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_setPwd,R.id.btn_confirmPwd})
    void OnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_setPwd:
                intent = new Intent(this,GestureSetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_confirmPwd:
                intent = new Intent(this,GestureConfirmPwdActivity.class);
                startActivity(intent);
                break;
        }
    }

}

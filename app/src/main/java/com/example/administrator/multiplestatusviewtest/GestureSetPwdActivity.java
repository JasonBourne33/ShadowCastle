package com.example.administrator.multiplestatusviewtest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chaos.sub.GestureLock;
import com.example.administrator.multiplestatusviewtest.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GestureSetPwdActivity extends AppCompatActivity {

    List<Integer> passList;

    @Bind(R.id.LockView)
    GestureLock lock;
    @Bind(R.id.btn_reset)
    Button btnReset;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.activity_gesture_set_pwd)
    RelativeLayout activityGestureSetPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_set_pwd);
        ButterKnife.bind(this);

        lock.setOnDrawFinishedListener(new GestureLock.OnDrawFinishedListener() {
            @Override
            public boolean OnDrawFinished(List<Integer> passList) {
                if (passList.size() < 3)
                {
                    Toast.makeText(GestureSetPwdActivity.this, "密码不能少于3个点", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else {
                    GestureSetPwdActivity.this.passList = passList;
                    System.out.println("passList==== "+passList);
                    return true;
                }
            }
        });

    }

    @OnClick({R.id.btn_save,R.id.btn_reset})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_save:
                if (passList != null)
                {
                    StringBuilder sb = new StringBuilder();
                    for (Integer i : passList)
                    {
                        sb.append(i);
                    }
                    SharedPreferences sp = GestureSetPwdActivity.this.getSharedPreferences("password", GestureSetPwdActivity.this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("password", sb.toString());
                    editor.commit();

                    System.out.println("saved password=== "+sb.toString());
                    Toast.makeText(GestureSetPwdActivity.this, "保存完成", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_reset:
                lock.resetPoints();
                break;
        }

    }
}

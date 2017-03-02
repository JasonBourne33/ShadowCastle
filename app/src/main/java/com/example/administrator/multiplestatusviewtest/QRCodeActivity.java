package com.example.administrator.multiplestatusviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;

public class QRCodeActivity extends AppCompatActivity {
    @Bind(R.id.btn_scan)
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    @OnClick({R.id.btn_scan})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_scan:

                break;
        }

    }
}

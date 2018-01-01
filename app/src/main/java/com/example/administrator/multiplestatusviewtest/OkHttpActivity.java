package com.example.administrator.multiplestatusviewtest;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity{
    @Bind(R.id.tv_showOkHttpResult)
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

    }



    @OnClick({R.id.btn_sendOkHttp})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_sendOkHttp:
                sendOkHttp();
            break;
        }
    }

    private void sendOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com/")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseStr = response.body().string();
                    System.out.println("responseStr==== "+responseStr);
                    showResponse(responseStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void showResponse(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResult.setText(str);
            }
        });
    }

}

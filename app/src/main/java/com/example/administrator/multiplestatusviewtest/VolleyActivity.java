package com.example.administrator.multiplestatusviewtest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.util.Common;
import com.example.administrator.multiplestatusviewtest.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolleyActivity extends AppCompatActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_password)
    EditText etPwd;
    @Bind(R.id.btnEnter)
    Button btnEnter;
    private int operaType = 2; //登录
    public RequestQueue mQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);

        mQueue = Volley.newRequestQueue(this);

    }

    @OnClick({R.id.btnEnter})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btnEnter:
                VolleyLogin();
                break;
        }
    }

    private void VolleyLogin() {
        String url = "http://120.76.27.117:8080/forecast/login.do";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(VolleyActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(VolleyActivity.this, MainActivity.class);
                startActivity(intent);
                System.out.println("Login success");
//                System.out.println("device_token=== " + device_token);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                int statusCode = 0;
                if (error != null && error.networkResponse != null) {
                    statusCode = error.networkResponse.statusCode;
                }
                Toast.makeText(VolleyActivity.this, "Login fail", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


//                device_token = JPushInterface.getRegistrationID(LoginActivity.this);

                params.put("platform", "android");
                params.put("phone", etPhone.getText().toString());
                params.put("password", etPwd.getText().toString());
                params.put("zone", "86");
                params.put("udid", Common.getDeviceInfo(VolleyActivity.this));
                params.put("lang", Common.getgetLanguage());
                params.put("country", Locale.getDefault().getCountry());
                params.put("cv", Common.getAppVersionName(VolleyActivity.this));
                params.put("sv", Build.VERSION.RELEASE);
                params.put("dt", Build.MODEL);
                params.put("dn", "");
                params.put("deviceToken", "");
//                        params.put("deviceToken", device_token);

                System.out.println("params=== " + params);

                return params;
            }
        };
        mQueue.add(stringRequest);
    }
}

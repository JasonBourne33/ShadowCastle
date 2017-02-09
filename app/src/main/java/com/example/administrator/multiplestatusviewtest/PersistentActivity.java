package com.example.administrator.multiplestatusviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersistentActivity extends AppCompatActivity {

    @Bind(R.id.btn_sharePref)
    Button btnSharePref;
    @Bind(R.id.btn_litePro)
    Button btnLitePro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistent);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_sharePref,R.id.btn_litePro})
    void OnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_sharePref:

                break;
            case R.id.btn_litePro:
                intent = new Intent(this,LiteProActivity.class);
                startActivity(intent);
                break;
        }

    }
}

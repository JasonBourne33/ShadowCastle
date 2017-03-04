package com.example.administrator.multiplestatusviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StartActivityForResultActivity extends AppCompatActivity {

    @Bind(R.id.tv_resultShow)
    TextView tvResultShow;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_for_result);
        ButterKnife.bind(this);



    }

    @OnClick({R.id.btn_next})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_next:
                Intent intent = new Intent();
                intent.setClass(StartActivityForResultActivity.this, ResultActivity.class);
                startActivityForResult(intent, 0);//这里采用startActivityForResult来做跳转，此处的0为一个依据，可以写其他的值，但一定要>=0
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                Bundle b = data.getExtras();  //data为B中回传的Intent
                String str = b.getString("resultContent");//str即为回传的值
                tvResultShow.setText(str);
                break;
        }
    }
}

package com.example.administrator.multiplestatusviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nostra13.universalimageloader.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.btn_commit)
    Button btnCommit;
    String content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_commit})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_commit:
                content = etContent.getText().toString();
                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("resultContent",content);
                i.putExtras(bundle);
                L.i("content=== "+content);
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }
}

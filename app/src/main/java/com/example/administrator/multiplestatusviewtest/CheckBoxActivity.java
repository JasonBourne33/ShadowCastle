package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.multiplestatusviewtest.R;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckBoxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    String str;
    Button btnEnter;

    @Bind(R.id.cb_milk)
    CheckBox cbMilk;
    @Bind(R.id.cb_apple)
    CheckBox cbApple;
    @Bind(R.id.cb_chicken)
    CheckBox cbChicken;
    @Bind(R.id.cb_frenchFries)
    CheckBox cbFrenchFries;
    @Bind(R.id.tv_note)
    TextView tvNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        ButterKnife.bind(this);

        cbMilk.setOnCheckedChangeListener(this);
        cbApple.setOnCheckedChangeListener(this);
        cbChicken.setOnCheckedChangeListener(this);
        cbFrenchFries.setOnCheckedChangeListener(this);


        btnEnter = (Button) findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckBoxActivity.this,"str=== "+str,Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        str = "Your favourite";

        if(cbMilk.isChecked()){
            str += cbMilk.getText()+",";
        }
        if(cbApple.isChecked()){
            str += cbApple.getText()+",";
        }
        if(cbChicken.isChecked()){
            str += cbChicken.getText()+",";
        }
        if(cbFrenchFries.isChecked()){
            str += cbFrenchFries.getText();
        }
        tvNote.setText(str);
    }


//    @OnClick({R.id.btn_checkBox})
//    void OnClick(View v){
//        switch (v.getId()){
//            case R.id.btn_checkBox:
//                Toast.makeText(this,"str=== "+str,Toast.LENGTH_LONG).show();
//                break;
//        }
//    }

}

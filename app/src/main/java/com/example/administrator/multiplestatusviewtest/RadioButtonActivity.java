package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.multiplestatusviewtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RadioButtonActivity extends AppCompatActivity {

    @Bind(R.id.rb_sex_male)
    RadioButton rbSexMale;
    @Bind(R.id.rb_sex_female)
    RadioButton rbSexFemale;
    @Bind(R.id.rg_sex)
    RadioGroup rgSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        ButterKnife.bind(this);
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                System.out.println("checkedId==== "+checkedId);
            }
        });
    }

    @OnClick({R.id.btn_radioEnter})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_radioEnter:
                if(rbSexMale.getId() == rgSex.getCheckedRadioButtonId()){
                    System.out.println("Male==== ");
                }else if(rbSexFemale.getId() == rgSex.getCheckedRadioButtonId()){
                    System.out.println("Female==== ");
                }
                break;
        }
    }

}

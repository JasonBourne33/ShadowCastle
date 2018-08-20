package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chaos.dialog.DragFloatActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingButtonActivity extends AppCompatActivity {

    @Bind(R.id.dfab)
    DragFloatActionButton dfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_button);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dfab)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.dfab:
                Toast.makeText(this,"666",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

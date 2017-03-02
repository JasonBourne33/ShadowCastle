package com.example.administrator.multiplestatusviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chaos.service.AlarmService;

public class AlarmServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_service);

        Intent i = new Intent(this,AlarmService.class);
        startService(i);

    }
}

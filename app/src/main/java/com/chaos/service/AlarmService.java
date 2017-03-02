package com.chaos.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.LayoutInflater;

import com.example.administrator.multiplestatusviewtest.AlarmServiceActivity;
import com.nostra13.universalimageloader.utils.L;

public class AlarmService extends Service {
    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                //这里写要执行的代码
                L.i("onAlarmService===");
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int tempTime = 3 * 1000;
        int anHour = 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+tempTime;
        Intent i =  new Intent(this,AlarmService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);


        return super.onStartCommand(intent, flags, startId);
    }
}

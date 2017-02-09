package com.example.administrator.multiplestatusviewtest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LeakCanaryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

        Button button = (Button)findViewById(R.id.btn_sLeakCanary);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ttttttttttttt");
                start();
            }
        });
    }

//    @OnClick({R.id.btn_start})
//    void OnClick(View v){
//        switch (v.getId()){
//            case R.id.btn_start:
//                Log.i("start?","tttttttt");
//                System.out.println("ttttttttttttt");
//                start();
//                break;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void start(){
        //这个Thread是一个匿名内部类，因此它隐式的持有一个外部类
        //的对象，也就是MainActivity。如果MainActivity在Thread
        //执行完成前就销毁了，这个activity实例就发生了泄露。
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(20000);
            }
        }).start();

    }
}

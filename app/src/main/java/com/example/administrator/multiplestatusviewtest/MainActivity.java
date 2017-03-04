package com.example.administrator.multiplestatusviewtest;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fragment.FragmentController;
import com.example.fragment.MainFoundFragment;
import com.example.fragment.MainMessageFragment;
import com.example.fragment.MainPagerFragment;
import com.example.fragment.MainPersonalFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.mainpager_framelay)
    FrameLayout mainpagerFramelay;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.activity_main)
    DrawerLayout activityMain;

    private FragmentManager fragmentManager;
    private Fragment mainpagerFragment, mainmessageFragment, mainpersonalFragment, maincolletFragment;//mainVipFragment,
    private FragmentController controller;

    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //用FragmentController
        fragments = new ArrayList<Fragment>();
        fragments.add(new MainPagerFragment());
        fragments.add(new MainMessageFragment());
        fragments.add(new MainFoundFragment());
        fragments.add(new MainPersonalFragment());
        controller = new FragmentController(this, R.id.mainpager_framelay, fragments);

        //这两句只显示第一个主页
        controller.hideFragments();
        controller.showFragment(0);

//        fragmentManager = getFragmentManager();
//        setFragment();

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                System.out.println("item=== "+item.getItemId());
                switch (item.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this,"call",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this,"friends",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this,"mail",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this,"task",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_view:
                        Toast.makeText(MainActivity.this,"view",Toast.LENGTH_SHORT).show();
                        break;
                }
                activityMain.closeDrawers();
                return true;
            }
        });

    }


    @OnClick({R.id.main_pager_layout, R.id.main_collect_layout, R.id.main_message_layout, R.id.main_personal_layout, R.id.iv_add})
    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.main_pager_layout:
                controller.showFragment(0);
                break;
            case R.id.main_collect_layout:
                controller.showFragment(1);
                break;
            case R.id.main_message_layout:
                controller.showFragment(2);
                break;
            case R.id.main_personal_layout:
                controller.showFragment(3);
                break;
            case R.id.iv_add:
                Toast.makeText(this, "add Listener", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mainpagerFragment = new MainPagerFragment();
        transaction.add(R.id.mainpager_framelay, mainpagerFragment);
        transaction.show(mainpagerFragment);
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mainpagerFragment != null) {
            transaction.hide(mainpagerFragment);
        }
        if (maincolletFragment != null) {
            transaction.hide(maincolletFragment);
        }
//        if (mainVipFragment != null) {
//            transaction.hide(mainVipFragment);
//        }
        if (mainmessageFragment != null) {
            transaction.hide(mainmessageFragment);
        }
        if (mainpersonalFragment != null) {
            transaction.hide(mainpersonalFragment);
        }

    }

}

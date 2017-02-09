package com.example.fragment;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Administrator on 2016/12/26.
 */

public class FragmentController {
    private int containerId;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    private static FragmentController controller;

//    public static FragmentController getInstance(FragmentActivity activity, int containerId) {
//        //没有controller 对象就new，有就用
//        if (controller == null) {
//            controller = new FragmentController(activity, containerId);
//        }
//
//        return controller;
//    }

    public static void onDestroy() {
        controller = null;
    }

    //本来是 private
    public FragmentController(FragmentActivity activity, int containerId,ArrayList<Fragment> ListFragment) {
        this.containerId = containerId;
        this.fragments = ListFragment;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
//        fragments = new ArrayList<Fragment>();
//        fragments.add(new HomeFragment());
//        fragments.add(new MessageFragment());
//        fragments.add(new SearchFragment());
//        fragments.add(new UserFragment());

        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commit();
    }

    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commit();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            if(fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}

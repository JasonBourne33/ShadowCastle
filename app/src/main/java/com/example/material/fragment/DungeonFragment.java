package com.example.material.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.multiplestatusviewtest.AlarmServiceActivity;
import com.example.administrator.multiplestatusviewtest.EventBusActivity;
import com.example.administrator.multiplestatusviewtest.LeakCanaryActivity;
import com.example.administrator.multiplestatusviewtest.MenuActivity;
import com.example.administrator.multiplestatusviewtest.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DungeonFragment extends Fragment {
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dungeon, container, false);
        ButterKnife.bind(this,view);
        mContext = getContext();
        return view;
    }

    @OnClick({R.id.btn_leakCanary,R.id.btn_Menu,R.id.btn_AlarmService,R.id.btn_QRCode,R.id.btn_eventBus})
    void OnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_leakCanary:
                intent = new Intent(mContext, LeakCanaryActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Menu:
                intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_AlarmService:
                intent = new Intent(mContext, AlarmServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_QRCode:
                intent = new Intent(mContext, AlarmServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eventBus:
                intent = new Intent(mContext, EventBusActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText(getArguments().getString("ARGS"));
    }

    public static DungeonFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        DungeonFragment fragment = new DungeonFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

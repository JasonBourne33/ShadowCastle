package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.util.TitleBuilder;
import com.chaos.util.ToastUtils;
import com.example.administrator.multiplestatusviewtest.BottomNavigationActivity;
import com.example.administrator.multiplestatusviewtest.CheckBoxActivity;
import com.example.administrator.multiplestatusviewtest.R;
import com.example.administrator.multiplestatusviewtest.RadioButtonActivity;
import com.example.administrator.multiplestatusviewtest.StartActivityForResultActivity;

/**
 * Created by Administrator on 2016/12/30.
 */

public class MainMessageFragment extends BaseFragment implements View.OnClickListener{

    private Context mContext;

    public MainMessageFragment() {
        // Required empty public constructor
    }

    public static MainMessageFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MainMessageFragment fragment = new MainMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_main_collect, container, false);
        new TitleBuilder(view)
                .setTitleText("Message")
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "right click~", Toast.LENGTH_SHORT);
                    }
                });

        Button btnRb = (Button) view.findViewById(R.id.btn_radioButton);
        Button btnCb = (Button) view.findViewById(R.id.btn_checkBox);
        Button btnForResult = (Button) view.findViewById(R.id.btn_forResult);
        Button btnBottomNavigation = (Button) view.findViewById(R.id.btn_bottomNavigation);
        btnRb.setOnClickListener(this);
        btnCb.setOnClickListener(this);
        btnForResult.setOnClickListener(this);
        btnBottomNavigation.setOnClickListener(this);
        return view;


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_radioButton:
                intent = new Intent(mContext,RadioButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_checkBox:
                intent = new Intent(mContext,CheckBoxActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_forResult:
                intent = new Intent(mContext, StartActivityForResultActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_bottomNavigation:
                intent = new Intent(mContext, BottomNavigationActivity.class);
                startActivity(intent);
                break;
        }
    }
}

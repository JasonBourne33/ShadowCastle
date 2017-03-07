package com.example.fragment;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.util.TitleBuilder;
import com.chaos.util.ToastUtils;
import com.example.administrator.multiplestatusviewtest.AlarmServiceActivity;
import com.example.administrator.multiplestatusviewtest.LeakCanaryActivity;
import com.example.administrator.multiplestatusviewtest.MenuActivity;
import com.example.administrator.multiplestatusviewtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFoundFragment extends BaseFragment implements View.OnClickListener{

    private Context mContext;

    public MainFoundFragment() {
        // Required empty public constructor
    }

    public static MainFoundFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MainFoundFragment fragment = new MainFoundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_main_message, container, false);

        Button btnLeakCanary = (Button) view.findViewById(R.id.btn_leakCanary);
        Button btnMenu = (Button) view.findViewById(R.id.btn_Menu);
        Button btnAlarmService = (Button) view.findViewById(R.id.btn_AlarmService);
        Button btnQRCode = (Button) view.findViewById(R.id.btn_QRCode);
        btnLeakCanary.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
        btnAlarmService.setOnClickListener(this);
        btnQRCode.setOnClickListener(this);

        new TitleBuilder(view)
                .setTitleText("HomePage")
                .setRightImage(R.drawable.ic_launcher)
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "right click~", Toast.LENGTH_SHORT);
                    }
                });
        return view;
    }

    @Override
    public void onClick(View v) {
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
        }
    }
}

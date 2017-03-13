package com.example.material.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.multiplestatusviewtest.BottomNavigationActivity;
import com.example.administrator.multiplestatusviewtest.CheckBoxActivity;
import com.example.administrator.multiplestatusviewtest.R;
import com.example.administrator.multiplestatusviewtest.RadioButtonActivity;
import com.example.administrator.multiplestatusviewtest.StartActivityForResultActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment {
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contral, container, false);
        ButterKnife.bind(this,view);
        mContext = getContext();
        return view;
    }

    @OnClick({R.id.btn_radioButton,R.id.btn_checkBox,R.id.btn_forResult,R.id.btn_bottomNavigation})
    void OnClick(View v){
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText(getArguments().getString("ARGS"));
    }

    public static ControlFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

}

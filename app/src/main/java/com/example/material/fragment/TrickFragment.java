package com.example.material.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.multiplestatusviewtest.AdvertisingActivity;
import com.example.administrator.multiplestatusviewtest.BannerActivity;
import com.example.administrator.multiplestatusviewtest.GesturePwdActivity;
import com.example.administrator.multiplestatusviewtest.MaterialActivity;
import com.example.administrator.multiplestatusviewtest.MultipleStatusActivity;
import com.example.administrator.multiplestatusviewtest.PersistentActivity;
import com.example.administrator.multiplestatusviewtest.PullLoadMoreRecyclerActivity;
import com.example.administrator.multiplestatusviewtest.R;
import com.example.administrator.multiplestatusviewtest.RecyclerViewActivity;
import com.example.administrator.multiplestatusviewtest.VolleyActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrickFragment extends Fragment {
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trick, container, false);
        ButterKnife.bind(this, view);
        mContext = getContext();
        return view;
    }

    @OnClick({R.id.btn_MultiStatus, R.id.btn_GesturePwd, R.id.btn_Volley, R.id.btn_advertising, R.id.btn_banner, R.id.btn_recycler, R.id.btn_pullRecycler, R.id.btn_persistentData, R.id.btn_Material})
    void OnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_MultiStatus:
                intent = new Intent(mContext, MultipleStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_GesturePwd:
                intent = new Intent(mContext, GesturePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Volley:
                intent = new Intent(mContext, VolleyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_advertising:
                intent = new Intent(mContext, AdvertisingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_banner:
                intent = new Intent(mContext, BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler:
                intent = new Intent(mContext, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pullRecycler:
                intent = new Intent(mContext, PullLoadMoreRecyclerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_persistentData:
                intent = new Intent(mContext, PersistentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Material:
                intent = new Intent(mContext, MaterialActivity.class);
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

    public static TrickFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        TrickFragment fragment = new TrickFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

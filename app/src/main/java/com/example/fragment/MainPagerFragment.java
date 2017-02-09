package com.example.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chaos.adapter.PullRecyclerViewAdapter;
import com.chaos.util.TitleBuilder;
import com.example.administrator.multiplestatusviewtest.AdvertisingActivity;
import com.example.administrator.multiplestatusviewtest.BannerActivity;
import com.example.administrator.multiplestatusviewtest.GesturePwdActivity;
import com.example.administrator.multiplestatusviewtest.MaterialActivity;
import com.example.administrator.multiplestatusviewtest.MultipleStatusActivity;
import com.example.administrator.multiplestatusviewtest.PersistentActivity;
import com.example.administrator.multiplestatusviewtest.PullLoadMoreRecyclerActivity;
import com.example.administrator.multiplestatusviewtest.RecyclerViewActivity;
import com.example.administrator.multiplestatusviewtest.VolleyActivity;
import com.example.administrator.multiplestatusviewtest.R;

import butterknife.ButterKnife;

/**
 * 主页Fragment
 */
public class MainPagerFragment extends BaseFragment implements View.OnClickListener{
    private Context mContext;
    public MainPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_main_pager, container, false);
        ButterKnife.bind(view);

        Button btnMultiSatus = (Button) view.findViewById(R.id.btn_MultiStatus);
        Button btnGesture = (Button) view.findViewById(R.id.btn_GesturePwd);
        Button btnVolley = (Button) view.findViewById(R.id.btn_Volley);
        Button btnAdvertising = (Button) view.findViewById(R.id.btn_advertising);
        Button btnRecycler = (Button) view.findViewById(R.id.btn_recycler);
        Button btnPullRecycler = (Button) view.findViewById(R.id.btn_pullRecycler);
        Button btnBanner = (Button) view.findViewById(R.id.btn_banner);
        Button btnPersitentData = (Button) view.findViewById(R.id.btn_persistentData);
        Button btnMaterial = (Button) view.findViewById(R.id.btn_Material);

        btnMultiSatus.setOnClickListener(this);
        btnGesture.setOnClickListener(this);
        btnVolley.setOnClickListener(this);
        btnAdvertising.setOnClickListener(this);
        btnRecycler.setOnClickListener(this);
        btnPullRecycler.setOnClickListener(this);
        btnBanner.setOnClickListener(this);
        btnPersitentData.setOnClickListener(this);
        btnMaterial.setOnClickListener(this);

        new TitleBuilder(view)
                .setTitleText("HomePage");
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_MultiStatus:
                intent = new Intent(mContext,MultipleStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_GesturePwd:
                intent = new Intent(mContext,GesturePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Volley:
                intent = new Intent(mContext,VolleyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_advertising:
                intent = new Intent(mContext,AdvertisingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_banner:
                intent = new Intent(mContext,BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler:
                intent = new Intent(mContext,RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pullRecycler:
                intent = new Intent(mContext,PullLoadMoreRecyclerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_persistentData:
                intent = new Intent(mContext,PersistentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Material:
                intent = new Intent(mContext,MaterialActivity.class);
                startActivity(intent);
                break;
        }
    }
}

package com.example.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chaos.util.TitleBuilder;
import com.chaos.util.ToastUtils;
import com.example.administrator.multiplestatusviewtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPersonalFragment extends BaseFragment {


    public MainPersonalFragment() {
        // Required empty public constructor
    }

    public static MainPersonalFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MainPersonalFragment fragment = new MainPersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_personal, container, false);

        new TitleBuilder(view)
                .setTitleText("Personal")
                .setRightImage(R.drawable.ic_launcher)
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "right click~", Toast.LENGTH_SHORT);
                    }
                });
        return view;
    }

}

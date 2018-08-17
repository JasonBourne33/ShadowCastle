package com.example.administrator.multiplestatusviewtest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaos.adapter.ViewPagerAdapter;
import com.example.fragment.RegistByAccountFragment;
import com.example.fragment.RegistByPhoneFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<Fragment> listFragment;
    private String[] titles = {"手机/邮箱注册", "普通账号注册"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        tabLayout = (TabLayout) findViewById(R.id.tab_mine_pay);
        viewPager = (ViewPager) findViewById(R.id.vp_mine_pay);

        listFragment = new ArrayList<>();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);
        for (int i = 0; i < titles.length; i++) {
            View view = View.inflate(getBaseContext(), R.layout.item_tab_view, null);
            TextView tvTabs = (TextView) view.findViewById(R.id.tv_tab_item_title);
            tvTabs.setText(titles[i]);
            tvTabs.setTextSize(16);
            tabLayout.addTab(tabLayout.newTab().setCustomView(view));
        }
        Fragment registByPhoneFragment = new RegistByPhoneFragment();
        Fragment registByAccountAccount = new RegistByAccountFragment();
        listFragment.add(registByPhoneFragment);
        listFragment.add(registByAccountAccount);
        viewPager.setAdapter(adapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("tvQuestion onclick=== ");
            }
        });


//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabQuestion) {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                if (mSlideBackLayout != null) {
//                    if (position == 0) {
//                        mSlideBackLayout.edgeOnly(false);
//                    } else {
//                        mSlideBackLayout.edgeOnly(true);
//                    }
//                }
//            }
//        });
    }


}

package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultipleStatusActivity extends AppCompatActivity {


    @Bind(R.id.main_multiplestatusview)
    MultipleStatusView mainMultiplestatusview;
    @Bind(R.id.main_fab_loading)
    FloatingActionButton mainFabLoading;
    @Bind(R.id.main_fab_empty)
    FloatingActionButton mainFabEmpty;
    @Bind(R.id.main_fab_error)
    FloatingActionButton mainFabError;
    @Bind(R.id.main_fab_no_network)
    FloatingActionButton mainFabNoNetwork;
    @Bind(R.id.main_fab_content)
    FloatingActionButton mainFabContent;
    @Bind(R.id.main_fab_menu)
    FloatingActionMenu mainFabMenu;
    @Bind(R.id.activity_multiple_status)
    RelativeLayout activityMultipleStatus;
    private FloatingActionMenu mFloatingActionMenu;


    private MultipleStatusView mMultipleStatusView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_status);
        ButterKnife.bind(MultipleStatusActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //在设置有标题栏的时候把这行注释掉，否则崩溃
        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.main_multiplestatusview);
        mFloatingActionMenu = (FloatingActionMenu) findViewById(R.id.main_fab_menu);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.top_back);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.main_fab_loading, R.id.main_fab_empty, R.id.main_fab_error, R.id.main_fab_no_network, R.id.main_fab_content})
    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.main_fab_loading:
                mMultipleStatusView.showLoading();
                break;
            case R.id.main_fab_empty:
                mMultipleStatusView.showEmpty();
                break;
            case R.id.main_fab_error:
                mMultipleStatusView.showError();
                break;
            case R.id.main_fab_no_network:
                mMultipleStatusView.showNoNetwork();
                break;
            case R.id.main_fab_content:
                mMultipleStatusView.showContent();
                break;
        }
        mFloatingActionMenu.toggle(false);
    }
}

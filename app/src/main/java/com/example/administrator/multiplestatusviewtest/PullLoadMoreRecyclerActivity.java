package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaos.adapter.PullRecyclerViewAdapter;
import com.chaos.adapter.RecyclerViewAdapter;
import com.chaos.bean.Fruit;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PullLoadMoreRecyclerActivity extends AppCompatActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener{

    private static final String TAG = "tag";
    private PullRecyclerViewAdapter mPullRecyclerViewAdapter;
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private RecyclerView mRecyclerView;

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_load_more_recycler);
        ButterKnife.bind(this);
        initFruits();

        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");

        mPullLoadMoreRecyclerView.setLinearLayout();  //
//        mPullLoadMoreRecyclerView.setGridLayout(2); //网格，2列
//        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);//参数为列数
        mPullRecyclerViewAdapter = new PullRecyclerViewAdapter(this);
        mPullLoadMoreRecyclerView.setAdapter(mPullRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        getData();

    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: fruitList=== "+fruitList );
                        mPullRecyclerViewAdapter.addAllData(fruitList);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        Log.e("wxl", "onRefresh");
        mPullRecyclerViewAdapter.clearData();
        getData();
        mPullRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        Log.e("wxl", "onLoadMore");
        getData();
        mPullRecyclerViewAdapter.notifyDataSetChanged();
    }


}

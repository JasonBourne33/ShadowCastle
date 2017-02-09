package com.chaos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaos.bean.Fruit;
import com.example.administrator.multiplestatusviewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuXiaolong
 * on 2015/7/2.
 */
public class PullRecyclerViewAdapter extends RecyclerView.Adapter<PullRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> dataList = new ArrayList<>();
    private ImageView imgFruit;
    private TextView tvName;


    public void addAllData(List<Fruit> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public PullRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
                imgFruit = (ImageView) view.findViewById(R.id.fruit_image);
                tvName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Fruit fruit = dataList.get(position);
        tvName.setText(fruit.getName());
        imgFruit.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
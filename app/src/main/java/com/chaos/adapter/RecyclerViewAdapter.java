package com.chaos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaos.bean.Fruit;
import com.example.administrator.multiplestatusviewtest.R;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    // 数据集
    private List<Fruit> mFruit;

    public RecyclerViewAdapter(List<Fruit> fruit) {
        super();
        this.mFruit = fruit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.item_fruit, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public ImageView imgFruit;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_fruitName);
            imgFruit = (ImageView) view.findViewById(R.id.img_fruit);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // 绑定数据到ViewHolder上
        viewHolder.tvName.setText(mFruit.get(i).getName());
        viewHolder.imgFruit.setImageResource(mFruit.get(i).getImageId());
    }

    @Override
    public int getItemCount() {
        return mFruit.size();
    }


}

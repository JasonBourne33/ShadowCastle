package com.chaos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.bean.Fruit;
import com.example.administrator.multiplestatusviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class PullRecyclerViewAdapter extends RecyclerView.Adapter<PullRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> dataList = new ArrayList<>();
    private int selectedPos;
    private Fruit fruit;
    int position;

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

        View fruitView;
        ImageView imgFruit;
        TextView tvName;
        public ViewHolder(View view) {
            super(view);
                fruitView = view;
                imgFruit = (ImageView) view.findViewById(R.id.fruit_image);
                tvName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(v);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                selectedPos = position;
                Toast.makeText(mContext,"position=== "+position,Toast.LENGTH_SHORT).show();
//                if(position == selectedPos){
//                    holder.tvName.setText("selected");
//                }
//                holder.tvName.setText(" fruit 6666666666666 pasition=== "+position);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        this.position = position;

        fruit = dataList.get(position);
        holder.tvName.setText(fruit.getName());
        holder.imgFruit.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
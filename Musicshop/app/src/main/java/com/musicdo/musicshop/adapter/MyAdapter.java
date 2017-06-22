package com.musicdo.musicshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.musicdo.musicshop.R;

import java.util.List;

/**
 * 名 称 ：
 * 描 述 ：
 * 创建者：  张海明
 * 创建时间：2017/6/22.
 * 版 本 ：
 * 备 注 ：
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    /**
     * 数据
     */
    private List<Object> mDatas;

    public MyAdapter(List<Object> datas) {
        this.mDatas = datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "good", Toast.LENGTH_SHORT).show();
            }
        });
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

package com.luanzera.feedreaderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koushikdutta.ion.Ion;
import com.luanzera.feedreaderapp.Adapter.ViewHolder.ItemViewHolder;
import com.luanzera.feedreaderapp.Common.Common;
import com.luanzera.feedreaderapp.Interface.IItemClickListener;
import com.luanzera.feedreaderapp.Model.Item;
import com.luanzera.feedreaderapp.R;
import com.luanzera.feedreaderapp.WebActivity;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    Context context;
    List<Item> itemList;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.txt_title.setText(itemList.get(position).getTitle());
        holder.txt_pubDate.setText(new StringBuilder("Postado em ").append(Common.pubDateFormatter(itemList.get(position).getPubDate())).toString());
        holder.txt_author.setText(new StringBuilder("Por ").append(itemList.get(position).getAuthor()).toString());Ion.with(context).load(itemList.get(position).getEnclosure().getLink()).withBitmap().intoImageView(holder.img_feed);
        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view) {
                Common.currentItem = itemList.get(position);
                context.startActivity(new Intent(context, WebActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

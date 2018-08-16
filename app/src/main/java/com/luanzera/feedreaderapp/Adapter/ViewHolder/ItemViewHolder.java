package com.luanzera.feedreaderapp.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luanzera.feedreaderapp.Interface.IItemClickListener;
import com.luanzera.feedreaderapp.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView img_feed;
    public TextView txt_title, txt_pubDate, txt_author;

    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemViewHolder(View itemView) {
        super(itemView);

        img_feed = itemView.findViewById(R.id.img_feed);
        txt_title = itemView.findViewById(R.id.txt_title);
        txt_pubDate = itemView.findViewById(R.id.txt_pubDate);
        txt_author = itemView.findViewById(R.id.txt_author);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }

}

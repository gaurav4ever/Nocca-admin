package com.bookpurple.pp1.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bookpurple.pp1.mvp.PanDetails;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public abstract class PanListingViewHolder<T extends PanDetails> extends RecyclerView.ViewHolder {

    public PanListingViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T item, int position);
}

package com.bookpurple.pp1.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bookpurple.pp1.mvp.DeviceDetails;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public abstract class DeviceListingViewHolder<T extends DeviceDetails> extends RecyclerView.ViewHolder {

    public DeviceListingViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T item, int position);
}

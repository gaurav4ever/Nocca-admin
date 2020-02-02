package com.bookpurple.pp1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookpurple.pp1.R;
import com.bookpurple.pp1.adapter.viewholder.PanListingItemViewHolder;
import com.bookpurple.pp1.adapter.viewholder.PanListingViewHolder;
import com.bookpurple.pp1.mvp.PanDetails;
import com.bookpurple.pp1.publishsubject.PanClickedItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class PanListingAdapter extends RecyclerView.Adapter<PanListingViewHolder> {

    private Context context;
    private CompositeDisposable compositeDisposable;
    private List<PanDetails> panDetailResponses;
    private LayoutInflater layoutInflater;
    private PublishSubject<PanClickedItem> panClickedItemPublishSubject;

    public PanListingAdapter(Context context,
                             CompositeDisposable lifecycle) {
        this.context = context;
        this.compositeDisposable = lifecycle;
        this.panDetailResponses = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
        this.panClickedItemPublishSubject = PublishSubject.create();
    }

    public void setData(List<PanDetails> panDetailResponses) {
        this.panDetailResponses.clear();
        this.panDetailResponses.addAll(panDetailResponses);
        this.notifyDataSetChanged();
        //this.notifyItemRangeChanged(0, this.panDetailResponses.size());
    }

    @NonNull
    @Override
    public PanListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = layoutInflater.inflate(R.layout.listing_pan_item, parent, false);
        return new PanListingItemViewHolder(context, itemView, panClickedItemPublishSubject);
    }

    @Override
    public void onBindViewHolder(@NonNull PanListingViewHolder holder, int position) {
        holder.bindData(panDetailResponses.get(position), position);
    }

    @Override
    public int getItemCount() {
        return this.panDetailResponses.size();
    }

    public PublishSubject<PanClickedItem> getVendorClickedItemPublishSubject() {
        return this.panClickedItemPublishSubject;
    }
}

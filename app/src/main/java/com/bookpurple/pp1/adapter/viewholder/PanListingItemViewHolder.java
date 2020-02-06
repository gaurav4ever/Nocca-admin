package com.bookpurple.pp1.adapter.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookpurple.pp1.R;
import com.bookpurple.pp1.logger.Logger;
import com.bookpurple.pp1.mvp.PanDetails;
import com.bookpurple.pp1.publishsubject.PanClickedItem;
import com.bookpurple.pp1.util.rx.RxViewUtil;

import io.reactivex.subjects.PublishSubject;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class PanListingItemViewHolder extends PanListingViewHolder<PanDetails> {

    private Context context;
    private View view;
    private PublishSubject<PanClickedItem> panClickedItemPublishSubject;

    private TextView panTextView;

    public PanListingItemViewHolder(Context context,
                                    View itemView,
                                    PublishSubject<PanClickedItem> panClickedItemPublishSubject) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        this.panClickedItemPublishSubject = panClickedItemPublishSubject;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        panTextView = itemView.findViewById(R.id.pan);
    }

    @Override
    public void bindData(PanDetails item, int position) {
        panTextView.setText(item.panNumber);

        RxViewUtil.click(panTextView)
                .subscribe(aVoid -> {
                    final PanClickedItem panClickedItem = new PanClickedItem();
                    panClickedItem.panNumber = item.panNumber;
                    panClickedItemPublishSubject.onNext(panClickedItem);
                }, throwable -> Logger.logException(throwable));
    }
}

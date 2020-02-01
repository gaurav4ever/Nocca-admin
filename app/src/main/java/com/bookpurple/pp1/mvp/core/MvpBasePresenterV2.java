package com.bookpurple.pp1.mvp.core;

import java.lang.ref.WeakReference;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public class MvpBasePresenterV2<V> {

    private WeakReference<V> weakRef;

    protected void attachView(V view) {
        weakRef = new WeakReference<>(view);
    }

    protected boolean isViewAttached() {
        return weakRef != null && weakRef.get() != null;
    }

    protected V getView() {
        return weakRef.get();
    }
}

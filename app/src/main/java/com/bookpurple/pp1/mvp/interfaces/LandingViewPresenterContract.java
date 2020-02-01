package com.bookpurple.pp1.mvp.interfaces;

import com.bookpurple.pp1.mvp.core.MvpBasePresenterV2;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public interface LandingViewPresenterContract {

    interface View {

        void loadData();

        void dataFetchFailure(Throwable error);

    }

    abstract class Presenter extends MvpBasePresenterV2 {

    }
}

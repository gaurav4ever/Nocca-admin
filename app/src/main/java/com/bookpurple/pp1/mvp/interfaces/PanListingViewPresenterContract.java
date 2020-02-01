package com.bookpurple.pp1.mvp.interfaces;

import com.bookpurple.pp1.mvp.PanDetailResponse;
import com.bookpurple.pp1.mvp.UserDetailsRequest;
import com.bookpurple.pp1.mvp.core.MvpBasePresenterV2;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public interface PanListingViewPresenterContract {

    interface View {

        void onListingDataFetched(PanDetailResponse panDetailResponse);

        /**
         * Function to load the data from backend
         */
        void loadData();

        void dataFetchFailure(Throwable error);
    }

    abstract class Presenter extends MvpBasePresenterV2<View> {

        public abstract void getListingData(UserDetailsRequest userDetailsRequest);

        public abstract boolean isNoContent(PanDetailResponse panDetailResponse);

    }
}

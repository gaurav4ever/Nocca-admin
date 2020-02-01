package com.bookpurple.pp1.mvp.interfaces;

import com.bookpurple.pp1.mvp.DeviceRequestModel;
import com.bookpurple.pp1.mvp.DeviceResponseModel;
import com.bookpurple.pp1.mvp.UpdateDeviceStatusRequest;
import com.bookpurple.pp1.mvp.core.MvpBasePresenterV2;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public interface DeviceListingViewPresenterContract {

    interface View {

        void onListingDataFetched(DeviceResponseModel deviceResponseModel);

        /**
         * Function to load the data from backend
         */
        void loadData();

        void dataFetchFailure(Throwable error);
    }

    abstract class Presenter extends MvpBasePresenterV2<DeviceListingViewPresenterContract.View> {

        public abstract void getListingData(DeviceRequestModel deviceRequestModel);

        public abstract void updateDeviceStatus(UpdateDeviceStatusRequest updateDeviceStatusRequest);

        public abstract boolean isNoContent(DeviceResponseModel deviceResponseModel);

    }
}

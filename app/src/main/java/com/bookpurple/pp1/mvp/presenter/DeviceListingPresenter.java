package com.bookpurple.pp1.mvp.presenter;

import com.bookpurple.pp1.logger.Logger;
import com.bookpurple.pp1.mvp.DeviceRequestModel;
import com.bookpurple.pp1.mvp.DeviceResponseModel;
import com.bookpurple.pp1.mvp.UpdateDeviceStatusRequest;
import com.bookpurple.pp1.mvp.interactor.DeviceListingInteractor;
import com.bookpurple.pp1.mvp.interfaces.DeviceListingViewPresenterContract;
import com.bookpurple.pp1.util.rx.RxSchedulersAbstractBase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class DeviceListingPresenter extends DeviceListingViewPresenterContract.Presenter {

    private static final String TAG = DeviceListingPresenter.class.getSimpleName();

    private DeviceListingInteractor listingInteractor;
    private RxSchedulersAbstractBase rxSchedulers;
    private CompositeDisposable compositeDisposable;

    public DeviceListingPresenter(DeviceListingViewPresenterContract.View view,
                                  DeviceListingInteractor interactor,
                                  RxSchedulersAbstractBase rxSchedulers,
                                  CompositeDisposable compositeDisposable) {
        attachView(view);
        this.listingInteractor = interactor;
        this.rxSchedulers = rxSchedulers;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getListingData(DeviceRequestModel deviceRequestModel) {
        Disposable disposable = listingInteractor.getDeviceDetailResponse(deviceRequestModel)
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(deviceResponseModel -> {
                    if (isViewAttached()) {
                        if (isNoContent(deviceResponseModel)) {
                            getView().dataFetchFailure(new Throwable("Data is Null"));
                        } else {
                            getView().onListingDataFetched(deviceResponseModel);
                        }
                    }
                }, throwable -> Logger.logException(TAG, throwable));

        compositeDisposable.add(disposable);
    }

    @Override
    public void updateDeviceStatus(UpdateDeviceStatusRequest updateDeviceStatusRequest) {
        listingInteractor.updateDeviceStatus(updateDeviceStatusRequest);
    }

    @Override
    public boolean isNoContent(DeviceResponseModel deviceResponseModel) {
        return null == deviceResponseModel;
    }
}

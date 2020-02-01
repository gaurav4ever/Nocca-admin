package com.bookpurple.pp1.mvp.presenter;

import com.bookpurple.pp1.logger.Logger;
import com.bookpurple.pp1.mvp.PanDetailResponse;
import com.bookpurple.pp1.mvp.UserDetailsRequest;
import com.bookpurple.pp1.mvp.interactor.PanListingInteractor;
import com.bookpurple.pp1.mvp.interfaces.PanListingViewPresenterContract;
import com.bookpurple.pp1.util.rx.RxSchedulersAbstractBase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class PanListingPresenter extends PanListingViewPresenterContract.Presenter {

    private static final String TAG = PanListingPresenter.class.getSimpleName();

    private PanListingInteractor listingInteractor;
    private RxSchedulersAbstractBase rxSchedulers;
    private CompositeDisposable compositeDisposable;

    public PanListingPresenter(PanListingViewPresenterContract.View view,
                               PanListingInteractor interactor,
                               RxSchedulersAbstractBase rxSchedulers,
                               CompositeDisposable compositeDisposable) {
        attachView(view);
        this.listingInteractor = interactor;
        this.rxSchedulers = rxSchedulers;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getListingData(UserDetailsRequest userDetailsRequest) {
        Disposable disposable = listingInteractor.getPanDetailResponse(userDetailsRequest)
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(panDetailResponse -> {
                    if (isViewAttached()) {
                        if (isNoContent(panDetailResponse)) {
                            getView().dataFetchFailure(new Throwable("Data is Null"));
                        } else {
                            getView().onListingDataFetched(panDetailResponse);
                        }
                    }
                }, throwable -> Logger.logException(TAG, throwable));

        compositeDisposable.add(disposable);
    }

    @Override
    public boolean isNoContent(PanDetailResponse panDetailResponse) {
        return null == panDetailResponse;
    }
}

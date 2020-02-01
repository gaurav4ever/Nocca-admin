package com.bookpurple.pp1.dagger.module;

import com.bookpurple.pp1.mvp.interactor.DeviceListingInteractor;
import com.bookpurple.pp1.mvp.interactor.PanListingInteractor;
import com.bookpurple.pp1.network.api.ServiceApi;
import com.bookpurple.pp1.util.rx.RxSchedulers;
import com.bookpurple.pp1.util.rx.RxSchedulersAbstractBase;
import com.bookpurple.pp1.util.rx.RxUtil;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    public PanListingInteractor getPanListingInteractor(@Named("serviceApi") ServiceApi serviceApi) {
        return new PanListingInteractor(serviceApi);
    }

    @Provides
    @Singleton
    public DeviceListingInteractor getDeviceDetailsListingInteractor(@Named("serviceApi") ServiceApi serviceApi) {
        return new DeviceListingInteractor(serviceApi);
    }

    @Provides
    @Singleton
    public RxSchedulersAbstractBase provideRxSchedulers() {
        return new RxSchedulers();
    }

    @Provides
    @Singleton
    public RxUtil provideRxUtil() {
        return new RxUtil();
    }
}

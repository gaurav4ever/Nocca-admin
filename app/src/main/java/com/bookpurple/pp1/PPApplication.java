package com.bookpurple.pp1;

import android.app.Application;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public class PPApplication extends Application {

    private static PPApplication application;
    private InternalApplication internalApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        internalApplication = InternalApplication.initialize(application);
        internalApplication.initFlow();
    }
}

package com.bookpurple.pp1;

import android.content.Context;

import com.bookpurple.pp1.core.GsonUtil;
import com.facebook.stetho.Stetho;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public class InternalApplication {

    private static final String TAG = InternalApplication.class.getName();
    private static InternalApplication internalApplication;

    private Context application;

    private InternalApplication(Context application) {
        this.application = application;
    }

    public static InternalApplication getApplication() {
        if (internalApplication == null) {
            throw new NullPointerException("Internal Application class not intialized");
        }
        return internalApplication;
    }

    public static InternalApplication initialize(Context application) {
        internalApplication = new InternalApplication(application);
        return internalApplication;
    }

    public Context getApplicationContext() {
        return application;
    }

    public void initFlow() {
        Stetho.initializeWithDefaults(application);
        GsonUtil.getInstance();
    }
}

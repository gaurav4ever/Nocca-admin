package com.bookpurple.pp1.network.logger;

import android.util.Log;

import com.bookpurple.pp1.BuildConfig;


/*
 * Written by gauravsharma on 2019-05-19.
 */
public class Logger {

    private static final String TAG = Logger.class.getName();


    public static void log(Object object) {
        log(TAG, object);
    }

    public static void log(String tag, Object object) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "" + object);
        }
    }

    public static void log(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message, throwable);
        }
    }
}

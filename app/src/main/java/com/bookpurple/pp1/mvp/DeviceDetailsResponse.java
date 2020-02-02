package com.bookpurple.pp1.mvp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class DeviceDetailsResponse {

    @SerializedName("deviceDetails")
    public List<DeviceDetails> deviceDetails;
}

package com.bookpurple.pp1.mvp;

import com.google.gson.annotations.SerializedName;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class UpdateDeviceStatusRequest {

    @SerializedName("tokenId")
    public String tokenId;

    @SerializedName("deviceId")
    public String deviceId;

    @SerializedName("status")
    public int status;
}

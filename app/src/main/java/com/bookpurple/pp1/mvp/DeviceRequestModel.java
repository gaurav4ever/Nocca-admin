package com.bookpurple.pp1.mvp;

import com.google.gson.annotations.SerializedName;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public class DeviceRequestModel {

    @SerializedName("userId")
    public String email;

    @SerializedName("panNo")
    public String panNumber;

}

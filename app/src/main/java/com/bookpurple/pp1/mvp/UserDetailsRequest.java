package com.bookpurple.pp1.mvp;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
@Parcel
public class UserDetailsRequest {

    @SerializedName("email")
    public String email;
}

package com.bookpurple.pp1.network.api;

import com.bookpurple.pp1.mvp.DeviceRequestModel;
import com.bookpurple.pp1.mvp.DeviceResponseModel;
import com.bookpurple.pp1.mvp.PanDetailResponse;
import com.bookpurple.pp1.mvp.UpdateDeviceStatusRequest;
import com.bookpurple.pp1.mvp.UserDetailsRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
 * Retrofit API interface to hold all the URLs related to Service API
 * Written by gauravsharma on 2019-05-19.
 */
public interface ServiceApi {

    @POST("/getPans")
    Observable<PanDetailResponse> getPanDetails(@Body UserDetailsRequest userDetailsRequest);

    @POST("/getDevices")
    Observable<DeviceResponseModel> getDeviceDetails(@Body DeviceRequestModel deviceRequestModel);

    @POST("/updateDeviceStatus")
    void updateDeviceStatus(@Body UpdateDeviceStatusRequest updateDeviceStatusRequest);
}

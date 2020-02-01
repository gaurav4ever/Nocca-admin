package com.bookpurple.pp1.mvp.interactor;

import com.bookpurple.pp1.mvp.DeviceRequestModel;
import com.bookpurple.pp1.mvp.DeviceResponseModel;
import com.bookpurple.pp1.mvp.UpdateDeviceStatusRequest;
import com.bookpurple.pp1.network.api.ServiceApi;

import io.reactivex.Observable;


/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class DeviceListingInteractor {

    private ServiceApi serviceApi;

    public DeviceListingInteractor(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    public Observable<DeviceResponseModel> getDeviceDetailResponse(DeviceRequestModel deviceRequestModel) {
        return serviceApi.getDeviceDetails(deviceRequestModel);
    }

    public void updateDeviceStatus(UpdateDeviceStatusRequest updateDeviceStatusRequest) {
        serviceApi.updateDeviceStatus(updateDeviceStatusRequest);
    }

    public DeviceRequestModel createDeviceRequestModel(String panId) {
        DeviceRequestModel deviceRequestModel = new DeviceRequestModel();
        deviceRequestModel.panId = panId;
        return deviceRequestModel;
    }

    public UpdateDeviceStatusRequest createUpdateDeviceStatusRequest(String tokenId,
                                                                     String deviceId,
                                                                     int status) {
        UpdateDeviceStatusRequest updateDeviceStatusRequest = new UpdateDeviceStatusRequest();
        updateDeviceStatusRequest.tokenId = tokenId;
        updateDeviceStatusRequest.deviceId = deviceId;
        updateDeviceStatusRequest.status = status;
        return updateDeviceStatusRequest;
    }
}

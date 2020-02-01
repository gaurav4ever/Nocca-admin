package com.bookpurple.pp1.mvp.interactor;

import com.bookpurple.pp1.mvp.PanDetailResponse;
import com.bookpurple.pp1.mvp.UserDetailsRequest;
import com.bookpurple.pp1.network.api.ServiceApi;

import io.reactivex.Observable;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class PanListingInteractor {

    private ServiceApi serviceApi;

    public PanListingInteractor(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    public Observable<PanDetailResponse> getPanDetailResponse(UserDetailsRequest userDetailsRequest) {
        return serviceApi.getPanDetails(userDetailsRequest);
    }

    public UserDetailsRequest createUserDetailsRequest(String email) {
        UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
        userDetailsRequest.email = email;
        return userDetailsRequest;
    }
}

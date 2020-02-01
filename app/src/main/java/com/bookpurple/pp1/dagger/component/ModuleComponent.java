package com.bookpurple.pp1.dagger.component;

import com.bookpurple.pp1.activity.DeviceListingActivity;
import com.bookpurple.pp1.activity.PanListingActivity;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public interface ModuleComponent {

    void inject(PanListingActivity panListingActivity);

    void inject(DeviceListingActivity deviceListingActivity);
}

package com.bookpurple.pp1.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bookpurple.pp1.R;
import com.bookpurple.pp1.logger.Logger;
import com.bookpurple.pp1.mvp.DeviceDetails;
import com.bookpurple.pp1.publishsubject.DeviceClickedItem;
import com.bookpurple.pp1.util.rx.RxViewUtil;

import io.reactivex.subjects.PublishSubject;

/*
 * Written by Gaurav Sharma on 2020-02-02.
 */
public class DeviceListingItemViewHolder extends DeviceListingViewHolder<DeviceDetails> {

    private Context context;
    private View view;
    private PublishSubject<DeviceClickedItem> deviceClickedItemPublishSubject;
    private PublishSubject<DeviceClickedItem> deviceStatusClickedItemPublishSubject;

    private TextView deviceTextView;
    private ToggleButton deviceStatusCheckBox;

    public DeviceListingItemViewHolder(Context context,
                                       View itemView,
                                       PublishSubject<DeviceClickedItem> deviceClickedItemPublishSubject,
                                       PublishSubject<DeviceClickedItem> deviceStatusClickedItemPublishSubject) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        this.deviceClickedItemPublishSubject = deviceClickedItemPublishSubject;
        this.deviceStatusClickedItemPublishSubject = deviceStatusClickedItemPublishSubject;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        deviceTextView = itemView.findViewById(R.id.deviceId);
        deviceStatusCheckBox = itemView.findViewById(R.id.deviceStatus);
    }

    @Override
    public void bindData(DeviceDetails item, int position) {
        if (null != item.deviceName) {
            deviceTextView.setText(item.deviceName);
        } else {
            deviceTextView.setText(item.deviceId);
        }
        deviceStatusCheckBox.setChecked(false);
        if (item.status == 1) {
            deviceStatusCheckBox.setChecked(true);
        }

        RxViewUtil.click(deviceTextView)
                .subscribe(aVoid -> {
                    final DeviceClickedItem deviceClickedItem = new DeviceClickedItem();
                    deviceClickedItem.deviceId = item.deviceId;
                    deviceClickedItem.status = item.status;
                    deviceClickedItemPublishSubject.onNext(deviceClickedItem);
                }, throwable -> Logger.logException(throwable));

        RxViewUtil.click(deviceStatusCheckBox)
                .subscribe(aVoid -> {
                    final DeviceClickedItem deviceClickedItem = new DeviceClickedItem();
                    deviceClickedItem.deviceId = item.deviceId;
                    if (deviceStatusCheckBox.isChecked()) {
                        deviceClickedItem.status = 1;
                        deviceStatusCheckBox.setChecked(true);
                    } else {
                        deviceClickedItem.status = 0;
                        deviceStatusCheckBox.setChecked(false);
                    }
                    deviceClickedItem.tokenId = item.tokenId;
                    deviceStatusClickedItemPublishSubject.onNext(deviceClickedItem);
                });
    }
}

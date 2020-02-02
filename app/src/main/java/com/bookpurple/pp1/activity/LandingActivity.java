package com.bookpurple.pp1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.bookpurple.pp1.R;
import com.bookpurple.pp1.constant.Constant;
import com.bookpurple.pp1.logger.Logger;
import com.bookpurple.pp1.mvp.UserDetailsRequest;
import com.bookpurple.pp1.util.rx.RxViewUtil;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class LandingActivity extends AppCompatActivity {

    private static final String TAG = LandingActivity.class.getSimpleName();

    private PublishSubject<UserDetailsRequest> loginButtonPublishSubject = PublishSubject.create();

    private EditText emailEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        emailEditText = findViewById(R.id.email);
        loginButton = findViewById(R.id.button);
        addClickObservables();
        registerToClickObservables();
    }

    private void addClickObservables() {

        RxViewUtil.click(loginButton)
                .subscribe(aVoid -> {
                    UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
                    userDetailsRequest.email = emailEditText.getText().toString();
                    loginButtonPublishSubject.onNext(userDetailsRequest);
                }, throwable -> Logger.logException(TAG, throwable));
    }


    private void registerToClickObservables() {
        Disposable loginButtonClickSubscription = loginButtonPublishSubject
                .subscribe(userDetailsRequest -> {
                    emailEditText.getText().toString();
                    startPanListingActivity(userDetailsRequest);
                }, throwable -> Logger.logException(TAG, throwable));

    }

    private void startPanListingActivity(UserDetailsRequest userDetailsRequest) {
        Intent intent = new Intent(getApplicationContext(), PanListingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ParcelConstant.PAN_LISTING_REQUEST_MODEL, userDetailsRequest.email);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

package com.bookpurple.pp1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bookpurple.pp1.R;
import com.bookpurple.pp1.adapter.PanListingAdapter;
import com.bookpurple.pp1.constant.Constant;
import com.bookpurple.pp1.dagger.component.ModuleComponent;
import com.bookpurple.pp1.dagger.provider.ComponentProvider;
import com.bookpurple.pp1.logger.Logger;
import com.bookpurple.pp1.mvp.PanDetailResponse;
import com.bookpurple.pp1.mvp.UserDetailsRequest;
import com.bookpurple.pp1.mvp.interactor.PanListingInteractor;
import com.bookpurple.pp1.mvp.interfaces.PanListingViewPresenterContract;
import com.bookpurple.pp1.mvp.presenter.PanListingPresenter;
import com.bookpurple.pp1.util.rx.RxSchedulersAbstractBase;
import com.bookpurple.pp1.util.rx.RxUtil;
import com.facebook.shimmer.ShimmerFrameLayout;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public class PanListingActivity extends AppCompatActivity implements PanListingViewPresenterContract.View {

    private static final String TAG = PanListingActivity.class.getSimpleName();
    @Inject
    public RxUtil rxUtil;
    @Inject
    public RxSchedulersAbstractBase rxSchedulers;
    @Inject
    public PanListingInteractor interactor;

    // Dagger related variables
    protected ModuleComponent component;
    // Rx Related Variables
    private CompositeDisposable lifecycle;

    private PanListingPresenter panListingPresenter;

    private RecyclerView listingRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PanListingAdapter adapter;

    private UserDetailsRequest userDetailsRequest;

    private RelativeLayout contentLayout;
    private ShimmerFrameLayout listingShimmerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panlistingactivity);
        injectDependencies();
        handleIntent();
        initView();
        loadData();
    }

    private void handleIntent() {
        if (null != getIntent() && null != getIntent().getExtras()) {
            Bundle bundle = getIntent().getExtras();
            if (!bundle.containsKey(Constant.ParcelConstant.PAN_LISTING_REQUEST_MODEL)) {
                finish();
            } else {
                userDetailsRequest = interactor
                        .createUserDetailsRequest(bundle.getString(Constant.ParcelConstant.PAN_LISTING_REQUEST_MODEL));
            }
        }
    }

    private void injectDependencies() {

        component = ComponentProvider.getComponent();
        component.inject(this);
        lifecycle = new CompositeDisposable();

        // create presenter
        panListingPresenter = new PanListingPresenter(this,
                interactor,
                rxSchedulers,
                lifecycle);
        RegisterToClickObservables();
    }

    private void RegisterToClickObservables() {
        Disposable panListingItemClickSubscription = adapter.getVendorClickedItemPublishSubject()
                .subscribe(panClickedItem -> {
                    // start device details listing activity
                }, throwable -> Logger.logException(TAG, throwable));

        lifecycle.add(panListingItemClickSubscription);
    }

    private void initView() {
        listingRecyclerView = findViewById(R.id.listing);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        listingRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PanListingAdapter(this, lifecycle);
        listingRecyclerView.setAdapter(adapter);

        listingShimmerLayout = findViewById(R.id.listing_shimmer_layout);
    }

    @Override
    public void onListingDataFetched(PanDetailResponse panDetailResponse) {
        stopShimmerAnimation();
        if (panDetailResponse != null) {
            adapter.setData(panDetailResponse.panDetails);
        }
    }

    @Override
    public void loadData() {
        startShimmerAnimation();
        panListingPresenter.getListingData(userDetailsRequest);
    }

    @Override
    public void dataFetchFailure(Throwable error) {
        Logger.log(TAG, error);
    }

    private void startShimmerAnimation() {
        //contentLayout.setVisibility(View.GONE);
        listingShimmerLayout.startShimmer();
    }

    private void stopShimmerAnimation() {
        //contentLayout.setVisibility(View.VISIBLE);
        listingShimmerLayout.setVisibility(View.GONE);
        listingShimmerLayout.stopShimmer();
    }

}

package com.nerdgeeks.nerdcrict20;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.nerdgeeks.nerdcrict20.adapters.TabAdapter;


public class MatchActivity extends AppCompatActivity {

    private TabLayout fixtab;
    private VideoController videoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        toolBar.setTitle("Match Scorecard");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ViewPager fixView = (ViewPager) findViewById(R.id.sparkViewPager);
        final CardView cardView = (CardView) findViewById(R.id.card);
        final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

        final NativeExpressAdView mAdView= new NativeExpressAdView(this);
        AdSize adSize = new AdSize(AdSize.FULL_WIDTH, 80);
        mAdView.setAdSize(adSize);
        mAdView.setAdUnitId(getString(R.string.native_ad));
        cardView.addView(mAdView);

        mAdView.setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build());

        videoController= mAdView.getVideoController();
        videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            @Override
            public void onVideoEnd() {
                super.onVideoEnd();
            }
        });

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                cardView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                cardView.setVisibility(View.VISIBLE);
                cardView.setAnimation(slide_down);
                fixView.setAnimation(slide_down);
            }
        });
        mAdView.loadAd(new AdRequest.Builder().build());

        fixtab = (TabLayout) findViewById(R.id.fixTabs);

        //Adding the tabs using addTab() method
        fixtab.addTab(fixtab.newTab().setText("Batting"));
        fixtab.addTab(fixtab.newTab().setText("Bowling"));

        fixView.setAdapter(new TabAdapter(getSupportFragmentManager(), fixtab.getTabCount(), this));

        fixtab.setupWithViewPager(fixView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_leave);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
    }
}

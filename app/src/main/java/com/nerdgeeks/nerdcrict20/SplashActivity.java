package com.nerdgeeks.nerdcrict20;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.utils.PrefManager;

public class SplashActivity extends Activity {

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View mSplashImage = findViewById(R.id.splash);
        View nSplashImage = findViewById(R.id.ball_img);
        TextView mSplashText = (TextView) findViewById(R.id.splashText);
        Animation splashAnimImage = AnimationUtils.loadAnimation(this, R.anim.splash_anim_img);
        Animation splashAnimText = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        mSplashText.startAnimation(splashAnimText);
        mSplashImage.startAnimation(splashAnimImage);
        nSplashImage.startAnimation(splashAnimImage);

        PrefManager prefManager= new PrefManager(this);
        int count= prefManager.AdsCheck();

        if(count!=1) {
            count++;
            prefManager.storeAdsCheck(count);
        } else {
            prefManager.storeAdsCheck(2);
        }

        if(count%4==0){
            launchInterstitial();
            loadInterstitial();
        }


        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void launchInterstitial() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9551927371844997/7318462468");
        //Set the adListener
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                showAdInter();
            }
            public void onAdFailedToLoad(int errorCode) {
                String message = String.format("onAdFailedToLoad(%s)", getErrorReason(errorCode));

            }
            @Override
            public void onAdClosed() {

            }
        });

    }

    private void showAdInter() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.d("", "ad was not ready to shown");
        }
    }

    public void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        //Load this Interstitial ad
        interstitialAd.loadAd(adRequest);
    }

    //Get a string error
    private String getErrorReason(int errorCode) {
        String errorReason = "";
        switch (errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "Internal Error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "Invalid Request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "No Fill";
                break;
        }
        return errorReason;
    }

}

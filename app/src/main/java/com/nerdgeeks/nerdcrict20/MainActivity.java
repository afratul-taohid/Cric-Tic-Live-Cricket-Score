package com.nerdgeeks.nerdcrict20;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.appbrain.AdId;
import com.appbrain.AppBrain;
import com.appbrain.InterstitialBuilder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.nerdgeeks.nerdcrict20.fragments.MatchCalendar;
import com.nerdgeeks.nerdcrict20.fragments.LiveFragment;
import com.nerdgeeks.nerdcrict20.fragments.RankingFragment;
import com.nerdgeeks.nerdcrict20.fragments.ResultFragment;
import com.nerdgeeks.nerdcrict20.fragments.UpcomingFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private InterstitialBuilder interstitialBuilder;
    private VideoController videoController;
    FrameLayout frameLayout;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        AppBrain.init(this);

        interstitialBuilder = InterstitialBuilder.create().setAdId(AdId.EXIT)
                .setFinishOnExit(this).preload(this);
        //creating nav header view
        View view = View.inflate(this,R.layout.nav_header,null);
        final View viewAbout = View.inflate(this,R.layout.about,null);
        TextView versionName= (TextView) viewAbout.findViewById(R.id.versionName);
        versionName.setText(BuildConfig.VERSION_NAME);

        //adding toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout= (FrameLayout) findViewById(R.id.fragment_container);
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
                frameLayout.setAnimation(slide_down);
            }
        });
        mAdView.loadAd(new AdRequest.Builder().build());


        //Adding navigation drawer
        final Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withHeader(view)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName("Live Scores")
                                .withIcon(R.drawable.ic_live_tv)
                                .withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName("Fixtures (Upcoming)")
                                .withIcon(R.drawable.ic_schedule_copy)
                                .withIdentifier(2),
                        new PrimaryDrawerItem()
                                .withName("ICC Calendar")
                                .withIcon(R.drawable.ic_calendar)
                                .withIdentifier(3),
                        new PrimaryDrawerItem()
                                .withName("Results")
                                .withIcon(R.drawable.ic_timeline)
                                .withIdentifier(4),
                        new PrimaryDrawerItem()
                                .withName("Ranking")
                                .withIcon(R.drawable.ic_trophy)
                                .withIdentifier(5),
                        new SectionDrawerItem()
                                .withName("MORE"),
                        new PrimaryDrawerItem()
                                .withName("Rate Me")
                                .withIcon(R.drawable.ic_rate_review)
                                .withIdentifier(6),
                        new PrimaryDrawerItem()
                                .withName("About")
                                .withIcon(R.drawable.ic_info)
                                .withIdentifier(7)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Fragment fragment;
                            if (drawerItem.getIdentifier() == 1) {
                                toolbar.setSubtitle("Live Scores");
                                fragment = new LiveFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_leave);
                                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                            } else if (drawerItem.getIdentifier() == 2) {
                                toolbar.setSubtitle("Upcoming Matches");
                                fragment = new UpcomingFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_leave);
                                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                            }else if (drawerItem.getIdentifier() == 3) {
                                toolbar.setSubtitle("Match Calendar");
                                fragment = new MatchCalendar();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_leave);
                                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                            } else if (drawerItem.getIdentifier() == 4) {
                                toolbar.setSubtitle("Results");
                                fragment = new ResultFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_leave);
                                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                            }else if (drawerItem.getIdentifier() == 5) {
                                toolbar.setSubtitle("Rankings");
                                fragment = new RankingFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_leave);
                                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                            } else if (drawerItem.getIdentifier() == 6) {
                                Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                // To count with Play market backstack, After pressing back button,
                                // to taken back to our application, we need to add following flags to intent.
                                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                try {
                                    startActivity(goToMarket);
                                } catch (ActivityNotFoundException e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                                }
                            } else if (drawerItem.getIdentifier() == 7) {
                                new MaterialDialog.Builder(MainActivity.this)
                                        .title("About Developer")
                                        .customView(viewAbout,true)
                                        .positiveText("MORE APPS")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                Uri uri = Uri.parse("market://search?q=pub:" + "NerdGeeks");
                                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                                try {
                                                    startActivity(goToMarket);
                                                } catch (ActivityNotFoundException e) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                                            Uri.parse("http://play.google.com/store/search?q=pub:" + "NerdGeeks")));
                                                }
                                            }
                                        })
                                        .show();
                            }
                        }
                        return false;
                    }
                })
                .withSelectedItem(1)
                .build();
        drawer.setSelection(1, true);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0
                || !interstitialBuilder.show(this)) {
            super.onBackPressed();
        }
    }

}

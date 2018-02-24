package com.nerdgeeks.nerdcrict20.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clockbyte.admobadapter.expressads.AdmobExpressRecyclerAdapterWrapper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.adapters.CalendarAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.models.Calendar;
import com.nerdgeeks.nerdcrict20.models.Datum;
import com.nerdgeeks.nerdcrict20.models.Match;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchCalendar extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CalendarAdapter calendarAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton mFabButton;
    private List<Datum> data;
    private ProgressBar circular_progress;
    AdmobExpressRecyclerAdapterWrapper adapterWrapper;


    public MatchCalendar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterFragment newInstance(String param1, String param2) {
        InterFragment fragment = new InterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);
        mFabButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
        circular_progress = (ProgressBar) rootView.findViewById(R.id.circular_progress);
        mFabButton.setOnClickListener(this);

        //MobileAds.initialize(getContext(), "ca-app-pub-9551927371844997/5851798468");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String Url = "/api/matchCalendar?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&v=3";
        getMatchCalendarData(Url);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0 && mFabButton.isShown()){
                    mFabButton.hide();
                }
                else if(dy < 0) {
                    mFabButton.show();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if ( newState == RecyclerView.SCROLL_STATE_IDLE){
                    mFabButton.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        return rootView;
    }

    private void getMatchCalendarData(final String URL){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Calendar> call = service.getCalendar(URL);

        call.enqueue(new Callback<Calendar>() {
            @Override
            public void onResponse(Call<Calendar> call, Response<Calendar> response) {
                Log.d("onResponse", response.message());

                Calendar calendar = response.body();

                data = calendar.getCalendarData();
                calendarAdapter = new CalendarAdapter(getContext(), data);
                //recyclerView.setAdapter(calendarAdapter);
                createAdapterWithNativeAds(calendarAdapter);
                circular_progress.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onFailure(Call<Calendar> call, Throwable t) {
                circular_progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void createAdapterWithNativeAds(CalendarAdapter adapter) {
        //your test devices' ids
        //when you'll be ready for release please use another ctor with admobReleaseUnitId instead.
        String testDevicesIds="ca-app-pub-9551927371844997/5851798468";
        adapterWrapper = new AdmobExpressRecyclerAdapterWrapper(getContext(), testDevicesIds,new AdSize(AdSize.FULL_WIDTH, 250)){
            @NonNull
            @Override
            protected ViewGroup getAdViewWrapper(ViewGroup parent) {
                return (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.native_express_ad_container,
                        parent, false);
            }
            @Override
            protected void recycleAdViewWrapper(@NonNull ViewGroup wrapper, @NotNull NativeExpressAdView ad) {
                //get the view which directly will contain ad
                ViewGroup container = (ViewGroup) wrapper.findViewById(R.id.ad_container);
                /**iterating through all children of the container view and remove the first occured {@link NativeExpressAdView}. It could be different with {@param ad}!!!*/
                for (int i = 0; i < container.getChildCount(); i++) {
                    View v = container.getChildAt(i);
                    if (v instanceof NativeExpressAdView) {
                        container.removeViewAt(i);
                        break;
                    }
                }
            }
            @Override
            protected void addAdViewToWrapper(@NonNull ViewGroup wrapper, @NotNull NativeExpressAdView ad) {
                //get the view which directly will contain ad
                ViewGroup container = (ViewGroup) wrapper.findViewById(R.id.ad_container);
                /**add the {@param ad} directly to the end of container*/
                container.addView(ad);
            }
        };
        adapterWrapper.setAdapter(adapter); //wrapping your adapter with a AdmobExpressRecyclerAdapterWrapper.
        adapterWrapper.setLimitOfAds(30);
        adapterWrapper.setNoOfDataBetweenAds(8);
        adapterWrapper.setFirstAdIndex(5);
        recyclerView.setAdapter(adapterWrapper);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab :
                createPopUpWindow(v);
                break;
            default:
                break;
        }
    }

    private void createPopUpWindow(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        MenuPopupHelper menuHelper = new MenuPopupHelper(getContext(), (MenuBuilder) popup.getMenu(), v);
        menuHelper.setForceShowIcon(true);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.afg:
                        filter(data,"Afghanistan");
                        break;
                    case R.id.aus:
                        filter(data,"Australia");
                        break;
                    case R.id.ban:
                        filter(data,"Bangladesh");
                        break;
                    case R.id.eng:
                        filter(data,"England");
                        break;
                    case R.id.ind:
                        filter(data,"India");
                        break;
                    case R.id.ire:
                        filter(data,"Ireland");
                        break;
                    case R.id.nz:
                        filter(data,"New Zealand");
                        break;
                    case R.id.pak:
                        filter(data,"Pakistan");
                        break;
                    case R.id.sa:
                        filter(data,"South Africa");
                        break;
                    case R.id.sl:
                        filter(data,"Sri Lanka");
                        break;
                    case R.id.wi:
                        filter(data,"West Indies");
                        break;
                    case R.id.zim:
                        filter(data,"Zimbabwe");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        menuHelper.show();
    }

    private void filter(List<Datum> models, String query) {

        final List<Datum> filteredModelList = new ArrayList<>();
        for (Datum model : models) {
            final String text = model.getName();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        calendarAdapter = new CalendarAdapter(getContext(),filteredModelList);
        calendarAdapter.notifyDataSetChanged();
        //recyclerView.setAdapter(calendarAdapter);
        createAdapterWithNativeAds(calendarAdapter);

    }
}

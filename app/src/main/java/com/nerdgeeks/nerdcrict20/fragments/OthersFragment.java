package com.nerdgeeks.nerdcrict20.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clockbyte.admobadapter.expressads.AdmobExpressRecyclerAdapterWrapper;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.adapters.CalendarAdapter;
import com.nerdgeeks.nerdcrict20.adapters.MatchAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.models.Match;
import com.nerdgeeks.nerdcrict20.models.Matches;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OthersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OthersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MatchAdapter matchAdapter;
    private RecyclerView recyclerView;
    private List<Match> upcomingMatches = new ArrayList<>();
    AdmobExpressRecyclerAdapterWrapper adapterWrapper;

    public OthersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OthersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OthersFragment newInstance(String param1, String param2) {
        OthersFragment fragment = new OthersFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_others, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String Url = "/api/matches/?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&v=3";
        getUpcomingMatchesData(Url,rootView);
        return rootView;
    }

    private void getUpcomingMatchesData(String URL, View rootView){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Matches> call = service.getMatchData(URL);

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d("onResponse", response.message());

                Matches matches = response.body();

                for(int i=0; i<matches.getMatches().size();i++){
                    if( (!matches.getMatches().get(i).getMatchStarted()) &&
                            ( !matches.getMatches().get(i).getType().equals("One-Day Internationals") &&
                                    !matches.getMatches().get(i).getType().equals("Twenty20 Internationals"))) {
                        upcomingMatches.add(matches.getMatches().get(i));
                    }
                }

                matchAdapter = new MatchAdapter(getContext(),upcomingMatches);
                //recyclerView.setAdapter(matchAdapter);
                createAdapterWithNativeAds(matchAdapter);

            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {

            }
        });
    }

    private void createAdapterWithNativeAds(MatchAdapter adapter) {
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
        adapterWrapper.setNoOfDataBetweenAds(6);
        adapterWrapper.setFirstAdIndex(5);
        recyclerView.setAdapter(adapterWrapper);
        adapter.notifyDataSetChanged();
    }

}

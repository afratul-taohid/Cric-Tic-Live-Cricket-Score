package com.nerdgeeks.nerdcrict20.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.adapters.MatchAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.models.Match;
import com.nerdgeeks.nerdcrict20.models.Matches;

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
public class InterFragment extends Fragment {
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
    private ProgressBar circular_progress;


    public InterFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_inter, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);
        circular_progress = (ProgressBar) rootView.findViewById(R.id.circular_progress);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String Url = "/api/matches/?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&v=3";
        getUpcomingMatchesData(Url,rootView);

        return rootView;
    }

    private void getUpcomingMatchesData(String URL, final View rootView){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Matches> call = service.getMatchData(URL);

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d("onResponse", response.message());

                Matches matches = response.body();

                for(int i=0; i<matches.getMatches().size();i++){
                    if( (!matches.getMatches().get(i).getMatchStarted()) &&
                            ( matches.getMatches().get(i).getType().equals("One-Day Internationals") ||
                                    matches.getMatches().get(i).getType().equals("Twenty20 Internationals"))) {
                        upcomingMatches.add(matches.getMatches().get(i));
                    }
                }

                matchAdapter = new MatchAdapter(getContext(),upcomingMatches);
                recyclerView.setAdapter(matchAdapter);
                circular_progress.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Snackbar.make(rootView, "Unable to resolve host, check your internet connection", Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                circular_progress.setVisibility(View.INVISIBLE);
            }
        });
    }
}

package com.nerdgeeks.nerdcrict20.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.adapters.BattingAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.helper.DividerItemDecoration;
import com.nerdgeeks.nerdcrict20.helper.DoubleHeaderDecoration;
import com.nerdgeeks.nerdcrict20.models.Batting;
import com.nerdgeeks.nerdcrict20.models.Score;
import com.nerdgeeks.nerdcrict20.models.Score__;
import com.nerdgeeks.nerdcrict20.models.Summary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private BattingAdapter adapter;
    private DoubleHeaderDecoration decor;
    private ProgressBar circular_progress;
    private ArrayList<String> team_innings = new ArrayList<>();
    private ArrayList<String> team_player = new ArrayList<>();

    public BattingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BattingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BattingFragment newInstance(String param1, String param2) {
        BattingFragment fragment = new BattingFragment();
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
        // Inflate the layout for this fragmentView rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        View rootView = inflater.inflate(R.layout.fragment_batting, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.summaryView);
        circular_progress = (ProgressBar) rootView.findViewById(R.id.circular_progress);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        String UniqueId = getActivity().getIntent().getStringExtra("unique_id");
        String Url = "/api/fantasySummary?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&unique_id="+UniqueId;
        getSummaryMatchesData(Url,rootView);

        return rootView;
    }

    private void getSummaryMatchesData(String URL, final View rootView){
        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Summary> call = service.getSummary(URL);

        call.enqueue(new Callback<Summary>() {
            @Override
            public void onResponse(Call<Summary> call, Response<Summary> response) {
                Log.d("onResponse", response.message());

                Summary summary = response.body();
                List<Score__> bat_score = new ArrayList<Score__>();

                if (summary.getData().getBatting()!=null) {
                    for (int i = 0; i < summary.getData().getBatting().size(); i++) {
                        Batting bat = summary.getData().getBatting().get(i);
                        team_innings.add(bat.getTitle());
                        if (bat.getScores() != null) {
                            int size = bat.getScores().size();
                            team_player.add(String.valueOf(size));
                            for (int j = 0; j < size; j++) {
                                bat_score.add(bat.getScores().get(j));
                            }
                        }
                    }
                }
                adapter = new BattingAdapter(getContext(),bat_score, team_innings, team_player);
                decor = new DoubleHeaderDecoration(adapter);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(decor, 1);
                circular_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Summary> call, Throwable t) {
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

package com.nerdgeeks.nerdcrict20.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import com.nerdgeeks.nerdcrict20.adapters.ResultAdapter;
import com.nerdgeeks.nerdcrict20.adapters.ScoreAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.helper.DividerItemDecoration;
import com.nerdgeeks.nerdcrict20.helper.Result;
import com.nerdgeeks.nerdcrict20.models.Matches;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar circular_progress;
    private ResultAdapter resultAdapter;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.resultView);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.fragment_result);
        circular_progress = (ProgressBar) rootView.findViewById(R.id.circular_progress);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        String Url = "/api/cricket/?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1";
        getLiveMatchesData(Url,rootView);
        return rootView;
    }

    private void getLiveMatchesData(String url, View rootView) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Result> call = service.getResult(url);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("Live onResponse", response.message());

                Result results = response.body();

                resultAdapter = new ResultAdapter(getActivity(),results.getData());
                recyclerView.setAdapter(resultAdapter);

                circular_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("Error Live", t.getMessage());
                Snackbar.make(coordinatorLayout, "Unable to resolve host, check your internet connection", Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                circular_progress.setVisibility(View.INVISIBLE);
            }
        });
    }
}

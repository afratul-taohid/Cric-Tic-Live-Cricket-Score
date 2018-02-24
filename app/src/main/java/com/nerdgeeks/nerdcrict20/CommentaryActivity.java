package com.nerdgeeks.nerdcrict20;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.adapters.CommentAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.helper.Datum;
import com.nerdgeeks.nerdcrict20.helper.DividerItemDecoration;
import com.nerdgeeks.nerdcrict20.models.Ball;
import com.nerdgeeks.nerdcrict20.models.Commentary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentaryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private List<Ball> comment_ = new ArrayList<>();;
    private ProgressBar circular_progress;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentary);

        //adding toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Ball by Ball");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.commentView);
        circular_progress = (ProgressBar) findViewById(R.id.circular_progress);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.comment_layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        String UniqueId = getIntent().getStringExtra("unique_id");
        String Url = "/api/ballByBall?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&unique_id="+UniqueId;
        getCommentaryData(Url);
    }

    private void getCommentaryData(String URL){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Commentary> call = service.getCommentary(URL);

        call.enqueue(new Callback<Commentary>() {
            @Override
            public void onResponse(Call<Commentary> call, Response<Commentary> response) {
                Log.d("onResponse", response.message());

                Commentary commentary = response.body();

                for(int i=0; i<commentary.getData().size(); i++){
                    Datum data = commentary.getData().get(i);
                    for (int j=0; j<data.getBall().size(); j++){
                        comment_.add(data.getBall().get(j));
                    }
                }

                adapter = new CommentAdapter(CommentaryActivity.this,comment_);
                recyclerView.setAdapter(adapter);
                circular_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Commentary> call, Throwable t) {
                Snackbar.make(coordinatorLayout, "Unable to resolve host, check your internet connection", Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                circular_progress.setVisibility(View.INVISIBLE);
            }
        });
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

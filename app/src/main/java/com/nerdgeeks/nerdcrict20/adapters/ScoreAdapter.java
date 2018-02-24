package com.nerdgeeks.nerdcrict20.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nerdgeeks.nerdcrict20.CommentaryActivity;
import com.nerdgeeks.nerdcrict20.MatchActivity;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.models.LiveMatch;
import com.nerdgeeks.nerdcrict20.models.Match;
import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Matches;
import com.nerdgeeks.nerdcrict20.models.Matches_;
import com.nerdgeeks.nerdcrict20.utils.OnItemClickListener;

import junit.framework.TestCase;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 5/7/2017.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private Activity context;
    private List<Match> matches;
    private OnItemClickListener onItemClickListener;
    private final Handler handler = new Handler();
    private String url;

    public ScoreAdapter(Activity context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_score,parent,false);
        return new ViewHolder(rootView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(matches!= null){
            holder.error_msg.setVisibility(View.INVISIBLE);
            holder.team1.setText(matches.get(position).getTeam1());
            holder.card1.setBackgroundColor(getRandomMaterialColor("400"));

            holder.team2.setText(matches.get(position).getTeam2());
            holder.card2.setBackgroundColor(getRandomMaterialColor("400"));

            holder.match_type.setText(matches.get(position).getType());

            final String uID = String.valueOf(matches.get(position).getUniqueId());
            url= "/api/cricketScore?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&unique_id="+uID;
            getLiveScoreData(url, holder);
            Log.d("Live Matches Id", uID); //1099388

            holder.match_center.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MatchActivity.class);
                    intent.putExtra("unique_id", uID);
                    context.startActivity(intent);
                    context.overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                }
            });

            holder.ball_by_ball.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CommentaryActivity.class);
                    intent.putExtra("unique_id", uID);
                    context.startActivity(intent);
                    context.overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                }
            });
        } else {
           holder.error_msg.setVisibility(View.VISIBLE);
        }
    }

    private void getLiveScoreData(final String url, final ViewHolder holder) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<LiveMatch> call = service.getLiveMatchData(url);

        call.enqueue(new Callback<LiveMatch>() {
            @Override
            public void onResponse(Call<LiveMatch> call, Response<LiveMatch> response) {
                Log.d("Adapter onResponse", response.message());

                LiveMatch matches = response.body();
                holder.match_score.setText(matches.getScore());
                holder.match_inngs.setText(matches.getInningsRequirement());

            }

            @Override
            public void onFailure(Call<LiveMatch> call, Throwable t) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLiveScoreData(url, holder);
            }
        }, 10000);

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView team1, team2, match_type, match_score, match_inngs, error_msg;
        private CardView card1,card2;
        private  OnItemClickListener onItemClickListener;
        private AppCompatButton match_center, ball_by_ball;
        ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            match_type = (TextView) itemView.findViewById(R.id.type);
            match_score = (TextView) itemView.findViewById(R.id.score);
            match_inngs = (TextView) itemView.findViewById(R.id.innings);
            error_msg = (TextView) itemView.findViewById(R.id.error_message);
            card1 = (CardView) itemView.findViewById(R.id.card_1);
            card2 = (CardView) itemView.findViewById(R.id.card_2);
            match_center = (AppCompatButton) itemView.findViewById(R.id.match_center);
            ball_by_ball = (AppCompatButton) itemView.findViewById(R.id.ball_by_ball);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}


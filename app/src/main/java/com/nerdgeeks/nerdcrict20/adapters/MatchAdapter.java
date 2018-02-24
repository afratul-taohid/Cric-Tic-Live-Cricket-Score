package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.models.Match;
import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.utils.OnItemClickListener;

import junit.framework.TestCase;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by hp on 5/7/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Match> matches;
    private OnItemClickListener onItemClickListener;

    private String[] months = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October",
            "November", "December"
    };

    public MatchAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_matches,parent,false);
        return new ViewHolder(rootView, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder myholder = (ViewHolder) holder;

        myholder.team1.setText(matches.get(position).getTeam1());
        myholder.card1.setBackgroundColor(getRandomMaterialColor("400"));

        myholder.team2.setText(matches.get(position).getTeam2());
        myholder.card2.setBackgroundColor(getRandomMaterialColor("400"));

        myholder.match_type.setText(matches.get(position).getType());
        String dateTime = matches.get(position).getDateTimeGMT();

        //date and time split & date_Time[0] means date
        String[] date_Time = dateTime.split("T");

        //date split & date[0] means year, date[1], means month & date[2] means day
        String[] date = date_Time[0].split("-");

        if (Integer.parseInt(date[1]) < 10){
            String[] month_no = date[1].split("0");
            int m = Integer.valueOf(month_no[1]) ;
            String month = months[m-1];
            String final_date = date[2] + " " + month + " " + date[0];
            myholder.match_date.setText(final_date);

        } else {
            int m = Integer.valueOf(date[1]);
            String month = months[m-1];
            String final_date = date[2] + " " + month + " " + date[0];
            myholder.match_date.setText(final_date);
        }

        String defaultTimezone = TimeZone.getDefault().getID();

        myholder.gmt.setText(TimeZone.getTimeZone(defaultTimezone).getID());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-0530"));
            Date dm = sdf.parse(date_Time[1]);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
            String dateStr = formatter.format(dm);
            formatter.setTimeZone(TimeZone.getTimeZone(defaultTimezone));

            String[] dateChck = dateStr.split(":");
            if(Integer.valueOf(dateChck[0])>12){
                String finaldate= Integer.valueOf(dateChck[0])-12+":"+dateChck[1];
                myholder.match_time.setText(finaldate);
            }
            else {
                myholder.match_time.setText(dateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        holder.listItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onItemClickListener.onClick(view, position);
//            }
//        });
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

        private TextView team1, team2, match_date, match_type, match_time, gmt;
        private CardView card1,card2;
        private  OnItemClickListener onItemClickListener;
        ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team_1);
            team2 = (TextView) itemView.findViewById(R.id.team_2);
            match_date = (TextView) itemView.findViewById(R.id.match_date);
            match_type = (TextView) itemView.findViewById(R.id.match_type);
            match_time = (TextView) itemView.findViewById(R.id.match_time);
            gmt = (TextView) itemView.findViewById(R.id.gmt);
            card1 = (CardView) itemView.findViewById(R.id.card1);
            card2 = (CardView) itemView.findViewById(R.id.card2);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view, getAdapterPosition());
    }
    }
}

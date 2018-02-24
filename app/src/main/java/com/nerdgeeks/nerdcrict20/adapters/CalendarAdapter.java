package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Calendar;
import com.nerdgeeks.nerdcrict20.models.Datum;
import com.nerdgeeks.nerdcrict20.utils.OnItemClickListener;
import java.util.List;


/**
 * Created by hp on 5/7/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Datum> calendar;
    private OnItemClickListener onItemClickListener;



    public CalendarAdapter(Context context, List<Datum> calendar) {
        this.context = context;
        this.calendar = calendar;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView team1, team2, match_date, match_type, match_time;
        private CardView card1,card2;
        private  OnItemClickListener onItemClickListener;
        ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.team1 = (TextView) itemView.findViewById(R.id.team_1);
            this.team2 = (TextView) itemView.findViewById(R.id.team_2);
            this.match_date = (TextView) itemView.findViewById(R.id.match_date);
            this.match_type = (TextView) itemView.findViewById(R.id.match_type);
            this.match_time = (TextView) itemView.findViewById(R.id.match_time);
            this.card1 = (CardView) itemView.findViewById(R.id.card1);
            this.card2 = (CardView) itemView.findViewById(R.id.card2);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_calendar,parent,false);
        return new ViewHolder(rootView, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder myHolder = (ViewHolder) holder;

        String full= calendar.get(position).getName();
        String[] teamNplaceNgroup= full.split("at");
        String[] team= teamNplaceNgroup[0].split("v");
        String[] placeNgroup= teamNplaceNgroup[1].split(",");

        myHolder.team1.setText(team[0]);
        myHolder.card1.setBackgroundColor(getRandomMaterialColor("400"));
        myHolder.team2.setText(team[1]);
        myHolder.card2.setBackgroundColor(getRandomMaterialColor("400"));
        myHolder.match_date.setText(calendar.get(position).getDate());
        myHolder.match_type.setText(placeNgroup[1]);
        myHolder.match_time.setText(placeNgroup[0]);

//        holder.listItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onItemClickListener.onClick(view, position);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return calendar.size();
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
}

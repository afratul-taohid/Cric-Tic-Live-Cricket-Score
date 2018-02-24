package com.nerdgeeks.nerdcrict20.adapters;

/**
 * Created by IMRAN on 6/4/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.MatchActivity;
import com.nerdgeeks.nerdcrict20.helper.Datas;
import com.nerdgeeks.nerdcrict20.models.Ball;
import com.nerdgeeks.nerdcrict20.R;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Activity context;
    private List<Datas> data;

    public ResultAdapter(Activity context, List<Datas> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_result,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());

        final String uID = data.get(position).getUniqueId();

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MatchActivity.class);
                intent.putExtra("unique_id", uID);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private CardView card;
        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_result);
            card = (CardView) itemView.findViewById(R.id.card_result);
        }
    }
}
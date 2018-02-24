package com.nerdgeeks.nerdcrict20.adapters;

/**
 * Created by IMRAN on 6/4/2017.
 */

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.models.Ball;
import com.nerdgeeks.nerdcrict20.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Activity context;
    private List<Ball> ball;

    public CommentAdapter(Activity context, List<Ball> ball) {
        this.context = context;
        this.ball = ball;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!ball.get(position).getText().equals("")){
            holder.text.setText(ball.get(position).getText());
        }else {
            holder.text.setVisibility(View.INVISIBLE);
        }

        if(!ball.get(position).getEvent().equals("")){
            if(ball.get(position).getEvent().equals("FOUR")){
                holder.event.setText("4 runs");
            } else if(ball.get(position).getEvent().equals("SIX")){
                holder.event.setText("6 runs");
            } else
                holder.event.setText(ball.get(position).getEvent());
        }else {
            holder.event.setVisibility(View.INVISIBLE);
        }

        if(!ball.get(position).getDismissal().equals("")){
            holder.dismiss.setText(ball.get(position).getDismissal());
        }else {
            holder.dismiss.setVisibility(View.INVISIBLE);
        }

        if(!ball.get(position).getOversActual().equals("")){
            holder.actual_over.setText(ball.get(position).getOversActual() + " Ov.");
        }else {
            holder.actual_over.setVisibility(View.INVISIBLE);
        }

        if(!ball.get(position).getPlayers().equals("")){
            holder.players.setText(ball.get(position).getPlayers());
        }else {
            holder.players.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return ball.size();
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

        private TextView text, event, dismiss, actual_over, players;
        private CardView card;
        ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            event = (TextView) itemView.findViewById(R.id.event);
            dismiss = (TextView) itemView.findViewById(R.id.dismiss);
            actual_over = (TextView) itemView.findViewById(R.id.overs_actual);
            players = (TextView) itemView.findViewById(R.id.players);
            card = (CardView) itemView.findViewById(R.id.comment_card);
        }
    }
}
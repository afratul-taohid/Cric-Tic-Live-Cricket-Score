package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Score_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IMRAN on 5/31/2017.
 */

public class BowlingAdapter extends RecyclerView.Adapter<BowlingAdapter.ViewHolder>
        implements DoubleHeaderAdapter<BowlingAdapter.HeaderHolder, BowlingAdapter.SubHeaderHolder>{
    private Context context;
    private List<Score_> bowling;
    private ArrayList<String> team;
    private ArrayList<String> team_size;
    private int i;

    public BowlingAdapter(Context context, List<Score_> bowling, ArrayList<String> team, ArrayList<String> team_size) {
        this.context = context;
        this.bowling = bowling;
        this.team = team;
        this.team_size = team_size;
    }

    @Override
    public BowlingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_summary,parent,false);
        return new BowlingAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BowlingAdapter.ViewHolder holder, int position) {
        holder.name.setText(bowling.get(position).getBowler());
        holder.run_over.setText(bowling.get(position).getO());
        holder.ball_maiden.setText(bowling.get(position).getM());
        holder.fours_run.setText(bowling.get(position).getR());
        holder.sixes_wicket.setText(bowling.get(position).getW());
    }

    @Override
    public int getItemCount() {
        return bowling.size();
    }

    @Override
    public long getHeaderId(int position) {
        if(team_size.size()==1){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            } else {
                return 0;
            }
        } else if(team_size.size()==2){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            }else
                return 0;
        } else if(team_size.size()==3){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            }
            else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/jSize;
            }else
                return 0;

        } else if(team_size.size()==4) {
            if (position == Integer.parseInt(team_size.get(0))) {
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            } else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/jSize;
            } else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1))+Integer.parseInt(team_size.get(2)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1))+Integer.parseInt(team_size.get(2)));
                return position/jSize;
            }else
                return 0;

        } else
            return 0;
    }

    @Override
    public long getSubHeaderId(int position) {
        if(team_size.size()==1){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            } else
                return 0;
        } else if(team_size.size()==2){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            }else
                return 0;
        } else if(team_size.size()==3){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            }
            else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/jSize;
            }else
                return 0;

        } else if(team_size.size()==4) {
            if (position == Integer.parseInt(team_size.get(0))) {
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            } else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/jSize;
            } else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1))+Integer.parseInt(team_size.get(2)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1))+Integer.parseInt(team_size.get(2)));
                return position/jSize;
            }else
                return 0;

        } else
            return 0;
    }

    @Override
    public BowlingAdapter.HeaderHolder onCreateHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
        return new BowlingAdapter.HeaderHolder(rootView);
    }

    @Override
    public BowlingAdapter.SubHeaderHolder onCreateSubHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.sub_header, parent, false);
        return new BowlingAdapter.SubHeaderHolder(rootView);
    }

    @Override
    public void onBindHeaderHolder(BowlingAdapter.HeaderHolder viewholder, int position) {
        if(position+5>bowling.size()){
            if (i<team.size()){
                viewholder.state.setText(team.get(i));
                i++;
            }
        } else {
            if (i<team.size()){
                viewholder.state.setText(team.get(i));
                i++;
            }
        }
    }

    @Override
    public void onBindSubHeaderHolder(BowlingAdapter.SubHeaderHolder viewholder, int position) {
        viewholder.state.setText("BOWLING");
        viewholder.run_over.setText("0");
        viewholder.ball_maiden.setText("M");
        viewholder.fours_run.setText("R");
        viewholder.sixes_wicket.setText("W");
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, run_over, ball_maiden, fours_run, sixes_wicket;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            run_over = (TextView) itemView.findViewById(R.id.run_over);
            ball_maiden = (TextView) itemView.findViewById(R.id.ball_maiden);
            fours_run = (TextView) itemView.findViewById(R.id.fours_run);
            sixes_wicket = (TextView) itemView.findViewById(R.id.sixes_wicket);
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder{
        private ImageView img_flag;
        private TextView state;
        HeaderHolder(View itemView) {
            super(itemView);
            img_flag = (ImageView) itemView.findViewById(R.id.img_flag);
            state = (TextView) itemView.findViewById(R.id.state);
        }
    }

    class SubHeaderHolder extends RecyclerView.ViewHolder{
        private TextView state,run_over, ball_maiden, fours_run, sixes_wicket;
        SubHeaderHolder(View itemView) {
            super(itemView);
            state = (TextView) itemView.findViewById(R.id.state);
            run_over = (TextView) itemView.findViewById(R.id.run_over);
            ball_maiden = (TextView) itemView.findViewById(R.id.ball_maiden);
            fours_run = (TextView) itemView.findViewById(R.id.fours_run);
            sixes_wicket = (TextView) itemView.findViewById(R.id.sixes_wicket);
        }
    }
}

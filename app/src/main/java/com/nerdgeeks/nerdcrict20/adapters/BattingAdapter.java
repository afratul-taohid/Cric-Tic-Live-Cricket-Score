package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Score__;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IMRAN on 5/31/2017.
 */

public class BattingAdapter extends RecyclerView.Adapter<BattingAdapter.ViewHolder>
        implements DoubleHeaderAdapter<BattingAdapter.HeaderHolder, BattingAdapter.SubHeaderHolder>{
    private Context context;
    private List<Score__> battings;
    private ArrayList<String> team;
    private ArrayList<String> team_size;

    public BattingAdapter(Context context, List<Score__> battings, ArrayList<String> team, ArrayList<String> team_size) {
        this.context = context;
        this.battings = battings;
        this.team = team;
        this.team_size = team_size;
    }

    @Override
    public BattingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_summary,parent,false);
        return new BattingAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BattingAdapter.ViewHolder holder, int position) {
        holder.name.setText(battings.get(position).getBatsman());
        holder.dismiss.setText(battings.get(position).getDismissalInfo());
        holder.run_over.setText(battings.get(position).getR());
        holder.ball_maiden.setText(battings.get(position).getB());
        holder.fours_run.setText(battings.get(position).get4s());
        holder.sixes_wicket.setText(battings.get(position).get6s());
    }

    @Override
    public int getItemCount() {
        return battings.size();
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
            }else {
                return 0;
            }
        } else if(team_size.size()==3){
            if(position==Integer.parseInt(team_size.get(0))){
                int jSize = Integer.parseInt(team_size.get(0));
                return position/jSize;
            }
            else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int jSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/jSize;
            }else {
                return 0;
            }

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
            }else {
                return 0;
            }

        } else {
            return 0;
        }
    }

    @Override
    public long getSubHeaderId(int position) {
        if(team_size.size()==1){
            if(position==Integer.parseInt(team_size.get(0))){
                int kSize = Integer.parseInt(team_size.get(0));
                return position/kSize;
            } else {
                return 0;
            }

        } else if(team_size.size()==2){
            if(position==Integer.parseInt(team_size.get(0))){
                int kSize = Integer.parseInt(team_size.get(0));
                return position/kSize;
            }else {
                return 0;
            }

        } else if(team_size.size()==3){
            if(position==Integer.parseInt(team_size.get(0))){
                int kSize = Integer.parseInt(team_size.get(0));
                return position/kSize;
            }
            else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int kSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/kSize;
            }else {
                return 0;
            }

        } else if(team_size.size()==4) {
            if (position == Integer.parseInt(team_size.get(0))) {
                int kSize = Integer.parseInt(team_size.get(0));
                return position/kSize;
            } else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)))){
                int kSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1)));
                return position/kSize;
            } else if(position==(Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1))+Integer.parseInt(team_size.get(2)))){
                int kSize = (Integer.parseInt(team_size.get(0))+ Integer.parseInt(team_size.get(1))+Integer.parseInt(team_size.get(2)));
                return position/kSize;
            }else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public BattingAdapter.HeaderHolder onCreateHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
        return new BattingAdapter.HeaderHolder(rootView);
    }

    @Override
    public BattingAdapter.SubHeaderHolder onCreateSubHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.sub_header, parent, false);
        return new BattingAdapter.SubHeaderHolder(rootView);
    }

    @Override
    public void onBindHeaderHolder(BattingAdapter.HeaderHolder viewholder, int position) {
        switch (team_size.size()){
            case 1:
                viewholder.state.setText(team.get(0));
                break;
            case 2:
                if(position==Integer.parseInt(team_size.get(0)))
                    viewholder.state.setText(team.get(1));
                else
                    viewholder.state.setText(team.get(0));
                break;
            case 3:
                if(position==Integer.parseInt(team_size.get(0))) {
                    viewholder.state.setText(team.get(1));
                }
                else if(position==Integer.parseInt(team_size.get(1))) {
                    viewholder.state.setText(team.get(2));
                }
                else
                    viewholder.state.setText(team.get(0));
                break;
            case 4:
                if(position==Integer.parseInt(team_size.get(0)))
                    viewholder.state.setText(team.get(1));
                else if(position==Integer.parseInt(team_size.get(1)))
                    viewholder.state.setText(team.get(2));
                else if(position==Integer.parseInt(team_size.get(2)))
                    viewholder.state.setText(team.get(3));
                else
                    viewholder.state.setText(team.get(0));
                break;
            default:
                break;

        }
    }

    @Override
    public void onBindSubHeaderHolder(BattingAdapter.SubHeaderHolder viewholder, int position) {
        viewholder.state.setText("BATTING");
        viewholder.run_over.setText("R");
        viewholder.ball_maiden.setText("B");
        viewholder.fours_run.setText("4s");
        viewholder.sixes_wicket.setText("6s");
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, dismiss, run_over, ball_maiden, fours_run, sixes_wicket;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            dismiss = (TextView) itemView.findViewById(R.id.dismissal);
            run_over = (TextView) itemView.findViewById(R.id.run_over);
            ball_maiden = (TextView) itemView.findViewById(R.id.ball_maiden);
            fours_run = (TextView) itemView.findViewById(R.id.fours_run);
            sixes_wicket = (TextView) itemView.findViewById(R.id.sixes_wicket);
        }
    }

     class HeaderHolder extends RecyclerView.ViewHolder{
         private ImageView img_flag;
         private TextView state, total_score, overs, run_rate;
         HeaderHolder(View itemView) {
            super(itemView);
             img_flag = (ImageView) itemView.findViewById(R.id.img_flag);
             state = (TextView) itemView.findViewById(R.id.state);
             total_score = (TextView) itemView.findViewById(R.id.total_score);
             overs = (TextView) itemView.findViewById(R.id.overs);
             run_rate = (TextView) itemView.findViewById(R.id.run_rate);
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

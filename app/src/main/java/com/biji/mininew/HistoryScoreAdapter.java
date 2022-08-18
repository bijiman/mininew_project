package com.biji.mininew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biji.mininew.database.HistoryScore;

import java.util.List;

public class HistoryScoreAdapter extends RecyclerView.Adapter<HistoryScoreAdapter.MyHolder> {

    private final Context context;
    private List<HistoryScore> historyScoreList;

    public HistoryScoreAdapter(Context context){
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHistoryScoreList(List<HistoryScore> historyScoreList){
        this.historyScoreList=historyScoreList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_score,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.score.setText(Integer.toString(this.historyScoreList.get(position).history_score));
    }

    @Override
    public int getItemCount() {
        if (historyScoreList == null){
            return 0;
        }
        return this.historyScoreList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView score;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.scoreText);
        }
    }
}

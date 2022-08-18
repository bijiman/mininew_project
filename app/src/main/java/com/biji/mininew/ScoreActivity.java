package com.biji.mininew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.biji.mininew.database.AppDatabase;
import com.biji.mininew.database.HistoryScore;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private HistoryScoreAdapter historyScoreAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        MaterialButton back2Btn = findViewById(R.id.back2Btn);
        back2Btn.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });

        initRecycle();
        loadHistoryList();
    }

    private void initRecycle() {
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        historyScoreAdapter = new HistoryScoreAdapter(getApplicationContext());
        recyclerView.setAdapter(historyScoreAdapter);
    }

    private void loadHistoryList(){
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<HistoryScore> historyScoreList = db.historyScoreDao().getHistory();
        historyScoreAdapter.setHistoryScoreList(historyScoreList);

    }
}
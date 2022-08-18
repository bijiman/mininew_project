package com.biji.mininew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.biji.mininew.database.AppDatabase;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton snakeBtn = findViewById(R.id.snakeBtn);
        snakeBtn.setOnClickListener(view -> {
            AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
            db.scoreDao().deleteAll();
            finish();
            startActivity(new Intent(this, snakeActivity.class));
        });

        MaterialButton comingSoonBtn = findViewById(R.id.btn2048);
        comingSoonBtn.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(this, ComingSoonActivity.class));
        });

        MaterialButton tictactoeBtn = findViewById(R.id.tictactoeBtn);
        tictactoeBtn.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(this, ComingSoonActivity.class));
        });

        MaterialButton scoreBtn = findViewById(R.id.scoreBtn);
        scoreBtn.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(this, ScoreActivity.class));
        });

//        MaterialButton scoreBtn = findViewById(R.id.scoreBtn);
//        scoreBtn.setOnClickListener(view -> startActivity(new Intent(this, ScoreActivity.class)));
    }
}
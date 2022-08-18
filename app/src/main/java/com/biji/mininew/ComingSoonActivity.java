package com.biji.mininew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class ComingSoonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        MaterialButton back1Btn = findViewById(R.id.back1Btn);
        back1Btn.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
package com.biji.mininew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.biji.mininew.database.AppDatabase;

public class snakeActivity extends AppCompatActivity {

    private static final int FPS = 60;
    private static final int SPEED = 10;

    private GameView mGameView;

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);

        mGameView = findViewById(R.id.game_view);
        mGameView.init();

        findViewById(R.id.up_btn).setOnClickListener(v -> mGameView.setDirection(Direction.UP));
        findViewById(R.id.down_btn).setOnClickListener(v -> mGameView.setDirection(Direction.DOWN));
        findViewById(R.id.left_btn).setOnClickListener(v -> mGameView.setDirection(Direction.LEFT));
        findViewById(R.id.right_btn).setOnClickListener(v -> mGameView.setDirection(Direction.RIGHT));

        startGame();

        Button button = findViewById(R.id.restart_btn);
        button.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, snakeActivity.class));
        });

        Button button1 = findViewById(R.id.back_btn);
        button1.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void startGame()
    {
        final int delay = 1000 / FPS;
        new Thread(() -> {
            int count = 0;
            while (!mGameView.isGameOver()) {
                try {
                    Thread.sleep(delay);
                    if (count % SPEED == 0) {
                        mGameView.next();
                        mHandler.post(mGameView::invalidate);
                    }
                    count++;

                } catch (InterruptedException ignored) {

                }
            }
        } ).start();
    }
}
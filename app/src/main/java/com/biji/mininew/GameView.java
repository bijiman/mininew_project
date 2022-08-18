package com.biji.mininew;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.biji.mininew.database.AppDatabase;
import com.biji.mininew.database.Score;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;

public class GameView extends View {
    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final int MAP_SIZE = 20;
    private static final int START_X = 5;
    private static final int START_Y = 10;

    private final Point[][] mPoints = new Point[MAP_SIZE][MAP_SIZE];
    private final LinkedList<Point> mSnake = new LinkedList<>();
    private Direction mDir;

    private boolean mGameOver = false;

    private int mBoxSize;

    private Paint mPaint = new Paint();


    public Handler mHandler = new Handler(Looper.getMainLooper());

    public void init() {
        mGameOver = false;
        mBoxSize = getContext().getResources()
                .getDimensionPixelSize(R.dimen.game_size) / MAP_SIZE;
        mDir = Direction.RIGHT;
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                mPoints[i][j] = new Point(j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            Point point = getPoint(START_X + i, START_Y);
            point.type = PointType.SNAKE;
            mSnake.addFirst(point);
        }
        randomApple();
    }

    private void randomApple() {
        Random random = new Random();
        while (true) {
            Point point = getPoint(random.nextInt(MAP_SIZE),
                    random.nextInt(MAP_SIZE));
            if (point.type == PointType.EMPTY) {
                point.type = PointType.APPLE;
                break;
            }
        }
    }

    private Point getPoint(int x, int y) {
        return mPoints[y][x];
    }

    public void next() {
        Point first = mSnake.getFirst();
        Point next = getNext(first);

        switch (next.type) {
            case EMPTY:
                next.type = PointType.SNAKE;
                mSnake.addFirst(next);
                mSnake.getLast().type = PointType.EMPTY;
                mSnake.removeLast();
                break;
            case APPLE:
                next.type = PointType.SNAKE;
                mSnake.addFirst(next);
                insertData(next.toString());
                randomApple();
                break;
            case SNAKE:
                mGameOver = true;
                AppDatabase db1 = AppDatabase.getDbInstance(getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Your Score = " + db1.scoreDao().getCount());
                builder.setTitle("Game Over");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase db = AppDatabase.getDbInstance(getContext());
                        db.scoreDao().deleteAll();
                        dialog.cancel();
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getDbInstance(getContext());
                        db.scoreDao().deleteAll();
                        builder.show();
                    }
                });
                Log.d("score","skor anda : " + db1.scoreDao().getCount());

                break;
        }
    }

    private void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void setDirection(Direction dir) {
        if ((dir == Direction.LEFT || dir == Direction.RIGHT) &&
                (mDir == Direction.LEFT || mDir == Direction.RIGHT)) {
            return;
        } if ((dir == Direction.UP || dir == Direction.DOWN) &&
                (mDir == Direction.UP || mDir == Direction.DOWN)) {
            return;
        }
        mDir = dir;
    }

    private Point getNext(Point point) {
        int x = point.x;
        int y = point.y;

        switch (mDir) {
            case UP:
                y = y == 0 ? MAP_SIZE - 1 : y - 1;
                break;
            case DOWN:
                y = y == MAP_SIZE - 1 ? 0 : y + 1;
                break;
            case LEFT:
                x = x == 0 ? MAP_SIZE - 1 : x - 1;
                break;
            case RIGHT:
                x = x == MAP_SIZE - 1 ? 0: x + 1;
                break;
        }
        return getPoint(x, y);
    }

    public boolean isGameOver() {
        return mGameOver;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++){
                switch (getPoint(x, y).type) {

                    case APPLE:
                        mPaint.setColor(Color.RED);
                        break;
                    case SNAKE:
                        mPaint.setColor(Color.WHITE);
                        break;
                    case EMPTY:
                        mPaint.setColor(Color.BLACK);
                        break;
                }
                int left = mBoxSize * x;
                int right = left + mBoxSize;
                int top = mBoxSize * y;
                int bottom = top + mBoxSize;

                canvas.drawRect(left, top , right, bottom, mPaint);
            }
        }
    }

    private void insertData(final String score)
    {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getDbInstance(getContext());
                db.scoreDao().insert(new Score(score));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("score", "score inserted");
                    }
                });
            }
        });
    }
}
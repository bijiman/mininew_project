package com.biji.mininew;

import android.util.Log;

public class Point {
    public final int x,y;
    public PointType type;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = PointType.EMPTY;
    }
}

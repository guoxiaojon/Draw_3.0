package mygraph.dynamic.inter;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by jon on 2016/3/12.
 */
public abstract class Graph implements Serializable {
    public float mLeft;
    public float mRigth;
    public float mBottom;
    public float mTop;

    public Graph(float mLeft, float mTop, float mRigth, float mBottom) {
        this.mLeft = mLeft;
        this.mBottom = mBottom;
        this.mRigth = mRigth;
        this.mTop = mTop;
    }

    public abstract void draw(Canvas var1, Paint var2);

    public void move(float distanceX, float distanceY) {
        this.mLeft += distanceX;
        this.mRigth += distanceX;
        this.mTop += distanceY;
        this.mBottom += distanceY;
    }

    public Graph Selected(float x, float y) {
        return this.mLeft < x && x < this.mRigth && y > this.mTop && y < this.mBottom?this:null;
    }

    public abstract String getName();
}


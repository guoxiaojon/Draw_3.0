package mygraph.dynamic.inter;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jon on 2016/3/12.
 */
public class Rect extends mygraph.dynamic.inter.Graph {
    public Rect(float mLeft, float mTop, float mRigth, float mBottom) {
        super(mLeft, mTop, mRigth, mBottom);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(this.mLeft, this.mTop, this.mRigth, this.mBottom, paint);
    }

    public String getName() {
        return "Rect";
    }
}
package mygraph.dynamic.inter;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

/**
 * Created by jon on 2016/3/12.
 */
public class Oval extends Graph {
    public Oval(float mLeft, float mTop, float mRigth, float mBottom) {
        super(mLeft, mTop, mRigth, mBottom);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void draw(Canvas var1, Paint var2) {
        var1.drawOval(mLeft,mTop,mRigth,mBottom,var2);
    }

    @Override
    public String getName() {
        return "Oval";
    }
}

package mygraph.dynamic.inter;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

/**
 * Created by jon on 2016/3/12.
 */
public class Circle extends Graph {
    public Circle(float mLeft, float mTop, float mRigth, float mBottom) {
        super(mLeft, mTop, mRigth, mBottom);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas var1, Paint var2) {
        if((mRigth-mLeft)*(mBottom-mTop)>0)
            mBottom = mTop + (mRigth-mLeft);
        else if((mRigth-mLeft)*(mBottom-mTop)<0)
            mBottom = mTop - (mRigth-mLeft);
        var1.drawOval(mLeft,mTop,mRigth,mBottom,var2);

    }

    @Override
    public String getName() {
        return "Circle";
    }
}

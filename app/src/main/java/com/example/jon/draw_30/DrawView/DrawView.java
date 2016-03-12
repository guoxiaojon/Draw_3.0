package com.example.jon.draw_30.DrawView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import mygraph.dynamic.inter.Graph;


/**
 * Created by jon on 2016/3/11.
 */
public class DrawView extends View {
    public static List<Graph> mGraphLists = new ArrayList<Graph>();

    public Paint mPaint;
    private Bitmap mBitmap;
    public Canvas mCanvas;

    private int mScreenWidth;
    private int mScreenHeigh;

    public DrawView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(7.0f);
        mPaint.setStyle(Paint.Style.STROKE);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeigh = wm.getDefaultDisplay().getHeight();
        mBitmap = Bitmap.createBitmap(mScreenWidth,mScreenHeigh, Bitmap.Config.RGB_565);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);





    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,mPaint);

    }

    public void drawGraph(){
        mBitmap = Bitmap.createBitmap(mScreenWidth,mScreenHeigh, Bitmap.Config.RGB_565);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);
        for(int i = 0; i<mGraphLists.size(); i++){
            mGraphLists.get(i).draw(mCanvas,mPaint);
        }
        invalidate();
    }
}


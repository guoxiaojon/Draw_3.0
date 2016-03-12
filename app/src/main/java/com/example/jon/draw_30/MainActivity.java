package com.example.jon.draw_30;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.jon.draw_30.DrawView.DrawView;
import com.example.jon.draw_30.Utils.ClassLoaderUtils;
import com.example.jon.draw_30.Utils.FileUtils;

import mygraph.dynamic.inter.Graph;


public class MainActivity extends Activity implements View.OnTouchListener {

    private Button mSaveButton;
    private Button mOpenButton;
    private Button mClearButton;
    private RadioGroup mGraphGroup;

    private CheckBox mMoveButton;
    private CheckBox mChangeButton;

    private LinearLayout mDrawLayout;
    private RelativeLayout mToolLayout;
    private DrawView mDrawView;

    private boolean mMove = false;
    private boolean mChange = false;

    private float mCurrentX;
    private float mCurrentY;
    private float mStartX;
    private float mStartY;
    private float revise;

    private Graph tempGraph;
    private String mCommand = "Rect";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSaveButton = (Button) findViewById(R.id.saveButton);
        mOpenButton = (Button) findViewById(R.id.openButton);
        mGraphGroup = (RadioGroup) findViewById(R.id.graphGroup);
        mDrawLayout = (LinearLayout) findViewById(R.id.drawLayout);
        mToolLayout = (RelativeLayout) findViewById(R.id.toolLayout);
        mMoveButton = (CheckBox) findViewById(R.id.moveButton);
        mChangeButton = (CheckBox) findViewById(R.id.changeButton);
        mClearButton = (Button)findViewById(R.id.clearButton);
        mDrawView = new DrawView(this);
        mDrawView.setOnTouchListener(this);
        mDrawLayout.addView(mDrawView);

        init();


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        revise = mToolLayout.getHeight() + getStatusBarHeight();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY() - revise;
                if (mMove || mChange) {
                    for (int i = 0; i < DrawView.mGraphLists.size(); i++) {
                        tempGraph = DrawView.mGraphLists.get(i).Selected(mStartX, mStartY);
                        if (tempGraph != null)
                            break;
                    }

                } else {
                    tempGraph = ClassLoaderUtils.getGraph(this, mCommand);
                }


                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentX = event.getX();
                mCurrentY = event.getY() - revise;

                if (mMove) {
                    if (tempGraph == null)
                        break;
                    tempGraph.mLeft += (mCurrentX - mStartX);
                    tempGraph.mRigth += (mCurrentX - mStartX);
                    tempGraph.mTop += (mCurrentY - mStartY);
                    tempGraph.mBottom += (mCurrentY - mStartY);
                    mDrawView.drawGraph();
                    mStartX = event.getX();
                    mStartY = event.getY() - revise;
                } else if (!mMove && !mChange) {
                    mDrawView.drawGraph();
                    tempGraph.mLeft = mStartX;
                    tempGraph.mTop = mStartY;
                    tempGraph.mRigth = mCurrentX;
                    tempGraph.mBottom = mCurrentY;
                    tempGraph.draw(mDrawView.mCanvas, mDrawView.mPaint);


                } else if (mChange) {
                    if (tempGraph == null)
                        break;
                    if (Math.abs(tempGraph.mRigth - mStartX) > Math.abs(mStartX - tempGraph.mLeft)) {
                        tempGraph.mLeft = mCurrentX;
                        if (Math.abs(tempGraph.mTop - mStartY) > Math.abs(mStartY - tempGraph.mBottom))
                            tempGraph.mBottom = mCurrentY;
                        else
                            tempGraph.mTop = mCurrentY;

                    } else {
                        tempGraph.mRigth = mCurrentX;
                        if (Math.abs(tempGraph.mTop - mStartY) > Math.abs(mStartY - tempGraph.mBottom))
                            tempGraph.mBottom = mCurrentY;
                        else
                            tempGraph.mTop = mCurrentY;

                    }
                    mDrawView.drawGraph();


                }


                break;
            case MotionEvent.ACTION_UP:
                if (mMove || mChange)
                    break;
                else
                    DrawView.mGraphLists.add(tempGraph);

                Log.d("data", DrawView.mGraphLists.size() + "");

                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private void init() {
        mMoveButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMove = isChecked;

            }
        });

        mChangeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChange = isChecked;


            }
        });

        mGraphGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rectButton)
                    mCommand = "Rect";
                if (checkedId == R.id.circleButton)
                    mCommand = "Circle";
                if (checkedId == R.id.ovalButton)
                    mCommand = "Oval";


            }
        });
        RadioButton rectButton = (RadioButton) findViewById(R.id.rectButton);
        rectButton.setChecked(true);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.save(DrawView.mGraphLists);
            }
        });

        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.open();
                mDrawView.drawGraph();
            }
        });
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.clear();
            }
        });


    }

}

package cn.saltedfish.saltedcdd.stage.gameplay;

import android.util.DisplayMetrics;

public class LayoutHelper {
    protected int mWidth;
    protected int mHeight;
    protected double mScaleRatio;

    protected int mStartX;
    protected int mStartY;

    protected static final int sRefWidth = 1920;
    protected static final int sRefHeight = 1080;

    public LayoutHelper(DisplayMetrics pMetrics)
    {
        int width = pMetrics.widthPixels;
        int height = pMetrics.heightPixels;

        if (height > width) {
            int temp = height;
            height = width;
            width = temp;
        }

        mWidth = width;
        mHeight = height;

        double ratio = width / (double)height;
        double refRatio = sRefWidth / (double)height;

        if (ratio > refRatio)
        {
            mStartX = (int)((width - (height * refRatio)) / 2.0);
            mStartY = 0;
            mScaleRatio = height / (double)sRefHeight;
        }
        else if (ratio < refRatio)
        {
            mStartX = 0;
            mStartY = (int)((height - (width / refRatio)) / 2.0);
            mScaleRatio = width / (double)sRefWidth;
        }
        else
        {
            mStartX = 0;
            mStartY = 0;
            mScaleRatio = width / (double)sRefWidth;
        }
    }

    public int convX(int pX)
    {
        return mStartX + (int)(pX * mScaleRatio);
    }

    public int convY(int pY)
    {
        return mStartY + (int)(pY * mScaleRatio);
    }

    public int convM(int pM)
    {
        return (int)(pM * mScaleRatio);
    }
}

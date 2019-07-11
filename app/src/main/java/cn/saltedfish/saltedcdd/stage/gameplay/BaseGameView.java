package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Canvas;

public abstract class BaseGameView {
    protected int mPosX;

    protected int mPosY;

    public abstract void onDraw(Canvas pCanvas);

    public int getPosX()
    {
        return mPosX;
    }

    public void setPosX(int pPosX)
    {
        mPosX = pPosX;
    }

    public int getPosY()
    {
        return mPosY;
    }

    public void setPosY(int pPosY)
    {
        mPosY = pPosY;
    }

    public boolean onTouch(int x, int y) { return false; }
}

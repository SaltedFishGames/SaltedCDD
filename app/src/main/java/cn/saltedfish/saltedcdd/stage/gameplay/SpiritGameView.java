package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class SpiritGameView extends BaseGameView {
    protected Bitmap mBitmap;

    protected static Rect sSrc = new Rect();
    protected static Rect sDest = new Rect();
    protected static Paint sPaint = new Paint();

    protected int mWidth;
    protected int mHeight;

    protected boolean mVisible = true;
    protected boolean mInteractable;

    protected OnClickListener mOnClickListener;

    public int getWidth()
    {
        return mWidth;
    }

    public void setWidth(int pWidth)
    {
        mWidth = pWidth;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public void setHeight(int pHeight)
    {
        mHeight = pHeight;
    }

    public Bitmap getBitmap()
    {
        return mBitmap;
    }

    public void setBitmap(Bitmap pBitmap)
    {
        mBitmap = pBitmap;
    }

    static
    {
        sPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        if (mBitmap == null || !mVisible) return;
        LayoutHelper helper = GameBoardView.getInstance().getLayoutHelper();

        int x = helper.convX(mPosX);
        int y = helper.convY(mPosY);

        sSrc.set(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        sDest.set(x, y, x + helper.convM(mWidth), y + helper.convM(mHeight));

        pCanvas.drawBitmap(mBitmap, sSrc, sDest, sPaint);
    }

    public boolean isVisible()
    {
        return mVisible;
    }

    public void setVisible(boolean pVisible)
    {
        mVisible = pVisible;
    }

    public boolean isInteractable()
    {
        return mInteractable;
    }

    public void setInteractable(boolean pInteractable)
    {
        mInteractable = pInteractable;
    }

    public interface OnClickListener
    {
        void onClick(SpiritGameView pView);
    }

    public void setOnClickListener(OnClickListener pListener)
    {
        mOnClickListener = pListener;
    }

    public boolean onTouch(int x, int y)
    {
        if (mBitmap == null || !mVisible || !mInteractable || mOnClickListener == null) return false;

        LayoutHelper helper = GameBoardView.getInstance().getLayoutHelper();

        int xStart = helper.convX(mPosX);
        int yStart = helper.convY(mPosY);

        int xEnd = xStart + helper.convM(mWidth);
        int yEnd = yStart + helper.convM(mHeight);

        if (x >= xStart && x < xEnd && y >= yStart && y < yEnd)
        {
            mOnClickListener.onClick(this);
            return true;
        }
        return false;
    }
}

package cn.saltedfish.saltedcdd.stage.gameplay;

public class ExpandedSpiritGameView extends SpiritGameView {
    protected int mLExpand = 0;
    protected int mRExpand = 0;
    protected int mTExpand = 0;
    protected int mBExpand = 0;

    public ExpandedSpiritGameView(int l, int r, int t, int b)
    {
        mLExpand = l;
        mRExpand = r;
        mTExpand = t;
        mBExpand = b;
    }

    @Override
    public boolean onTouch(int x, int y)
    {
        if (mBitmap == null || !mVisible || !mInteractable || mOnClickListener == null) return false;

        LayoutHelper helper = GameBoardView.getInstance().getLayoutHelper();

        int xStart = helper.convX(mPosX - mLExpand);
        int yStart = helper.convY(mPosY - mTExpand);

        int xEnd = xStart + helper.convM(mWidth + mLExpand + mRExpand);
        int yEnd = yStart + helper.convM(mHeight + mTExpand + mBExpand);

        if (x >= xStart && x < xEnd && y >= yStart && y < yEnd)
        {
            mOnClickListener.onClick(this);
            return true;
        }
        return false;
    }
}

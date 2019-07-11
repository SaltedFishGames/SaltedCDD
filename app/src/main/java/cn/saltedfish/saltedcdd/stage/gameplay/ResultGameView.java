package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ResultGameView extends BaseGameView {
    protected boolean mVisible;

    protected final static int sLineNum = 4;

    protected String[] mNicknames = new String[sLineNum];

    protected int[] mScores = new int[sLineNum];

    protected int mRank = 0;

    protected final static int sLineHeight = 56;

    protected final static int sFontSize = 40;
    protected final static int sFontSizeBig = 128;

    protected final static int sWidth = 600;

    protected static Paint sTextPaint;

    static
    {
        sTextPaint = new Paint();
        sTextPaint.setColor(Color.WHITE);
        sTextPaint.setAntiAlias(true);
        sTextPaint.setTextSize(sFontSize);
    }

    public void setVisible(boolean pVisible)
    {
        mVisible = pVisible;
    }

    public void setLine(int index, String pNickname, int pScore)
    {
        mNicknames[index] = pNickname;
        mScores[index] = pScore;
    }

    public void setRank(int pRank)
    {
        mRank = pRank;
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        if (!mVisible) return;

        int x = getPosX();
        int y = getPosY();

        drawText(pCanvas, "游戏结束", false, x + 80, y + sFontSizeBig + 50, sFontSizeBig);

        x += 1272;
        int xEnd = x + sWidth;
        y += sFontSize;

        drawText(pCanvas, "您在本局游戏中位列第 " + mRank + " 名", false, x, y, sFontSize);
        y += sLineHeight;

        for (int i = 0; i < sLineNum; i++)
        {
            drawText(pCanvas, "#" + (i + 1) + " " + mNicknames[i], false, x, y, sFontSize);
            drawText(pCanvas, "" + mScores[i], true, xEnd, y, sFontSize);
            y += sLineHeight;
        }
    }

    protected static void drawText(Canvas pCanvas, String pStr, boolean pR2L, int pX, int pY, int pSize)
    {
        LayoutHelper helper = GameBoardView.getInstance().getLayoutHelper();

        sTextPaint.setTextSize(helper.convM(pSize));
        sTextPaint.setTextAlign(pR2L ? Paint.Align.RIGHT : Paint.Align.LEFT);
        pCanvas.drawText(
                pStr,
                helper.convX(pX),
                helper.convY(pY),
                sTextPaint
        );
    }
}

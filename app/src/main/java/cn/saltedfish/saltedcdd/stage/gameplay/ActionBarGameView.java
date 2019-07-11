package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import cn.saltedfish.saltedcdd.R;

public class ActionBarGameView extends BaseGameView {
    protected SpiritGameView mShowCardButton;

    protected SpiritGameView mPassButton;

    protected SpiritGameView mHintButton;

    protected boolean mVisible;

    protected Bitmap mShowCardNormal;
    protected Bitmap mShowCardDisabled;

    protected Bitmap mPassNormal;
    protected Bitmap mPassDisabled;

    protected Bitmap mHintNormal;
    protected Bitmap mHintDisabled;

    protected static final int sButtonWidth = 192;
    protected static final int sButtonHeight = 64;

    protected IActionListener mActionListener;

    public boolean isVisible()
    {
        return mVisible;
    }

    public ActionBarGameView()
    {
        mShowCardButton = new ExpandedSpiritGameView(0, 0, 24, 24);
        mPassButton = new ExpandedSpiritGameView(0, 0, 24, 24);
        mHintButton = new ExpandedSpiritGameView(0, 0, 24, 24);

        mShowCardButton.setPosX(LayoutHelper.sRefWidth / 2 - (int)(2 * sButtonWidth));
        mShowCardButton.setPosY(695);
        mShowCardButton.setWidth(sButtonWidth);
        mShowCardButton.setHeight(sButtonHeight);

        mPassButton.setPosX(LayoutHelper.sRefWidth / 2 - (int)(0.5 * sButtonWidth));
        mPassButton.setPosY(695);
        mPassButton.setWidth(sButtonWidth);
        mPassButton.setHeight(sButtonHeight);

        mHintButton.setPosX(LayoutHelper.sRefWidth / 2 + (int)(1 * sButtonWidth));
        mHintButton.setPosY(695);
        mHintButton.setWidth(sButtonWidth);
        mHintButton.setHeight(sButtonHeight);

        GameBitmapFactory factory = GameBoardView.getInstance().getBitmapFactory();
        mShowCardNormal = factory.getBitmap(R.drawable.showcard_normal);
        mShowCardDisabled = factory.getBitmap(R.drawable.showcard_disabled);

        mPassNormal = factory.getBitmap(R.drawable.pass_normal);
        mPassDisabled = factory.getBitmap(R.drawable.pass_disabled);

        mHintNormal = factory.getBitmap(R.drawable.hint_normal);
        mHintDisabled = factory.getBitmap(R.drawable.hint_disabled);

        mShowCardButton.setOnClickListener(new SpiritGameView.OnClickListener() {
            @Override
            public void onClick(SpiritGameView pView)
            {
                if (mActionListener != null)
                {
                    mActionListener.onShowCard();
                }
            }
        });

        mPassButton.setOnClickListener(new SpiritGameView.OnClickListener() {
            @Override
            public void onClick(SpiritGameView pView)
            {
                if (mActionListener != null)
                {
                    mActionListener.onPass();
                }
            }
        });

        mHintButton.setOnClickListener(new SpiritGameView.OnClickListener() {
            @Override
            public void onClick(SpiritGameView pView)
            {
                if (mActionListener != null)
                {
                    mActionListener.onHint();
                }
            }
        });
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        if (!mVisible) return;

        mShowCardButton.onDraw(pCanvas);
        mPassButton.onDraw(pCanvas);
        mHintButton.onDraw(pCanvas);
    }

    public void show(boolean pShowCard, boolean pPass, boolean pHint)
    {
        Bitmap bitmap = pShowCard ? mShowCardNormal : mShowCardDisabled;
        mShowCardButton.setBitmap(bitmap);

        bitmap = pPass ? mPassNormal : mPassDisabled;
        mPassButton.setBitmap(bitmap);

        bitmap = pHint ? mHintNormal : mHintDisabled;
        mHintButton.setBitmap(bitmap);

        mVisible = true;

        mShowCardButton.setInteractable(pShowCard);
        mPassButton.setInteractable(pPass);
        mHintButton.setInteractable(pHint);
    }

    public void hide()
    {
        mVisible = false;
    }

    @Override
    public boolean onTouch(int x, int y)
    {
        if (!mVisible) return false;

        if (mShowCardButton.onTouch(x, y)) return true;
        if (mPassButton.onTouch(x, y)) return true;
        if (mHintButton.onTouch(x, y)) return true;
        return false;
    }

    public void setActionListener(IActionListener pActionListener)
    {
        mActionListener = pActionListener;
    }

    public interface IActionListener
    {
        void onPass();
        void onShowCard();
        void onHint();
    }
}

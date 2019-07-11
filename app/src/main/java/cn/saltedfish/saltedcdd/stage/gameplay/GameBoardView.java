package cn.saltedfish.saltedcdd.stage.gameplay;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;

import static android.view.MotionEvent.ACTION_UP;

public class GameBoardView extends View {
    protected LayoutHelper mLayoutHelper;

    protected GameBitmapFactory mBitmapFactory;

    protected static WeakReference<GameBoardView> sInstance;

    protected PlayerGameView[] mPlayerViews = new PlayerGameView[4];

    protected ActionBarGameView mActionBarGameView;

    protected boolean mBlockTouchEvent = false;

    public GameBoardView(Context context)
    {
        super(context);
        initialize(context);
    }

    public GameBoardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize(context);
    }

    public GameBoardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    protected void initialize(Context pContext)
    {
        sInstance = new WeakReference<>(this);

        mBitmapFactory = new GameBitmapFactory(pContext.getResources());

        mPlayerViews[0] = new PlayerGameView(
                LayoutHelper.sRefWidth / 2 - CardGameView.sBigWidth / 2,
                LayoutHelper.sRefHeight - CardGameView.sBigHeight,
                CardGroupGameView.ShowType.CLR,
                CardGameView.ShowType.BigFront,
                (int)(CardGameView.sBigWidth * 0.75),

                LayoutHelper.sRefWidth / 2 - CardGameView.sSmallWidth / 2,
                LayoutHelper.sRefHeight - CardGameView.sBigHeight - 100 - CardGameView.sSmallHeight,
                CardGroupGameView.ShowType.CLR,
                CardGameView.ShowType.SmallFront,
                (int)(CardGameView.sSmallWidth * 1.1)
        );

        mPlayerViews[1] = new PlayerGameView(
                LayoutHelper.sRefWidth - CardGameView.sBigWidth - 45,
                LayoutHelper.sRefHeight / 2 - CardGameView.sBigHeight / 2 - 80,
                CardGroupGameView.ShowType.CUD,
                CardGameView.ShowType.BigBack,
                (int)(CardGameView.sBigHeight * 0.15),

                LayoutHelper.sRefWidth - CardGameView.sBigWidth - CardGameView.sSmallWidth - 100,
                LayoutHelper.sRefHeight / 2 - CardGameView.sSmallHeight / 2 - 80,
                CardGroupGameView.ShowType.RL,
                CardGameView.ShowType.SmallFront,
                (int)(CardGameView.sSmallWidth * 1.1)
        );

        mPlayerViews[2] = new PlayerGameView(
                LayoutHelper.sRefWidth / 2 - CardGameView.sBigWidth / 2,
                -(int)(CardGameView.sBigHeight * 0.5),
                CardGroupGameView.ShowType.CLR,
                CardGameView.ShowType.BigBack,
                (int)(CardGameView.sBigWidth * 0.45),

                LayoutHelper.sRefWidth / 2 - CardGameView.sSmallWidth / 2,
                (int)(CardGameView.sBigHeight * 0.5) + 50,
                CardGroupGameView.ShowType.CLR,
                CardGameView.ShowType.SmallFront,
                (int)(CardGameView.sSmallWidth * 1.1)
        );

        mPlayerViews[3] = new PlayerGameView(
                45,
                LayoutHelper.sRefHeight / 2 - CardGameView.sBigHeight / 2 - 80,
                CardGroupGameView.ShowType.CUD,
                CardGameView.ShowType.BigBack,
                (int)(CardGameView.sBigHeight * 0.15),

                CardGameView.sBigWidth + 100,
                LayoutHelper.sRefHeight / 2 - CardGameView.sSmallHeight / 2 - 80,
                CardGroupGameView.ShowType.LR,
                CardGameView.ShowType.SmallFront,
                (int)(CardGameView.sSmallWidth * 1.1)
        );

        mActionBarGameView = new ActionBarGameView();
        mActionBarGameView.hide();

        mPlayerViews[0].getHandCardView().setAllowSelect(true);

        mPlayerViews[0].setPassIndicatorPosition(LayoutHelper.sRefWidth / 2 - PlayerGameView.sPassIndicatorWidth / 2, LayoutHelper.sRefHeight - CardGameView.sBigHeight - 100 - PlayerGameView.sPassIndicatorHeight);
        mPlayerViews[1].setPassIndicatorPosition(LayoutHelper.sRefWidth - CardGameView.sBigWidth - PlayerGameView.sPassIndicatorWidth - 100, LayoutHelper.sRefHeight / 2 - PlayerGameView.sPassIndicatorHeight / 2 - 80);
        mPlayerViews[2].setPassIndicatorPosition(LayoutHelper.sRefWidth / 2 - PlayerGameView.sPassIndicatorWidth / 2, (int)(CardGameView.sBigHeight * 0.5) + 50);
        mPlayerViews[3].setPassIndicatorPosition(CardGameView.sBigWidth + 100, LayoutHelper.sRefHeight / 2 - PlayerGameView.sPassIndicatorHeight / 2 - 80);

        invalidate();
    }

    public void setLayoutHelper(LayoutHelper pLayoutHelper)
    {
        mLayoutHelper = pLayoutHelper;
    }

    public LayoutHelper getLayoutHelper()
    {
        return mLayoutHelper;
    }

    @Override
    protected void onDraw(Canvas pCanvas)
    {
        for (int i = 0; i < mPlayerViews.length; i++)
        {
            if (mPlayerViews[i] != null)
            {
                mPlayerViews[i].onDraw(pCanvas);
            }
        }

        mActionBarGameView.onDraw(pCanvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mBlockTouchEvent) return true;
        if (ev.getAction() == ACTION_UP)
        {
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            if (mPlayerViews[0].onTouch(x, y))
            {
                invalidate();
            }
            else if (mActionBarGameView.onTouch(x, y))
            {
                invalidate();
            }
        }
        return true;
    }

    public static GameBoardView getInstance()
    {
        if (sInstance != null)
        {
            return sInstance.get();
        }
        else
        {
            return null;
        }
    }

    public GameBitmapFactory getBitmapFactory()
    {
        return mBitmapFactory;
    }

    public ActionBarGameView getActionBarGameView()
    {
        return mActionBarGameView;
    }

    public PlayerGameView getPlayerGameView(int index)
    {
        return mPlayerViews[index];
    }

    public void setBlockTouchEvent(boolean pBlockTouchEvent)
    {
        mBlockTouchEvent = pBlockTouchEvent;
    }

    public void onDestroy()
    {
        mBitmapFactory.clear();
    }
}

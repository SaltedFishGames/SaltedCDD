package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Canvas;
import android.util.Log;

import java.util.List;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.card.Card;

public class PlayerGameView extends BaseGameView {
    protected PlayerInfoGameView mPlayerInfoView;

    protected CardGroupGameView mHandCardView;

    protected CardGroupGameView mShowCardView;

    protected SpiritGameView mPassIndicator;

    public final static int sPassIndicatorWidth = 113;
    public final static int sPassIndicatorHeight = 64;

    public PlayerGameView(
            int handX, int handY, 
            CardGroupGameView.ShowType pHandShowType, CardGameView.ShowType pHandCardShowType, 
            int pHandGap,

            int showX, int showY,
            CardGroupGameView.ShowType pShowShowType, CardGameView.ShowType pShowCardShowType,
            int pShowGap,

            int pAvatarX, int pAvatarY,
            int pNicknameX, int pNicknameY,
            PlayerInfoGameView.NicknameShowType pNicknameShowType
    )
    {
        mHandCardView = new CardGroupGameView(handX, handY, pHandShowType, pHandCardShowType, pHandGap);
        mShowCardView = new CardGroupGameView(showX, showY, pShowShowType, pShowCardShowType, pShowGap);
        mPlayerInfoView = new PlayerInfoGameView(pAvatarX, pAvatarY, pNicknameX, pNicknameY, pNicknameShowType);

        mPassIndicator = new SpiritGameView();
        mPassIndicator.setVisible(false);
        mPassIndicator.setWidth(sPassIndicatorWidth);
        mPassIndicator.setHeight(sPassIndicatorHeight);
        mPassIndicator.setBitmap(GameBoardView.getInstance().getBitmapFactory().getBitmap(R.drawable.pass_indicator));
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        mHandCardView.onDraw(pCanvas);
        mShowCardView.onDraw(pCanvas);

        mPlayerInfoView.onDraw(pCanvas);

        mPassIndicator.onDraw(pCanvas);
    }

    public void setHandCards(List<Card> pCards)
    {
        mHandCardView.setCards(pCards);
    }

    public void setShowCardAction(List<Card> pCards)
    {
        mShowCardView.setCards(pCards);
    }

    public void clearShowCards()
    {
        mShowCardView.removeAll();
    }

    public CardGroupGameView getHandCardView()
    {
        return mHandCardView;
    }

    public CardGroupGameView getShowCardView()
    {
        return mShowCardView;
    }

    public PlayerInfoGameView getPlayerInfoView()
    {
        return mPlayerInfoView;
    }

    public void removeLastAction()
    {
        mShowCardView.removeAll();
        mPassIndicator.setVisible(false);
    }

    public void setPassIndicatorPosition(int pX, int pY)
    {
        mPassIndicator.setPosX(pX);
        mPassIndicator.setPosY(pY);
    }

    public void setPassAction()
    {
        mPassIndicator.setVisible(true);
    }

    @Override
    public boolean onTouch(int x, int y)
    {
        if (mHandCardView.onTouch(x, y))
        {
            return true;
        }
        else if (mPlayerInfoView.onTouch(x, y))
        {
            return true;
        }
        return false;

    }
}

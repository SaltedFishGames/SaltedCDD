package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class CardGroupGameView extends BaseGameView implements SpiritGameView.OnClickListener{
    protected List<CardGameView> mCardViews = new ArrayList<>(13);

    protected ShowType mShowType;

    protected CardGameView.ShowType mCardShowType;

    protected int mCardGap;

    protected boolean mAllowSelect;

    public CardGroupGameView(int posX, int posY, ShowType pShowType, CardGameView.ShowType pCardShowType, int pCardGap)
    {
        setPosX(posX);
        setPosY(posY);
        mShowType = pShowType;
        mCardShowType = pCardShowType;
        mCardGap = pCardGap;
    }

    public void setCards(List<Card> pCards)
    {
        removeAll();

        if (pCards == null) return;

        int x = getPosX();
        int y = getPosY();

        switch (mShowType)
        {
            case CUD:
                y -= (int)(mCardGap * (double)(pCards.size() - 1) / 2);
                break;
            case CLR:
                x -= (int)(mCardGap * (double)(pCards.size() - 1) / 2);
                break;
            case RL:
                x -= mCardGap * (pCards.size() - 1);

        }

        for (Card c : pCards)
        {
            CardGameView cardView = new CardGameView(c, mCardShowType);
            cardView.setPosX(x);
            cardView.setPosY(y);
            cardView.setInteractable(true);
            cardView.setOnClickListener(this);
            mCardViews.add(cardView);

            switch (mShowType)
            {
                case LR:
                case CLR:
                case RL:
                    x += mCardGap;
                    break;
                case CUD:
                    y += mCardGap;
                    break;
            }
        }
    }

    public void removeAll()
    {
        mCardViews.clear();
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        for (SpiritGameView card : mCardViews)
        {
            card.onDraw(pCanvas);
        }
    }

    public void setAllowSelect(boolean pAllowSelect)
    {
        mAllowSelect = pAllowSelect;
    }

    @Override
    public void onClick(SpiritGameView pView)
    {
        if (!mAllowSelect) return;

        for (CardGameView cardView : mCardViews)
        {
            if (cardView == pView)
            {
                cardView.setSelected(!cardView.isSelected());
            }
        }
    }

    @Override
    public boolean onTouch(int x, int y)
    {
        for (int i = mCardViews.size() - 1; i >= 0; i--)
        {
            if (mCardViews.get(i).onTouch(x, y))
            {
                return true;
            }
        }
        return false;
    }

    public void deselectAll()
    {
        for (CardGameView cardGameView : mCardViews)
        {
            cardGameView.setSelected(false);
        }
    }

    public void selectAll(List<Card> pCards)
    {
        for (CardGameView cardGameView : mCardViews)
        {
            cardGameView.setSelected(pCards.contains(cardGameView.getCard()));
        }
    }

    public List<Card> getSelectedCards()
    {
        List<Card> cards = new ArrayList<>();
        for (CardGameView cardGameView : mCardViews)
        {
            if (cardGameView.isSelected())
            {
                cards.add(cardGameView.getCard());
            }
        }
        return cards;
    }

    public enum ShowType
    {
        LR,
        RL,
        CUD,
        CLR
    }
}

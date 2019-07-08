package cn.saltedfish.saltedcdd.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;

public class Player {
    protected ArrayList<Card> mCards = new ArrayList<>();
    protected int mId;

    public int getId()
    {
        return mId;
    }

    public void setId(int pId)
    {
        mId = pId;
    }

    public List<Card> cards()
    {
        return mCards;
    }

    public boolean hasCard(ECardNumber pNumber, ECardSuit pSuit)
    {
        for (Card card : mCards)
        {
            if (card.equals(pNumber, pSuit))
            {
                return true;
            }
        }
        return false;
    }

    public boolean hasCards(Collection<Card> pCards)
    {
        return mCards.containsAll(pCards);
    }

    public void takeAwayCards(Collection<Card> pCards)
    {
        mCards.removeAll(pCards);
    }

    public int getCardCount()
    {
        return mCards.size();
    }
}

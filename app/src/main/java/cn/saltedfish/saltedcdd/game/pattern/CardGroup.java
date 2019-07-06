package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class CardGroup implements Comparable<CardGroup>{
    public ArrayList<Card> mCards;

    public EPatternType mType = EPatternType.Unrecognized;

    public Card mCriticalCard;

    public CardGroup(Card[] pCards)
    {
        mCards = new ArrayList<>();
        for (Card card : pCards)
        {
            mCards.add(card);
        }
    }

    public CardGroup(List<Card> pCards)
    {
        mCards = new ArrayList<>(pCards);
    }

    public boolean isSameType(CardGroup pGroup)
    {
        if (pGroup == null)
        {
            return false;
        }
        return pGroup.mType == mType;
    }

    @Override
    public int compareTo(CardGroup pGroup)
    {
        if (isSameType(pGroup))
        {
            return mCriticalCard.compareTo(pGroup.mCriticalCard);
        }
        return 0;
    }

    public void sort()
    {
        Collections.sort(mCards);
    }

    public int count()
    {
        return mCards.size();
    }

    public Card get(int index)
    {
        return mCards.get(index);
    }
}

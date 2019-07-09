package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class CardGroup implements Comparable<CardGroup>{
    protected ArrayList<Card> mCards;

    protected EPatternType mType = EPatternType.Unrecognized;

    protected Card mCriticalCard;

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

    public boolean isComparableTo(CardGroup pGroup)
    {
        if (pGroup == null)
        {
            return false;
        }
        return pGroup.cards().size() == mCards.size();
    }

    @Override
    public int compareTo(CardGroup pGroup)
    {
        if (isComparableTo(pGroup))
        {
            int result = mType.compareWeight(pGroup.mType);
            if (result == 0)
            {
                return getCriticalCard().compareTo(pGroup.getCriticalCard());
            }
            return result;
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

    public void recognize()
    {
        mType = EPatternType.Unrecognized;
        PatternRecognizer.recognize(this);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mCards.size(); i++)
        {
            if (i > 0)
            {
                sb.append(", ");
            }
            sb.append(mCards.get(i).toString());
        }
        return sb.toString();
    }

    public ArrayList<Card> cards()
    {
        return mCards;
    }

    public EPatternType getType()
    {
        return mType;
    }

    public void setType(EPatternType pType)
    {
        mType = pType;
    }

    public Card getCriticalCard()
    {
        return mCriticalCard;
    }

    public void setCriticalCard(Card pCriticalCard)
    {
        mCriticalCard = pCriticalCard;
    }
}

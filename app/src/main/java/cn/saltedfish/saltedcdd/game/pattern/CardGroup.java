package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;

public abstract class CardGroup implements Comparable<CardGroup>{
    public ArrayList<Card> mCards;

    public EPatternType mType = EPatternType.Unrecognized;

    public Card mCriticalCard;

    public CardGroup(Collection<Card> pCards)
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
    public abstract int compareTo(CardGroup pGroup);

    public abstract void sort();
}

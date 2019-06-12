package cn.saltedfish.saltedcdd.model.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.saltedfish.saltedcdd.CardEnum;
import cn.saltedfish.saltedcdd.CardNumberEnum;
import cn.saltedfish.saltedcdd.CardSuitEnum;

public class CardGroup implements Iterable<CardEnum>{
    protected HashMap<CardNumberEnum, SingleNumberCardGroup> mCards = new HashMap<>(16);

    public CardGroup()
    {
    }

    public CardGroup(List<CardEnum> cards)
    {
        addAll(cards);
    }

    public SingleNumberCardGroup getGroupByNumber(CardNumberEnum number)
    {
        return mCards.get(number);
    }

    protected SingleNumberCardGroup acquireGroupByNumber(CardNumberEnum number)
    {
        SingleNumberCardGroup group = getGroupByNumber(number);
        if (group == null)
        {
            group = new SingleNumberCardGroup(number);
            mCards.put(number, group);
        }
        return group;
    }

    public void add(CardEnum card)
    {
        acquireGroupByNumber(card.getNumber()).add(card.getSuit());
    }

    public void add(CardGroup group)
    {

    }

    public void addAll(Collection<CardEnum> cards)
    {
        for (CardEnum card : cards)
        {
            SingleNumberCardGroup group = acquireGroupByNumber(card.getNumber());
            group.add(card.getSuit());
        }
    }

    public boolean contains(CardEnum card)
    {
        SingleNumberCardGroup group = getGroupByNumber(card.getNumber());
        return group != null && group.contains(card.getSuit());
    }

    public boolean contains(CardNumberEnum number)
    {
        return mCards.containsKey(number);
    }

    public boolean remove(CardEnum card)
    {
        SingleNumberCardGroup group = getGroupByNumber(card.getNumber());
        if (group == null)
        {
            return false;
        }
        else
        {
            return group.remove(card.getSuit());
        }
    }

    public void clear()
    {
        mCards.clear();
    }

    @Override
    public Iterator<CardEnum> iterator()
    {
        return new CardIterator(this);
    }

    public class CardIterator implements Iterator<CardEnum>
    {
        public Iterator<Map.Entry<CardNumberEnum, SingleNumberCardGroup>> mInternalIterator;
        public Iterator<CardSuitEnum> mSuitIterator;

        public Map.Entry<CardNumberEnum, SingleNumberCardGroup> mCurrentEntry = null;

        public CardIterator(CardGroup group)
        {
            mInternalIterator = group.mCards.entrySet().iterator();
        }

        @Override
        public boolean hasNext()
        {
            return mSuitIterator.hasNext() || mInternalIterator.hasNext();
        }

        @Override
        public CardEnum next()
        {
            if (mSuitIterator.hasNext())
            {
                return CardEnum.getCard(mCurrentEntry.getKey(), mSuitIterator.next());
            }
            else if (mInternalIterator.hasNext())
            {
                mCurrentEntry = mInternalIterator.next();
                mSuitIterator = mCurrentEntry.getValue().iterator();
                return CardEnum.getCard(mCurrentEntry.getKey(), mSuitIterator.next());
            }
            else
            {
                return null;
            }
        }
    }
}

package cn.saltedfish.saltedcdd.model.game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.saltedfish.saltedcdd.CardNumberEnum;
import cn.saltedfish.saltedcdd.CardSuitEnum;

public class SingleNumberCardGroup implements Iterable<CardSuitEnum>{
    protected CardNumberEnum mCardNumber;
    protected HashMap<CardSuitEnum, Integer> mSuits = new HashMap<>();
    protected int mTotalCount = 0;

    public SingleNumberCardGroup(CardNumberEnum number)
    {
        mCardNumber = number;
    }

    public CardNumberEnum getCardNumber()
    {
        return mCardNumber;
    }

    public void add(CardSuitEnum suit)
    {
        mSuits.put(suit, getCountBySuit(suit) + 1);
        ++mTotalCount;
    }

    public int getCountBySuit(CardSuitEnum suit)
    {
        Integer suitCount = mSuits.get(suit);
        return suitCount == null ? 0 : suitCount;
    }

    public int getTotalCount()
    {
        return mTotalCount;
    }

    public boolean contains(CardSuitEnum suit)
    {
        return getCountBySuit(suit) > 0;
    }

    public boolean remove(CardSuitEnum suit)
    {
        int count = getCountBySuit(suit);
        if (count == 0)
        {
            return false;
        }
        else if (count == 1)
        {
            mSuits.remove(suit);
        }
        else
        {
            mSuits.put(suit, count - 1);
        }
        --mTotalCount;
        return true;
    }

    public void clear()
    {
        mSuits.clear();
    }

    @Override
    public Iterator<CardSuitEnum> iterator()
    {
        return new SuitIterator(this);
    }

    public class SuitIterator implements Iterator<CardSuitEnum>
    {
        public Iterator<Map.Entry<CardSuitEnum, Integer>> mInternalIterator;
        public int mCurrentCounter = 0;
        public Map.Entry<CardSuitEnum, Integer> mCurrentEntry = null;
        public SuitIterator(SingleNumberCardGroup group)
        {
            mInternalIterator = group.mSuits.entrySet().iterator();
        }

        @Override
        public boolean hasNext()
        {
            return mCurrentCounter > 0 || mInternalIterator.hasNext();
        }

        @Override
        public CardSuitEnum next()
        {
            if (mCurrentCounter > 0)
            {
                mCurrentCounter--;
                return mCurrentEntry.getKey();
            }
            else if (mInternalIterator.hasNext())
            {
                mCurrentEntry = mInternalIterator.next();
                mCurrentCounter = mCurrentEntry.getValue() - 1;
                return mCurrentEntry.getKey();
            }
            else
            {
                return null;
            }
        }
    }
}

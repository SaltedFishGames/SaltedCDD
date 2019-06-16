package cn.saltedfish.cdd.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NumberedSuitCollection {
    protected CardNumberEnum mCardNumber;
    protected int[] mSuitCounter;

    protected static CardSuitEnum[] mIndexToSuitMap;
    protected static HashMap<CardSuitEnum, Integer> mSuitToIndexMap;

    static
    {
        mIndexToSuitMap = CardSuitEnum.values();
        mSuitToIndexMap = new HashMap<>(mIndexToSuitMap.length);
        for (int i = 0; i < mIndexToSuitMap.length; i++)
        {
            mSuitToIndexMap.put(mIndexToSuitMap[i], i);
        }
    }

    protected static int getIndexForSuit(CardSuitEnum suit)
    {
        return mSuitToIndexMap.get(suit);
    }

    protected static CardSuitEnum getSuitForIndex(int index)
    {
        return mIndexToSuitMap[index];
    }

    public NumberedSuitCollection(CardNumberEnum number)
    {
        mCardNumber = number;
        mSuitCounter = new int[mIndexToSuitMap.length];
    }

    public CardNumberEnum getCardNumber()
    {
        return mCardNumber;
    }

    public void add(CardSuitEnum suit)
    {
        mSuitCounter[getIndexForSuit(suit)]++;
    }

    public void addAll(NumberedSuitCollection collection)
    {
        for (int i = 0; i < mSuitCounter.length; i++)
        {
            mSuitCounter[i] += collection.mSuitCounter[i];
        }
    }

    public int getCountBySuit(CardSuitEnum suit)
    {
        return mSuitCounter[getIndexForSuit(suit)];
    }

    public int getTotalCount()
    {
        int totalCount = 0;
        for (int i = 0; i < mSuitCounter.length; i++)
        {
            totalCount += mSuitCounter[i];
        }
        return totalCount;
    }

    public boolean contains(CardSuitEnum suit)
    {
        return getCountBySuit(suit) > 0;
    }

    public boolean remove(CardSuitEnum suit)
    {
        int index = getIndexForSuit(suit);
        int suitCount = mSuitCounter[index];
        if (suitCount == 0)
        {
            return false;
        }
        else
        {
            mSuitCounter[index] = suitCount - 1;
            return true;
        }
    }

    public void clear()
    {
        for (int i = 0; i < mSuitCounter.length; i++)
        {
            mSuitCounter[i] = 0;
        }
    }

    public Collection<CardSuitEnum> suits()
    {
       ArrayList<CardSuitEnum> result = new ArrayList<>(getTotalCount());

        int counter = 0;
        for (int index = 0; index < mSuitCounter.length; index++)
        {
            CardSuitEnum suit = getSuitForIndex(index);
            for (int count = mSuitCounter[index]; count > 0; count--)
            {
                result.add(suit);
            }
        }
        return result;
    }
}

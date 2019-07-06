package cn.saltedfish.saltedcdd.game.card;

public class Card implements Comparable<Card> {
    protected ECardNumber mNumber;
    protected ECardSuit mSuit;

    protected int mId;

    public Card(ECardNumber pNumber, ECardSuit pSuit)
    {
        mNumber = pNumber;
        mSuit = pSuit;
    }

    public ECardNumber getNumber()
    {
        return mNumber;
    }

    public ECardSuit getSuit()
    {
        return mSuit;
    }

    public int getId()
    {
        return mId;
    }

    public void setId(int pId)
    {
        mId = pId;
    }

    public boolean equals(ECardNumber pNumber, ECardSuit pSuit)
    {
        return mNumber == pNumber && mSuit == pSuit;
    }

    @Override
    public int compareTo(Card o)
    {
        int result = mNumber.compareWeight(o.mNumber);
        if (result == 0)
        {
            result = mSuit.compareWeight(o.mSuit);
        }
        return result;
    }

    @Override
    public String toString()
    {
        return mSuit.getName() + mNumber.getName();
    }
}

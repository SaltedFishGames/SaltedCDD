package cn.saltedfish.saltedcdd.game.card;

public class Card {
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
}

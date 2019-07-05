package cn.saltedfish.saltedcdd.game.card;

public class Card {
    protected CardNumber mNumber;
    protected CardSuit mSuit;

    protected int mId;

    public Card(CardNumber pNumber, CardSuit pSuit)
    {
        mNumber = pNumber;
        mSuit = pSuit;
    }

    public CardNumber getNumber()
    {
        return mNumber;
    }

    public CardSuit getSuit()
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
}

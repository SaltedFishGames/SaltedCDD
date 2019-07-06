package cn.saltedfish.saltedcdd.game.card;

public enum ECardSuit {
    SPADE(1, "♠"), // 黑桃
    HEART(2, "♥"), // 红桃
    CLUB(3, "♣"), // 梅花
    DIAMOND(4, "♦"); // 方片

    int mWeight;
    String mName;

    ECardSuit(int pWeight, String pName)
    {
        mWeight = pWeight;
        mName = pName;
    }

    int compareWeight(ECardSuit pOther)
    {
        return mWeight - pOther.mWeight;
    }

    public String getName()
    {
        return mName;
    }

    public static ECardSuit fromName(String pName)
    {
        for (ECardSuit suit : ECardSuit.values())
        {
            if (suit.getName().equals(pName))
            {
                return suit;
            }
        }
        return null;
    }
}

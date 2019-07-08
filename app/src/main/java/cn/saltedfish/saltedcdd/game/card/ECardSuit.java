package cn.saltedfish.saltedcdd.game.card;

public enum ECardSuit {
    DIAMOND(1, "♦"), // 方片
    CLUB(2, "♣"), // 梅花
    HEART(3, "♥"), // 红桃
    SPADE(4, "♠"); // 黑桃

    int mWeight;
    String mName;

    ECardSuit(int pWeight, String pName)
    {
        mWeight = pWeight;
        mName = pName;
    }

    public int compareWeight(ECardSuit pOther)
    {
        return mWeight - pOther.mWeight;
    }

    public String getName()
    {
        return mName;
    }

    public int getWeight()
    {
        return mWeight;
    }

    public static ECardSuit fromString(String pName)
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

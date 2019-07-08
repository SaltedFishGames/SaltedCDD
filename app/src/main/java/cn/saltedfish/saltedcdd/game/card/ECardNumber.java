package cn.saltedfish.saltedcdd.game.card;

public enum ECardNumber {
    NUM_3(1, "3"),
    NUM_4(2, "4"),
    NUM_5(3, "5"),
    NUM_6(4, "6"),
    NUM_7(5, "7"),
    NUM_8(6, "8"),
    NUM_9(7, "9"),
    NUM_10(8, "10"),
    NUM_J(9, "J"),
    NUM_Q(10, "Q"),
    NUM_K(11, "K"),
    NUM_A(12, "A"),
    NUM_2(13, "2");

    int mWeight;
    String mName;

    ECardNumber(int pWeight, String pName)
    {
        mWeight = pWeight;
        mName = pName;
    }

    int compareWeight(ECardNumber pOther)
    {
        return mWeight - pOther.mWeight;
    }

    public int getWeight()
    {
        return mWeight;
    }

    public String getName()
    {
        return mName;
    }

    public static ECardNumber fromString(String pName)
    {
        for (ECardNumber number : ECardNumber.values())
        {
            if (number.getName().equals(pName))
            {
                return number;
            }
        }
        return null;
    }
}

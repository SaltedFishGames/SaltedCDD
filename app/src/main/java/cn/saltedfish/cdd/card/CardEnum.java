package cn.saltedfish.cdd.card;

public enum CardEnum {
    CLUB_3(CardNumberEnum.NUM_3, CardSuitEnum.CLUB),
    CLUB_4(CardNumberEnum.NUM_4, CardSuitEnum.CLUB),
    CLUB_5(CardNumberEnum.NUM_5, CardSuitEnum.CLUB),
    CLUB_6(CardNumberEnum.NUM_6, CardSuitEnum.CLUB),
    CLUB_7(CardNumberEnum.NUM_7, CardSuitEnum.CLUB),
    CLUB_8(CardNumberEnum.NUM_8, CardSuitEnum.CLUB),
    CLUB_9(CardNumberEnum.NUM_9, CardSuitEnum.CLUB),
    CLUB_10(CardNumberEnum.NUM_10, CardSuitEnum.CLUB),
    CLUB_J(CardNumberEnum.NUM_J, CardSuitEnum.CLUB),
    CLUB_Q(CardNumberEnum.NUM_Q, CardSuitEnum.CLUB),
    CLUB_K(CardNumberEnum.NUM_K, CardSuitEnum.CLUB),
    CLUB_A(CardNumberEnum.NUM_A, CardSuitEnum.CLUB),
    CLUB_2(CardNumberEnum.NUM_2, CardSuitEnum.CLUB),

    SPADE_3(CardNumberEnum.NUM_3, CardSuitEnum.SPADE),
    SPADE_4(CardNumberEnum.NUM_4, CardSuitEnum.SPADE),
    SPADE_5(CardNumberEnum.NUM_5, CardSuitEnum.SPADE),
    SPADE_6(CardNumberEnum.NUM_6, CardSuitEnum.SPADE),
    SPADE_7(CardNumberEnum.NUM_7, CardSuitEnum.SPADE),
    SPADE_8(CardNumberEnum.NUM_8, CardSuitEnum.SPADE),
    SPADE_9(CardNumberEnum.NUM_9, CardSuitEnum.SPADE),
    SPADE_10(CardNumberEnum.NUM_10, CardSuitEnum.SPADE),
    SPADE_J(CardNumberEnum.NUM_J, CardSuitEnum.SPADE),
    SPADE_Q(CardNumberEnum.NUM_Q, CardSuitEnum.SPADE),
    SPADE_K(CardNumberEnum.NUM_K, CardSuitEnum.SPADE),
    SPADE_A(CardNumberEnum.NUM_A, CardSuitEnum.SPADE),
    SPADE_2(CardNumberEnum.NUM_2, CardSuitEnum.SPADE),

    DIAMOND_3(CardNumberEnum.NUM_3, CardSuitEnum.DIAMOND),
    DIAMOND_4(CardNumberEnum.NUM_4, CardSuitEnum.DIAMOND),
    DIAMOND_5(CardNumberEnum.NUM_5, CardSuitEnum.DIAMOND),
    DIAMOND_6(CardNumberEnum.NUM_6, CardSuitEnum.DIAMOND),
    DIAMOND_7(CardNumberEnum.NUM_7, CardSuitEnum.DIAMOND),
    DIAMOND_8(CardNumberEnum.NUM_8, CardSuitEnum.DIAMOND),
    DIAMOND_9(CardNumberEnum.NUM_9, CardSuitEnum.DIAMOND),
    DIAMOND_10(CardNumberEnum.NUM_10, CardSuitEnum.DIAMOND),
    DIAMOND_J(CardNumberEnum.NUM_J, CardSuitEnum.DIAMOND),
    DIAMOND_Q(CardNumberEnum.NUM_Q, CardSuitEnum.DIAMOND),
    DIAMOND_K(CardNumberEnum.NUM_K, CardSuitEnum.DIAMOND),
    DIAMOND_A(CardNumberEnum.NUM_A, CardSuitEnum.DIAMOND),
    DIAMOND_2(CardNumberEnum.NUM_2, CardSuitEnum.DIAMOND),

    HEART_3(CardNumberEnum.NUM_3, CardSuitEnum.HEART),
    HEART_4(CardNumberEnum.NUM_4, CardSuitEnum.HEART),
    HEART_5(CardNumberEnum.NUM_5, CardSuitEnum.HEART),
    HEART_6(CardNumberEnum.NUM_6, CardSuitEnum.HEART),
    HEART_7(CardNumberEnum.NUM_7, CardSuitEnum.HEART),
    HEART_8(CardNumberEnum.NUM_8, CardSuitEnum.HEART),
    HEART_9(CardNumberEnum.NUM_9, CardSuitEnum.HEART),
    HEART_10(CardNumberEnum.NUM_10, CardSuitEnum.HEART),
    HEART_J(CardNumberEnum.NUM_J, CardSuitEnum.HEART),
    HEART_Q(CardNumberEnum.NUM_Q, CardSuitEnum.HEART),
    HEART_K(CardNumberEnum.NUM_K, CardSuitEnum.HEART),
    HEART_A(CardNumberEnum.NUM_A, CardSuitEnum.HEART),
    HEART_2(CardNumberEnum.NUM_2, CardSuitEnum.HEART);

    private final CardNumberEnum number;
    private final CardSuitEnum suit;

    CardEnum(CardNumberEnum number, CardSuitEnum suit) {
        this.number = number;
        this.suit = suit;
    }

    public CardNumberEnum getNumber() {
        return number;
    }

    public CardSuitEnum getSuit() {
        return suit;
    }

    public static CardEnum getCard(CardNumberEnum number, CardSuitEnum suit) {
        for (CardEnum card : CardEnum.values())
        {
            if (card.number == number && card.suit == suit)
            {
                return card;
            }
        }
        return null;
    }
}

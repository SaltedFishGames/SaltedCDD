package cn.saltedfish.bigtwo.define;

public enum CardEnum {
    CLUB_3(CardNumberEnum.NUMBER_3, CardSuitEnum.CLUB),
    CLUB_4(CardNumberEnum.NUMBER_4, CardSuitEnum.CLUB),
    CLUB_5(CardNumberEnum.NUMBER_5, CardSuitEnum.CLUB),
    CLUB_6(CardNumberEnum.NUMBER_6, CardSuitEnum.CLUB),
    CLUB_7(CardNumberEnum.NUMBER_7, CardSuitEnum.CLUB),
    CLUB_8(CardNumberEnum.NUMBER_8, CardSuitEnum.CLUB),
    CLUB_9(CardNumberEnum.NUMBER_9, CardSuitEnum.CLUB),
    CLUB_10(CardNumberEnum.NUMBER_10, CardSuitEnum.CLUB),
    CLUB_J(CardNumberEnum.NUMBER_J, CardSuitEnum.CLUB),
    CLUB_Q(CardNumberEnum.NUMBER_Q, CardSuitEnum.CLUB),
    CLUB_K(CardNumberEnum.NUMBER_K, CardSuitEnum.CLUB),
    CLUB_A(CardNumberEnum.NUMBER_A, CardSuitEnum.CLUB),
    CLUB_2(CardNumberEnum.NUMBER_2, CardSuitEnum.CLUB),

    SPADE_3(CardNumberEnum.NUMBER_3, CardSuitEnum.SPADE),
    SPADE_4(CardNumberEnum.NUMBER_4, CardSuitEnum.SPADE),
    SPADE_5(CardNumberEnum.NUMBER_5, CardSuitEnum.SPADE),
    SPADE_6(CardNumberEnum.NUMBER_6, CardSuitEnum.SPADE),
    SPADE_7(CardNumberEnum.NUMBER_7, CardSuitEnum.SPADE),
    SPADE_8(CardNumberEnum.NUMBER_8, CardSuitEnum.SPADE),
    SPADE_9(CardNumberEnum.NUMBER_9, CardSuitEnum.SPADE),
    SPADE_10(CardNumberEnum.NUMBER_10, CardSuitEnum.SPADE),
    SPADE_J(CardNumberEnum.NUMBER_J, CardSuitEnum.SPADE),
    SPADE_Q(CardNumberEnum.NUMBER_Q, CardSuitEnum.SPADE),
    SPADE_K(CardNumberEnum.NUMBER_K, CardSuitEnum.SPADE),
    SPADE_A(CardNumberEnum.NUMBER_A, CardSuitEnum.SPADE),
    SPADE_2(CardNumberEnum.NUMBER_2, CardSuitEnum.SPADE),

    DIAMOND_3(CardNumberEnum.NUMBER_3, CardSuitEnum.DIAMOND),
    DIAMOND_4(CardNumberEnum.NUMBER_4, CardSuitEnum.DIAMOND),
    DIAMOND_5(CardNumberEnum.NUMBER_5, CardSuitEnum.DIAMOND),
    DIAMOND_6(CardNumberEnum.NUMBER_6, CardSuitEnum.DIAMOND),
    DIAMOND_7(CardNumberEnum.NUMBER_7, CardSuitEnum.DIAMOND),
    DIAMOND_8(CardNumberEnum.NUMBER_8, CardSuitEnum.DIAMOND),
    DIAMOND_9(CardNumberEnum.NUMBER_9, CardSuitEnum.DIAMOND),
    DIAMOND_10(CardNumberEnum.NUMBER_10, CardSuitEnum.DIAMOND),
    DIAMOND_J(CardNumberEnum.NUMBER_J, CardSuitEnum.DIAMOND),
    DIAMOND_Q(CardNumberEnum.NUMBER_Q, CardSuitEnum.DIAMOND),
    DIAMOND_K(CardNumberEnum.NUMBER_K, CardSuitEnum.DIAMOND),
    DIAMOND_A(CardNumberEnum.NUMBER_A, CardSuitEnum.DIAMOND),
    DIAMOND_2(CardNumberEnum.NUMBER_2, CardSuitEnum.DIAMOND),

    HEART_3(CardNumberEnum.NUMBER_3, CardSuitEnum.HEART),
    HEART_4(CardNumberEnum.NUMBER_4, CardSuitEnum.HEART),
    HEART_5(CardNumberEnum.NUMBER_5, CardSuitEnum.HEART),
    HEART_6(CardNumberEnum.NUMBER_6, CardSuitEnum.HEART),
    HEART_7(CardNumberEnum.NUMBER_7, CardSuitEnum.HEART),
    HEART_8(CardNumberEnum.NUMBER_8, CardSuitEnum.HEART),
    HEART_9(CardNumberEnum.NUMBER_9, CardSuitEnum.HEART),
    HEART_10(CardNumberEnum.NUMBER_10, CardSuitEnum.HEART),
    HEART_J(CardNumberEnum.NUMBER_J, CardSuitEnum.HEART),
    HEART_Q(CardNumberEnum.NUMBER_Q, CardSuitEnum.HEART),
    HEART_K(CardNumberEnum.NUMBER_K, CardSuitEnum.HEART),
    HEART_A(CardNumberEnum.NUMBER_A, CardSuitEnum.HEART),
    HEART_2(CardNumberEnum.NUMBER_2, CardSuitEnum.HEART);

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
}

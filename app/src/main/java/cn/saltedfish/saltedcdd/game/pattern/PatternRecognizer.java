package cn.saltedfish.saltedcdd.game.pattern;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;

public class PatternRecognizer {
    public static CardGroup recognize(Collection<Card> pCards)
    {
        CardGroup group = new CardGroup(pCards);

        // do recognize
        return group;
    }

    public static EPatternType getPatternType(Collection<Card> pCards)
    {
        return recognize(pCards).mType;
    }
}

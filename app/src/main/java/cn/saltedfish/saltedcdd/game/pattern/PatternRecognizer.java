package cn.saltedfish.saltedcdd.game.pattern;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;

public class PatternRecognizer {
    public static CardGroup recognize(Collection<Card> pCards)
    {
        // do recognize


        CardGroup group = null;
        // group.mType = ...
        // group.mCriticalCard = ...
        group.sort();
        return group;
    }

    public static EPatternType getPatternType(Collection<Card> pCards)
    {
        return recognize(pCards).mType;
    }
}

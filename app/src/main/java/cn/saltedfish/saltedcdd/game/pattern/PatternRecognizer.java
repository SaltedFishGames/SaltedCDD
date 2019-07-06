package cn.saltedfish.saltedcdd.game.pattern;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class PatternRecognizer {
    protected static EPatternType[] sSingleCardPatterns = new EPatternType[] {
        EPatternType.Single
    };

    protected static EPatternType[] sDoubleCardPatterns = new EPatternType[] {
        EPatternType.Pair
    };

    protected static EPatternType[] sTripleCardPatterns = new EPatternType[] {
        EPatternType.Triple
    };

    protected static EPatternType[] sQuadrupleCardPatterns = new EPatternType[] {
        EPatternType.Quadruple
    };

    protected static EPatternType[] sQuintupleCardPatterns = new EPatternType[] {
        EPatternType.StraightFlush,
        EPatternType.FourOfAKind,
        EPatternType.FullHouse,
        EPatternType.Flush,
        EPatternType.Straight
    };

    public static CardGroup recognize(List<Card> pCards)
    {
        CardGroup group = new CardGroup(pCards);
        group.sort();

        EPatternType[] patterns = getAvailablePatterns(group.count());

        if (patterns != null)
        {
            for (EPatternType pattern : patterns)
            {
                if (pattern.match(group))
                {
                    break;
                }
            }
        }

        if (group.mType == EPatternType.Unrecognized)
        {
            group.mType = EPatternType.Unknown;
            group.mCriticalCard = group.get(group.count() - 1);
        }

        return group;
    }

    public static EPatternType[] getAvailablePatterns(int num)
    {
        switch (num)
        {
            case 1:
                return sSingleCardPatterns;
            case 2:
                return sDoubleCardPatterns;
            case 3:
                return sTripleCardPatterns;
            case 4:
                return sQuadrupleCardPatterns;
            case 5:
                return sQuintupleCardPatterns;
            default:
                return null;
        }
    }

    public static EPatternType getPatternType(List<Card> pCards)
    {
        return recognize(pCards).mType;
    }
}

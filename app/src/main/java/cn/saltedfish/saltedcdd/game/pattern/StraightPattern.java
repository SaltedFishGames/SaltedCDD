package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;

public class StraightPattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 5) return false;

        if (PatternMatchUtil.isFiveStraightNumber(pGroup.cards()))
        {
            pGroup.setType(EPatternType.Straight);
            pGroup.setCriticalCard(pGroup.get(4));
            return true;
        }

        return false;
    }

    protected static boolean fillPattern(int depth, Card[] pCards, AnnotatedCards pAvailableCards, ECardNumber[] pPattern, List<CardGroup> pResult)
    {
        if (depth == 5)
        {
            pResult.add(new CardGroup(pCards));
            return true;
        }
        else
        {
            List<Card> cardsAtDepth = pAvailableCards.getNumberMap().get(pPattern[depth]);
            if (cardsAtDepth == null || cardsAtDepth.size() == 0)
            {
                return false;
            }
            for (Card c : cardsAtDepth)
            {
                pCards[depth] = c;
                if (!fillPattern(depth + 1, pCards, pAvailableCards, pPattern, pResult))
                {
                    return false;
                }
            }
            return true;
        }
    }

    public static List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
    {
        List<CardGroup> result = new ArrayList<>();
        for (ECardNumber[] pattern : PatternMatchUtil.getStraightPatterns())
        {
            Card[] cards = new Card[5];
            fillPattern(0, cards, pAvailableCards, pattern, result);
        }
        return result;
    }
}

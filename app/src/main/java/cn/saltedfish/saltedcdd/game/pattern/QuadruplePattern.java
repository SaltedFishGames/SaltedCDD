package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class QuadruplePattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 4) return false;

        if (PatternMatchUtil.isSameNumber(pGroup.cards()))
        {
            pGroup.setType(EPatternType.Quadruple);
            pGroup.setCriticalCard(pGroup.get(3));
            return true;
        }
        return false;
    }

    public static List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
    {
        List<CardGroup> result = new ArrayList<>();
        for (List<Card> cards : pAvailableCards.getNumberMap().values())
        {
            if (cards.size() >= 4)
            {
                PatternMatchUtil.enumCombinationSimple(result, cards, 4);
            }
        }
        return result;
    }
}

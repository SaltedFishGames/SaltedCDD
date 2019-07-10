package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class PairPattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 2) return false;

        if (PatternMatchUtil.isSameNumber(pGroup.cards()))
        {
            pGroup.setType(EPatternType.Pair);
            pGroup.setCriticalCard(pGroup.get(1));
            return true;
        }
        return false;
    }

    public static List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
    {
        List<CardGroup> result = new ArrayList<>();
        for (List<Card> cards : pAvailableCards.getNumberMap().values())
        {
            if (cards.size() >= 2)
            {
                PatternMatchUtil.enumCombinationSimple(result, cards, 2);
            }
        }
        return result;
    }
}

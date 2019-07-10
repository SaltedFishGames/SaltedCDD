package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class FlushPattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 5) return false;

        if (PatternMatchUtil.isSameSuit(pGroup.cards()))
        {
            pGroup.setType(EPatternType.Flush);
            pGroup.setCriticalCard(pGroup.get(4));
            return true;
        }
        return false;
    }

    public static List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
    {
        List<CardGroup> result = new ArrayList<>();
        for (List<Card> cards : pAvailableCards.getSuitMap().values())
        {
            if (cards.size() >= 5)
            {
                PatternMatchUtil.enumCombinationSimple(result, cards, 5);
            }
        }
        return result;
    }
}

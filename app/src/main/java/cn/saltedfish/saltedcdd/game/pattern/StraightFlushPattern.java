package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class StraightFlushPattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 5) return false;

        if (PatternMatchUtil.isFiveStraightNumber(pGroup.cards())
                && PatternMatchUtil.isSameSuit(pGroup.cards()))
        {
            pGroup.setType(EPatternType.StraightFlush);
            pGroup.setCriticalCard(pGroup.get(4));
            return true;
        }

        return false;
    }

    public static List<CardGroup> potentialCardGroup(final AnnotatedCards pAvailableCards)
    {
        final List<CardGroup> result = new ArrayList<>();

        PatternMatchUtil.EnumItemHandler straightFilter = new PatternMatchUtil.EnumItemHandler() {
            @Override
            public void handle(Card[] item)
            {
                if (PatternMatchUtil.isFiveStraightNumber(item))
                {
                    result.add(new CardGroup(item));
                }
            }
        };
        Card[] potentialGroup = new Card[5];

        for (List<Card> cards : pAvailableCards.getSuitMap().values())
        {
            if (cards.size() >= 5)
            {
                PatternMatchUtil.enumCombination(straightFilter, cards, 0, 5, 0, 0, potentialGroup);
            }
        }

        return result;
    }
}

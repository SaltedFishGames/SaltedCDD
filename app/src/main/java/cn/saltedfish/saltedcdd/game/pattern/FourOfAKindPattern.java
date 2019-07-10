package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.util.ValueContainer;

public class FourOfAKindPattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 5) return false;
        ArrayList<Card> cards = pGroup.cards();

        // 33334
        if (PatternMatchUtil.isSameNumber(cards.subList(0, 4)))
        {
            pGroup.setType(EPatternType.FourOfAKind);
            pGroup.setCriticalCard(pGroup.get(3));
            return true;
        }
        //34444
        else if (PatternMatchUtil.isSameNumber(cards.subList(1, 5)))
        {
            pGroup.setType(EPatternType.FourOfAKind);
            pGroup.setCriticalCard(pGroup.get(4));
            return true;
        }

        return false;
    }

    public static List<CardGroup> potentialCardGroup(final AnnotatedCards pAvailableCards)
    {
        final ValueContainer<ECardNumber> numberHolder = new ValueContainer<>(null);
        final List<CardGroup> result = new ArrayList<>();

        Card[] potentialGroup = new Card[5];

        PatternMatchUtil.EnumItemHandler handler = new PatternMatchUtil.EnumItemHandler() {
            @Override
            public void handle(Card[] item)
            {
                for (List<Card> cardsOne : pAvailableCards.getNumberMap().values())
                {
                    if (cardsOne.size() >= 1
                            && cardsOne.get(0).getNumber() != numberHolder.getValue())
                    {
                        for (int j = 0; j < cardsOne.size(); j++)
                        {
                            item[4] = cardsOne.get(j);
                            result.add(new CardGroup(item));
                        }
                    }
                }
            }
        };

        for (final List<Card> cardsFour : pAvailableCards.getNumberMap().values())
        {
            if (cardsFour.size() >= 4)
            {
                numberHolder.setValue(cardsFour.get(0).getNumber());
                PatternMatchUtil.enumCombination(handler, cardsFour, 0, 4, 0, 0, potentialGroup);
            }
        }
        return result;
    }
}

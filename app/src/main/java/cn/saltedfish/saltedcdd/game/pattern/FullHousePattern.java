package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.util.ValueContainer;

public class FullHousePattern {
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() != 5) return false;
        ArrayList<Card> cards = pGroup.cards();

        // 33344
        if (PatternMatchUtil.isSameNumber(cards.subList(0, 3))
                && PatternMatchUtil.isSameNumber(cards.subList(3, 5)))
        {
            pGroup.setType(EPatternType.FullHouse);
            pGroup.setCriticalCard(pGroup.get(2));
            return true;
        }
        //33444
        else if (PatternMatchUtil.isSameNumber(cards.subList(0, 2))
                && PatternMatchUtil.isSameNumber(cards.subList(2, 5)))
        {
            pGroup.setType(EPatternType.FullHouse);
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

        final PatternMatchUtil.EnumItemHandler handlerSecond = new PatternMatchUtil.EnumItemHandler() {
            @Override
            public void handle(Card[] item)
            {
                result.add(new CardGroup(item));
            }
        };

        PatternMatchUtil.EnumItemHandler handlerFirst = new PatternMatchUtil.EnumItemHandler() {
            @Override
            public void handle(Card[] item)
            {
                for (List<Card> cardsTwo : pAvailableCards.getNumberMap().values())
                {
                    if (cardsTwo.size() >= 2
                            && cardsTwo.get(0).getNumber() != numberHolder.getValue())
                    {
                        PatternMatchUtil.enumCombination(handlerSecond, cardsTwo, 3, 2, 0, 0, item);
                    }
                }
            }
        };

        for (final List<Card> cardsThree : pAvailableCards.getNumberMap().values())
        {
            if (cardsThree.size() >= 3)
            {
                numberHolder.setValue(cardsThree.get(0).getNumber());
                PatternMatchUtil.enumCombination(handlerFirst, cardsThree, 0, 3, 0, 0, potentialGroup);
            }
        }
        return result;
    }
}

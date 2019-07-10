package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class SinglePattern{
    public static boolean match(CardGroup pGroup)
    {
        if (pGroup.count() == 1)
        {
            pGroup.setType(EPatternType.Single);
            pGroup.setCriticalCard(pGroup.get(0));
            return true;
        }
        return false;
    }

    public static List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
    {
        List<CardGroup> result = new ArrayList<>();
        Card[] potentialCards = new Card[1];
        for (List<Card> cards : pAvailableCards.getNumberMap().values())
        {
            for (Card card : cards)
            {
                potentialCards[0] = card;
                result.add(new CardGroup(potentialCards));
            }
        }
        return result;
    }
}

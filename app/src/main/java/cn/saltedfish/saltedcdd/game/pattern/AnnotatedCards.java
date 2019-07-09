package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;

public class AnnotatedCards {
    protected EnumMap<ECardNumber, List<Card>> mNumberMap = new EnumMap<>(ECardNumber.class);
    protected EnumMap<ECardSuit, List<Card>> mSuitMap = new EnumMap<>(ECardSuit.class);

    public AnnotatedCards(List<Card> pCards)
    {
        ArrayList<Card> cards = new ArrayList<>(pCards);
        Collections.sort(cards);

        for (Card card : cards)
        {
            List<Card> numberMap = mNumberMap.get(card.getNumber());
            if (numberMap == null)
            {
                numberMap = new ArrayList<>();
                mNumberMap.put(card.getNumber(), numberMap);
            }
            numberMap.add(card);

            List<Card> suitMap = mSuitMap.get(card.getSuit());
            if (suitMap == null)
            {
                suitMap = new ArrayList<>();
                mSuitMap.put(card.getSuit(), suitMap);
            }
            suitMap.add(card);
        }
    }

    public EnumMap<ECardNumber, List<Card>> getNumberMap()
    {
        return mNumberMap;
    }


    public EnumMap<ECardSuit, List<Card>> getSuitMap()
    {
        return mSuitMap;
    }

    public List<Card> getByNumber(ECardNumber pNumber)
    {
        return mNumberMap.get(pNumber);
    }

    public List<Card> getBySuit(ECardSuit pSuit)
    {
        return mSuitMap.get(pSuit);
    }
}

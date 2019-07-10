package cn.saltedfish.saltedcdd.game.pattern;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;

public class PatternMatchUtil {
    protected static ECardNumber[][] sStraightPatterns = new ECardNumber[][]{
        new ECardNumber[] { ECardNumber.NUM_3, ECardNumber.NUM_4, ECardNumber.NUM_5, ECardNumber.NUM_A, ECardNumber.NUM_2 },
        new ECardNumber[] { ECardNumber.NUM_3, ECardNumber.NUM_4, ECardNumber.NUM_5, ECardNumber.NUM_6, ECardNumber.NUM_2 },
        new ECardNumber[] { ECardNumber.NUM_3, ECardNumber.NUM_4, ECardNumber.NUM_5, ECardNumber.NUM_6, ECardNumber.NUM_7 },
        new ECardNumber[] { ECardNumber.NUM_4, ECardNumber.NUM_5, ECardNumber.NUM_6, ECardNumber.NUM_7, ECardNumber.NUM_8 },
        new ECardNumber[] { ECardNumber.NUM_5, ECardNumber.NUM_6, ECardNumber.NUM_7, ECardNumber.NUM_8, ECardNumber.NUM_9 },
        new ECardNumber[] { ECardNumber.NUM_6, ECardNumber.NUM_7, ECardNumber.NUM_8, ECardNumber.NUM_9, ECardNumber.NUM_10 },
        new ECardNumber[] { ECardNumber.NUM_7, ECardNumber.NUM_8, ECardNumber.NUM_9, ECardNumber.NUM_10, ECardNumber.NUM_J },
        new ECardNumber[] { ECardNumber.NUM_8, ECardNumber.NUM_9, ECardNumber.NUM_10, ECardNumber.NUM_J, ECardNumber.NUM_Q },
        new ECardNumber[] { ECardNumber.NUM_9, ECardNumber.NUM_10, ECardNumber.NUM_J, ECardNumber.NUM_Q, ECardNumber.NUM_K },
        new ECardNumber[] { ECardNumber.NUM_10, ECardNumber.NUM_J, ECardNumber.NUM_Q, ECardNumber.NUM_K, ECardNumber.NUM_A },
    };

    public static boolean isFiveStraightNumber(Card[] pCards)
    {
        for (ECardNumber[] pattern : sStraightPatterns)
        {
            boolean flag = true;
            for (int i = 0; i < pattern.length; i++)
            {
                if (pCards[i].getNumber() != pattern[i])
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isFiveStraightNumber(List<Card> pCards)
    {
        for (ECardNumber[] pattern : sStraightPatterns)
        {
            boolean flag = true;
            for (int i = 0; i < pattern.length; i++)
            {
                if (pCards.get(i).getNumber() != pattern[i])
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameSuit(List<Card> pCards)
    {
        if (pCards.size() < 2)
            return true;

        ECardSuit suit = pCards.get(0).getSuit();

        for (Card card : pCards)
        {
            if (card.getSuit() != suit)
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isSameNumber(List<Card> pCards)
    {
        if (pCards.size() < 2)
            return true;

        ECardNumber number = pCards.get(0).getNumber();

        for (Card card : pCards)
        {
            if (card.getNumber() != number)
            {
                return false;
            }
        }

        return true;
    }

    public static ECardNumber[][] getStraightPatterns()
    {
        return sStraightPatterns;
    }

    public static void enumCombination(EnumItemHandler pHandler, List<Card> pAvailableCards, int pStartIdx, int pTotalNum, int pFilledNum, int pSelectedIndex, Card[] pTmpArray)
    {
        if (pFilledNum == pTotalNum) {
            pHandler.handle(pTmpArray);
            return;
        }

        int targetIndex = pAvailableCards.size() - (pTotalNum - pFilledNum);
        for (int i = pSelectedIndex; i <= targetIndex; i++) {
            pTmpArray[pStartIdx + pFilledNum] = pAvailableCards.get(i);
            enumCombination(pHandler, pAvailableCards, pStartIdx, pTotalNum, pFilledNum + 1, i + 1, pTmpArray);
        }
    }

    public static void enumCombinationSimple(final List<CardGroup> pResult, List<Card> pAvailableCards, int pTotalNum)
    {
        Card[] tmpArray = new Card[pTotalNum];
        EnumItemHandler handler = new EnumItemHandler() {
            @Override
            public void handle(Card[] item)
            {
                pResult.add(new CardGroup(item));
            }
        };
        enumCombination(handler, pAvailableCards, 0, pTotalNum, 0, 0, tmpArray);
    }

    public interface EnumItemHandler
    {
        void handle(Card[] item);
    }
}

package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.util.ValueContainer;

public enum EPatternType {
    Unrecognized(0) { // 尚未进行识别
        @Override
        public boolean match(CardGroup pGroup)
        {
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return null;
        }
    },

    Unknown(0) { // 未知牌型
        @Override
        public boolean match(CardGroup pGroup)
        {
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return null;
        }
    },

    // 1 张牌
    Single(1) { // 单张
        @Override
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() == 1)
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(0));
                return true;
            }
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            List<CardGroup> result = new ArrayList<>();
            Card[] potentialCards = new Card[1];
            for (List<Card> cards : pAvailableCards.mNumberMap.values())
            {
                for (Card card : cards)
                {
                    potentialCards[0] = card;
                    result.add(new CardGroup(potentialCards));
                }
            }
            return result;
        }
    },

    // 2 张牌
    Pair(1) { // 对子
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 2) return false;

            if (PatternMatchUtil.isSameNumber(pGroup.cards()))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(1));
                return true;
            }
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            List<CardGroup> result = new ArrayList<>();
            for (List<Card> cards : pAvailableCards.mNumberMap.values())
            {
                if (cards.size() >= 2)
                {
                    enumCombinationSimple(result, cards, 2);
                }
            }
            return result;
        }
    },

    // 3 张牌
    Triple(1) { // 三张
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 3) return false;

            if (PatternMatchUtil.isSameNumber(pGroup.cards()))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(2));
                return true;
            }
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            List<CardGroup> result = new ArrayList<>();
            for (List<Card> cards : pAvailableCards.mNumberMap.values())
            {
                if (cards.size() >= 3)
                {
                    enumCombinationSimple(result, cards, 3);
                }
            }
            return result;
        }
    },

    // 4 张牌
    Quadruple(1) { // 四张
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 4) return false;

            if (PatternMatchUtil.isSameNumber(pGroup.cards()))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(3));
                return true;
            }
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            List<CardGroup> result = new ArrayList<>();
            for (List<Card> cards : pAvailableCards.mNumberMap.values())
            {
                if (cards.size() >= 4)
                {
                    enumCombinationSimple(result, cards, 4);
                }
            }
            return result;
        }
    },

    // 5 张牌
    Straight(1) { // 顺子
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;

            if (PatternMatchUtil.isFiveStraightNumber(pGroup.cards()))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(4));
                return true;
            }

            return false;
        }

        protected boolean fillPattern(int depth, Card[] pCards, AnnotatedCards pAvailableCards, ECardNumber[] pPattern, List<CardGroup> pResult)
        {
            if (depth == 5)
            {
                pResult.add(new CardGroup(pCards));
                return true;
            }
            else
            {
                List<Card> cardsAtDepth = pAvailableCards.mNumberMap.get(pPattern[depth]);
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

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            List<CardGroup> result = new ArrayList<>();
            for (ECardNumber[] pattern : PatternMatchUtil.getStraightPatterns())
            {
                Card[] cards = new Card[5];
                fillPattern(0, cards, pAvailableCards, pattern, result);
            }
            return result;
        }
    },

    Flush(2) { // 同花
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;

            if (PatternMatchUtil.isSameSuit(pGroup.cards()))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(4));
                return true;
            }
            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            List<CardGroup> result = new ArrayList<>();
            for (List<Card> cards : pAvailableCards.mSuitMap.values())
            {
                if (cards.size() >= 5)
                {
                    enumCombinationSimple(result, cards, 5);
                }
            }
            return result;
        }
    },
    FullHouse(3) { // 葫芦（三带二）
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;
            ArrayList<Card> cards = pGroup.cards();

            // 33344
            if (PatternMatchUtil.isSameNumber(cards.subList(0, 3))
                    && PatternMatchUtil.isSameNumber(cards.subList(3, 5)))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(2));
                return true;
            }
            //33444
            else if (PatternMatchUtil.isSameNumber(cards.subList(0, 2))
                    && PatternMatchUtil.isSameNumber(cards.subList(2, 5)))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(4));
                return true;
            }

            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(final AnnotatedCards pAvailableCards)
        {
            final ValueContainer<ECardNumber> numberHolder = new ValueContainer<>(null);
            final List<CardGroup> result = new ArrayList<>();

            Card[] potentialGroup = new Card[5];

            final EnumItemHandler handlerSecond = new EnumItemHandler() {
                @Override
                public void handle(Card[] item)
                {
                    result.add(new CardGroup(item));
                }
            };

            EnumItemHandler handlerFirst = new EnumItemHandler() {
                @Override
                public void handle(Card[] item)
                {
                    for (List<Card> cardsTwo : pAvailableCards.mNumberMap.values())
                    {

                        if (cardsTwo.size() >= 2
                                && cardsTwo.get(0).getNumber() != numberHolder.getValue())
                        {
                            enumCombination(handlerSecond, cardsTwo, 3, 2, 0, 0, item);
                        }
                    }
                }
            };

            for (final List<Card> cardsThree : pAvailableCards.mNumberMap.values())
            {
                if (cardsThree.size() >= 3)
                {
                    numberHolder.setValue(cardsThree.get(0).getNumber());
                    enumCombination(handlerFirst, cardsThree, 0, 3, 0, 0, potentialGroup);
                }
            }
            return result;
        }
    },
    FourOfAKind(4) { // 铁支（四带一）
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;
            ArrayList<Card> cards = pGroup.cards();

            // 33334
            if (PatternMatchUtil.isSameNumber(cards.subList(0, 4)))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(3));
                return true;
            }
            //34444
            else if (PatternMatchUtil.isSameNumber(cards.subList(1, 5)))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(4));
                return true;
            }

            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(final AnnotatedCards pAvailableCards)
        {
            final ValueContainer<ECardNumber> numberHolder = new ValueContainer<>(null);
            final List<CardGroup> result = new ArrayList<>();

            Card[] potentialGroup = new Card[5];

            EnumItemHandler handler = new EnumItemHandler() {
                @Override
                public void handle(Card[] item)
                {
                    for (List<Card> cardsOne : pAvailableCards.mNumberMap.values())
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

            for (final List<Card> cardsFour : pAvailableCards.mNumberMap.values())
            {
                if (cardsFour.size() >= 4)
                {
                    numberHolder.setValue(cardsFour.get(0).getNumber());
                    enumCombination(handler, cardsFour, 0, 4, 0, 0, potentialGroup);
                }
            }
            return result;
        }
    },
    StraightFlush(5) { // 同花顺（顺子 & 同花）
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;

            if (PatternMatchUtil.isFiveStraightNumber(pGroup.cards())
                    && PatternMatchUtil.isSameSuit(pGroup.cards()))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(4));
                return true;
            }

            return false;
        }

        @Override
        public List<CardGroup> potentialCardGroup(final AnnotatedCards pAvailableCards)
        {
            final List<CardGroup> result = new ArrayList<>();

            EnumItemHandler straightFilter = new EnumItemHandler() {
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

            for (List<Card> cards : pAvailableCards.mSuitMap.values())
            {
                if (cards.size() >= 5)
                {
                    enumCombination(straightFilter, cards, 0, 5, 0, 0, potentialGroup);
                }
            }

            return result;
        }
    };

    int mWeight;

    EPatternType(int pWeight)
    {
        mWeight = pWeight;
    }

    public int getWeight()
    {
        return mWeight;
    }

    public int compareWeight(EPatternType pOther)
    {
        return mWeight - pOther.mWeight;
    }

    public abstract boolean match(CardGroup pGroup);
    public abstract List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards);

    protected static void enumCombination(EnumItemHandler pHandler, List<Card> pAvailableCards, int pStartIdx, int pTotalNum, int pFilledNum, int pSelectedIndex, Card[] pTmpArray)
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

    protected static void enumCombinationSimple(final List<CardGroup> pResult, List<Card> pAvailableCards, int pTotalNum)
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

    protected interface EnumItemHandler
    {
        void handle(Card[] item);
    }
}
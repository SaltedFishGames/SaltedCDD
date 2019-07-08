package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.game.card.Card;

public enum EPatternType {
    Unrecognized(0) { // 尚未进行识别
        public boolean match(CardGroup pGroup)
        {
            return false;
        }
    },

    Unknown(0) { // 未知牌型
        public boolean match(CardGroup pGroup)
        {
            return false;
        }
    },

    // 1 张牌
    Single(1) { // 单张
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
                pGroup.setCriticalCard(pGroup.get(4));
                return true;
            }
            //33444
            else if (PatternMatchUtil.isSameNumber(cards.subList(0, 2))
                    && PatternMatchUtil.isSameNumber(cards.subList(2, 5)))
            {
                pGroup.setType(this);
                pGroup.setCriticalCard(pGroup.get(2));
                return true;
            }

            return false;
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
    };

    int mWeight;

    EPatternType(int pWeight)
    {
        mWeight = pWeight;
    }

    public abstract boolean match(CardGroup pGroup);

    public int getWeight()
    {
        return mWeight;
    }
    // public abstract boolean canFollow(List<Card> pCards);
}
package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public enum EPatternType {
    Unrecognized { // 尚未进行识别
        public boolean match(CardGroup pGroup)
        {
            return false;
        }
    },
    Unknown { // 未知牌型
        public boolean match(CardGroup pGroup)
        {
            return false;
        }
    },

    // 1 张牌
    Single { // 单张
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() == 1)
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(0);
                return true;
            }
            return false;
        }
    },

    // 2 张牌
    Pair { // 对子
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 2) return false;

            if (PatternMatchUtil.isSameNumber(pGroup.mCards))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(1);
                return true;
            }
            return false;
        }
    },

    // 3 张牌
    Triple { // 三张
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 3) return false;

            if (PatternMatchUtil.isSameNumber(pGroup.mCards))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(2);
                return true;
            }
            return false;
        }
    },

    // 4 张牌
    Quadruple { // 四张
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 4) return false;

            if (PatternMatchUtil.isSameNumber(pGroup.mCards))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(3);
                return true;
            }
            return false;
        }
    },

    // 5 张牌
    Straight { // 顺子
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;

            if (PatternMatchUtil.isFiveStraightNumber(pGroup.mCards))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(4);
                return true;
            }

            return false;
        }
    },
    Flush { // 同花
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;

            if (PatternMatchUtil.isSameSuit(pGroup.mCards))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(4);
                return true;
            }
            return false;
        }
    },
    FullHouse { // 葫芦（三带二）
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;
            ArrayList<Card> cards = pGroup.mCards;

            // 33344
            if (PatternMatchUtil.isSameNumber(cards.subList(0, 3))
                    && PatternMatchUtil.isSameNumber(cards.subList(3, 5)))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(4);
                return true;
            }
            //33444
            else if (PatternMatchUtil.isSameNumber(cards.subList(0, 2))
                    && PatternMatchUtil.isSameNumber(cards.subList(2, 5)))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(2);
                return true;
            }

            return false;
        }
    },
    FourOfAKind { // 铁支（四带一）
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;
            ArrayList<Card> cards = pGroup.mCards;

            // 33334
            if (PatternMatchUtil.isSameNumber(cards.subList(0, 4)))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(3);
                return true;
            }
            //34444
            else if (PatternMatchUtil.isSameNumber(cards.subList(1, 5)))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(4);
                return true;
            }

            return false;
        }
    },
    StraightFlush { // 同花顺（顺子 & 同花）
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() != 5) return false;

            if (PatternMatchUtil.isFiveStraightNumber(pGroup.mCards)
                    && PatternMatchUtil.isSameSuit(pGroup.mCards))
            {
                pGroup.mType = this;
                pGroup.mCriticalCard = pGroup.get(4);
                return true;
            }

            return false;
        }
    };

    public abstract boolean match(CardGroup pGroup);

    // public abstract boolean canFollow(List<Card> pCards);
}
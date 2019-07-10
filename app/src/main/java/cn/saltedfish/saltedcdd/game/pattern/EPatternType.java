package cn.saltedfish.saltedcdd.game.pattern;

import java.util.List;

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
            return SinglePattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return SinglePattern.potentialCardGroup(pAvailableCards);
        }
    },

    // 2 张牌
    Pair(1) { // 对子
        @Override
        public boolean match(CardGroup pGroup)
        {
            return PairPattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return PairPattern.potentialCardGroup(pAvailableCards);
        }
    },

    // 3 张牌
    Triple(1) { // 三张
        @Override
        public boolean match(CardGroup pGroup)
        {
            return TriplePattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return TriplePattern.potentialCardGroup(pAvailableCards);
        }
    },

    // 4 张牌
    Quadruple(1) { // 四张
        @Override
        public boolean match(CardGroup pGroup)
        {
            return QuadruplePattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return QuadruplePattern.potentialCardGroup(pAvailableCards);
        }
    },

    // 5 张牌
    Straight(1) { // 顺子
        @Override
        public boolean match(CardGroup pGroup)
        {
            return StraightPattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return StraightPattern.potentialCardGroup(pAvailableCards);
        }
    },

    Flush(2) { // 同花
        @Override
        public boolean match(CardGroup pGroup)
        {
            return FlushPattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return FlushPattern.potentialCardGroup(pAvailableCards);
        }
    },

    FullHouse(3) { // 葫芦（三带二）
        @Override
        public boolean match(CardGroup pGroup)
        {
            return FullHousePattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return FullHousePattern.potentialCardGroup(pAvailableCards);
        }
    },

    FourOfAKind(4) { // 铁支（四带一）
        @Override
        public boolean match(CardGroup pGroup)
        {
            return FourOfAKindPattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return FourOfAKindPattern.potentialCardGroup(pAvailableCards);
        }
    },

    StraightFlush(5) { // 同花顺（顺子 & 同花）
        @Override
        public boolean match(CardGroup pGroup)
        {
            return StraightFlushPattern.match(pGroup);
        }

        @Override
        public List<CardGroup> potentialCardGroup(AnnotatedCards pAvailableCards)
        {
            return StraightFlushPattern.potentialCardGroup(pAvailableCards);
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
}
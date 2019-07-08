package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.game.card.Card;

public enum EPatternType {
    Unrecognized { // 尚未进行识别
        public boolean match(CardGroup pGroup)
        {
            return false;
        }

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            return false;
        }
    },
    Unknown { // 未知牌型
        public boolean match(CardGroup pGroup)
        {
            return false;
        }

        public boolean CanFollow(Card cCard, List<Card> pCards)
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

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            for(Card card : pCards)
                if(card.compareTo(cCard) > 0)
                    return true;
            
            return false;
        }
    },

    // 2 张牌
    Pair { // 对子
        public boolean match(CardGroup pGroup)
        {
            if (pGroup.count() == 2)
                if(pGroup.mCards.get(0).getNumber() == pGroup.mCards.get(1).getNumber())
                {
                    pGroup.mType = this;
                    pGroup.mCriticalCard = pGroup.get(1);
                    return true;
                }
            return false;
        }

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            int size = pCards.count();
            for(int index = 1; index < size; index ++)
                if(pCards.get(index).compareTo(cCard) > 0
                && Pair.match(pCards.subList(index - 1, index + 1))
                    return true;

            return false;
        }
    },

    // 3 张牌
    Triple { // 三张
        public boolean match(CardGroup pGroup)
        {
            if(pGroup.count() == 3)
                if(pGroup.mCards.get(0).getNumber() == pGroup.mCards.get(1).getNumber()
                        && pGroup.mCards.get(1).getNumber() == pGroup.mCards.get(2).getNumber())
                {
                    pGroup.mType = this;
                    pGroup.mCriticalCard = pGroup.get(2);
                    return true;
                }
            return false;
        }
        
        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            int size = pCards.count();
            for(int index = 2; index < size; index ++)
                if(pCards.get(index).compareTo(cCard) > 0
                && Triple.match(pCards.subList(index - 2, index + 1)))
                    return true;
            
            return false;
        }
    },

    // 4 张牌
    Quadruple { // 四张
        public boolean match(CardGroup pGroup)
        {
            if(pGroup.count() == 4)
                if(pGroup.mCards.get(0).getNumber() == pGroup.mCards.get(1).getNumber()
                        && pGroup.mCards.get(1).getNumber() == pGroup.mCards.get(2).getNumber()
                        && pGroup.mCards.get(2).getNumber() == pGroup.mCards.get(3).getNumber())
                {
                    pGroup.mType = this;
                    pGroup.mCriticalCard = pGroup.get(3);
                    return true;
                }
            return false;
        }

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            int size = pCards.count();
            for(int index = 3; index < size; index ++)
                if(pCards.get(index).compareTo(cCard) > 0
                && Quadruple.match(pCards.subList(index - 3, index + 1)))
                    return true;
            
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

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            boolean NumberKind[] = new boolean[14];
            ECardSuit SuitKind[] = new ECardSuit[14];
            for(Card card : pCards)
            {
                int index = card.getNumber().getWeight();
                if(index >= 13) index -= 13;

                NumberKind[index] = true;
                SuitKind[index] = card.getSuit();
            }
            int count = cCard.getNumber().getWeight();
            for(int index = count; index < 14; index ++)
                if(index > count || (index == count 
                && SuitKind[index].compareWeight(cCard.getSuit()) > 0))
                {
                    boolean isStraight = true;
                    for(int i = index; i > i-5; i --)
                        if(!NumberKind[i])
                        {
                            isStraight = false;
                            break;
                        }
                    if(isStraight) return true;
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

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            int Suitcount[] = new int[4];
            boolean hasGreater[] = new boolean[4];
            for(Card card : pCards)
            {
                int index = card.getSuit().getWeight() - 1;
                Suitcount[index] ++;
                if(card.compareTo(cCard) > 0)
                    hasGreater[index] = true;
            }
            for(int index = 0; index < 4; index ++)
                if(Suitcount[index] >= 5 && hasGreater[index])
                    return true;
            
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

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            int size = pCards.count();
            for(int index = 2; index < size; index ++)
                if(pCards.get(index).compareTo(cCard) > 0
                && Triple.match(pCards.subList(index - 2, index + 1)))
                    for(int sub = 1; sub < size; sub ++)
                        if( (sub < index-2 || sub >= index + 2)
                        && Pair.match(pCards.subList(sub - 1, sub + 1)))
                            return true;
            
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

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            int size = pCards.count();
            for(int index = 3; index < size; index ++)
                if(pCards.get(index).compareTo(cCard) > 0
                && Quadruple.match(pCards.subList(index - 3, index + 1))
                && size >= 5)
                    return true;
            
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

        public boolean CanFollow(Card cCard, List<Card> pCards)
        {
            boolean SuitKind[][] = new boolean[14][4];
            int suit = 0;
            for(Card card : pCards)
            {
                int index = card.getNumber().getWeight();
                if(index >= 13) index -= 13;

                int suit = card.getSuit().getWeight() - 1;
                SuitKind[index][suit] = true;
            }
            int count = cCard.getNumber().getWeight();
            for(int index = count; index < 14; index ++)
                for(int j = 0; j < 4; j ++)
                {
                    boolean isStraight = true;
                    for(int i = index; i > i-5; i --)
                        if(!SuitKind[i][j])
                        {
                            isStraight = false;
                            break;
                        }
                    if(isStraight)
                    {
                        ECardSuit temp = new ECardSuit(j+1, "");
                        if(index > count || (index == count
                        && temp.compareWeight(cCard.getSuit()) > 0))
                            return true;
                    }
                }

            return false;
        }
    };

    public abstract boolean match(CardGroup pGroup);

    public abstract boolean CanFollow(Card cCard, List<Card> pCards);
}
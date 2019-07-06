package cn.saltedfish.saltedcdd.game.pattern;

import java.util.ArrayList;
import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;

public class CardGroup {
    public ArrayList<Card> mCards;

    public EPatternType mType = EPatternType.Unrecognized;

    public Card mCriticalCard;

    public CardGroup(Collection<Card> pCards)
    {
        mCards = new ArrayList<>(pCards);
    }
}

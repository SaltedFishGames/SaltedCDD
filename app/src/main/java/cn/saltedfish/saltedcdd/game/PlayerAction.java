package cn.saltedfish.saltedcdd.game;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;

public class PlayerAction {
    public EActionType mAction;
    public CardGroup mCards;
    public int mPlayerId;

    public PlayerAction(int pPlayerId, EActionType pAction, CardGroup pCards)
    {
        mPlayerId = pPlayerId;
        mAction = pAction;
        mCards = pCards;
    }

    public static PlayerAction createPassAction(int pPlayerId)
    {
        return new PlayerAction(pPlayerId, EActionType.Pass, null);
    }

    public static PlayerAction createShowCardAction(int pPlayerId, CardGroup pCards)
    {
        return new PlayerAction(pPlayerId, EActionType.ShowCard, pCards);
    }
}

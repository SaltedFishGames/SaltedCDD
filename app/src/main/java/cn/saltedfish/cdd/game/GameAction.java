package cn.saltedfish.cdd.game;

import cn.saltedfish.cdd.PlayerInfo;
import cn.saltedfish.cdd.card.CardCollection;

public class GameAction
{
    public GamePlayer mPlayer;
    public TurnActionTypeEnum mType;
    public CardCollection mCards;

    public static GameAction createPassAction(GamePlayer player)
    {
        GameAction action = new GameAction();
        action.mPlayer = player;
        action.mType = TurnActionTypeEnum.Pass;

        return action;
    }

    public static GameAction createShowCardAction(GamePlayer player, CardCollection cards)
    {
        GameAction action = new GameAction();
        action.mPlayer = player;
        action.mType = TurnActionTypeEnum.ShowCard;
        action.mCards = cards;

        return action;
    }
}

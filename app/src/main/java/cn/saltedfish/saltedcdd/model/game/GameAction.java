package cn.saltedfish.saltedcdd.model.game;

public class GameAction
{
    public CDDPlayer mPlayer;
    public boolean mIsPass;
    public CardGroup mCards;

    public static GameAction createPassAction(CDDPlayer player)
    {
        GameAction action = new GameAction();
        action.mPlayer = player;
        action.mIsPass = true;

        return action;
    }

    public static GameAction createShowCardAction(CDDPlayer player, CardGroup cards)
    {
        GameAction action = new GameAction();
        action.mPlayer = player;
        action.mIsPass = false;
        action.mCards = cards;

        return action;
    }
}

package cn.saltedfish.saltedcdd.model.game;

public class GameAction
{
    public CDDPlayer mPlayer;
    public boolean mIsPass;
    public CardCollection mCards;

    public static GameAction createPassAction(CDDPlayer player)
    {
        GameAction action = new GameAction();
        action.mPlayer = player;
        action.mIsPass = true;

        return action;
    }

    public static GameAction createShowCardAction(CDDPlayer player, CardCollection cards)
    {
        GameAction action = new GameAction();
        action.mPlayer = player;
        action.mIsPass = false;
        action.mCards = cards;

        return action;
    }
}

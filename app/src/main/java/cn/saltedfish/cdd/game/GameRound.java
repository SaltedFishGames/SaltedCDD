package cn.saltedfish.cdd.game;

import java.util.ArrayList;
import java.util.List;

public class GameRound {
    public List<GameAction> mActions = new ArrayList<GameAction>();

    public void add(GameAction action)
    {
        mActions.add(action);
    }

    public GameAction getLastShowCardAction()
    {
        for (int i = mActions.size() - 1; i >= 0; i--)
        {
            GameAction action = mActions.get(i);
            if (action.mType == TurnActionTypeEnum.ShowCard)
            {
                return action;
            }
        }
        return null;
    }
}

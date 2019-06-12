package cn.saltedfish.saltedcdd.model.game;

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
            if (!mActions.get(i).mIsPass)
            {
                return mActions.get(i);
            }
        }
        return null;
    }
}

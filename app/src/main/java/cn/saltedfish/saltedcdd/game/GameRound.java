package cn.saltedfish.saltedcdd.game;

import java.util.ArrayList;
import java.util.List;

public class GameRound {
    public List<PlayerAction> mActions;

    public int mIndex;

    public GameRound(int pIndex)
    {
        mIndex = pIndex;
        mActions = new ArrayList<>();
    }

    public void add(PlayerAction pAction)
    {
        mActions.add(pAction);
    }

    public PlayerAction getLastShowCardAction()
    {
        for (int i = mActions.size() - 1; i >= 0; i--)
        {
            PlayerAction action = mActions.get(i);
            if (action.mAction == EActionType.ShowCard)
            {
                return action;
            }
        }
        return null;
    }

    public int getContinuousPassActionCount()
    {
        int count = 0;
        for (int i = mActions.size() - 1; i >= 0; i--)
        {
            PlayerAction action = mActions.get(i);
            if (action.mAction == EActionType.ShowCard)
            {
                break;
            }
            count++;
        }
        return count;
    }
}

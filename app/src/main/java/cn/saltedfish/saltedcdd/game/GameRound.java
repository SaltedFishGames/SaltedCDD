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
}

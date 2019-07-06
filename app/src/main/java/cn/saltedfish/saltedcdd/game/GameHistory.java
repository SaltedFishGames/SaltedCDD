package cn.saltedfish.saltedcdd.game;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
    public List<GameRound> mRounds = new ArrayList<>();

    public GameRound newRound()
    {
        GameRound newRound = new GameRound(mRounds.size());
        mRounds.add(newRound);
        return newRound;
    }

    public GameRound getCurrentRound()
    {
        if (mRounds.size() > 0)
        {
            return mRounds.get(mRounds.size() - 1);
        }
        else
        {
            return null;
        }
    }
}

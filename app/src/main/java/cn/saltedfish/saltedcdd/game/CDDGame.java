package cn.saltedfish.saltedcdd.game;

import cn.saltedfish.saltedcdd.game.card.CardFactory;
import cn.saltedfish.saltedcdd.game.fourplayer.GameStateFactory;

public abstract class CDDGame {
    protected Player[] mPlayers;

    protected GameBoard mBoard;

    protected IGameEventListener mEventListener;

    protected GameState mCurrentState;

    protected CardFactory mCardFactory;

    public abstract void prepare(Player[] pPlayers);

    public abstract void start();

    public abstract void onPlayerAction();

    public void setEventListener(IGameEventListener pListener)
    {
        mEventListener = pListener;
    }

    protected void enterState(GameState pNewState)
    {
        mCurrentState = pNewState;
    }

    public Player getPlayer(int id)
    {
        return mPlayers[id];
    }
}

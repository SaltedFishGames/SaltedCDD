package cn.saltedfish.saltedcdd.game;

public abstract class CDDGame {
    protected Player[] mPlayers;

    protected GameBoard mBoard;

    protected IGameEventListener mEventListener;

    protected GameState mCurrentState;

    public abstract void handleStartGame();

    public abstract void handlePlayerAction();

    public void setEventListener(IGameEventListener pListener)
    {
        mEventListener = pListener;
    }

    public void enterState(GameState pNewState)
    {
        mCurrentState = pNewState;
    }


}

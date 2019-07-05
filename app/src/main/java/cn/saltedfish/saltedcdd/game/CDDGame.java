package cn.saltedfish.saltedcdd.game;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;

public abstract class CDDGame {
    protected Player[] mPlayers;

    protected GameBoard mBoard;

    protected CardFactory mCardFactory;

    protected GameHistory mHistory;

    protected IGameEventListener mEventListener;

    protected GameState mCurrentState;

    protected Player mCurrentTurn;

    public Player getPlayer(int id)
    {
        return mPlayers[id];
    }

    public abstract void prepare(Player[] pPlayers);

    public abstract void start();

    public abstract void onPlayerAction(Player pPlayer, EActionType pAction, Collection<Card> pCards);

    public abstract void enterNewRound();

    public void setEventListener(IGameEventListener pListener)
    {
        mEventListener = pListener;
    }

    protected void enterState(GameState pNewState)
    {
        if (mCurrentState != null)
        {
            mCurrentState.onLeaveState(this);
        }

        mCurrentState = pNewState;

        if (pNewState != null)
        {
            pNewState.onEnterState(this);
        }

    }

    public Player getCurrentTurnedPlayer()
    {
        return mCurrentTurn;
    }

    public void setCurrentTurnedPlayer(Player pPlayer)
    {
        mCurrentTurn = pPlayer;
    }
}

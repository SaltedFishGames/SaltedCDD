package cn.saltedfish.saltedcdd.game;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;

public abstract class CDDGame implements IGameStateInterface {
    protected Player[] mPlayers;

    protected GameBoard mBoard;

    protected CardFactory mCardFactory;

    protected GameHistory mHistory;

    protected IGameEventListener mEventListener;

    protected GameState mCurrentState;

    protected Player mCurrentTurn;

    protected HashMap<Class<? extends GameState>, GameState> mStateCache = new HashMap<>();

    public void setEventListener(IGameEventListener pListener)
    {
        mEventListener = pListener;
    }

    // msg from upper level
    public abstract void prepare(Player[] pPlayers);

    public abstract void startGame();

    public abstract void onGameEnded();

    public abstract void onPlayerAction(Player pPlayer, EActionType pAction, Collection<Card> pCards);

    // msg from state
    public void enterState(Class<? extends GameState> pNewStateClass)
    {
        GameState newState = mStateCache.get(pNewStateClass);

        if (newState == null)
        {
            try
            {
                newState = pNewStateClass.newInstance();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            if (newState == null)
            {
                return;
            }
            mStateCache.put(pNewStateClass, newState);
        }

        if (mCurrentState != null)
        {
            mCurrentState.onLeaveState(this);
        }

        mCurrentState = newState;

        if (newState != null)
        {
            newState.onEnterState(this);
        }
    }

    public void enterNewRound()
    {
        mHistory.newRound();
        if (mEventListener != null)
        {
            mEventListener.onNewRound();
        }
    }

    public void setCurrentTurnedPlayer(Player pPlayer)
    {
        mCurrentTurn = pPlayer;
        if (mEventListener != null)
        {
            mEventListener.onPlayerTurn(pPlayer);
        }
    }

    // get context data
    public abstract boolean isActionAllowed(Player pPlayer, EActionType pAction, Collection<Card> pCards);

    public GameRound getCurrentRound()
    {
        return mHistory.getCurrentRound();
    }

    public Player getCurrentTurnedPlayer()
    {
        return mCurrentTurn;
    }

    public Player getPlayer(int id)
    {
        return mPlayers[id];
    }

    public boolean curStateIs(Class<? extends GameState> pStateClass)
    {
        return mCurrentState.getClass() == pStateClass;
    }

    public abstract void turnToNextPlayer();
}

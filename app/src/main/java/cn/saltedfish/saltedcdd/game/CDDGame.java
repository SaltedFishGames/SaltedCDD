package cn.saltedfish.saltedcdd.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;

public abstract class CDDGame implements IGameOperationBridge {
    protected Player[] mPlayers;

    protected GameBoard mBoard;

    protected CardFactory mCardFactory;

    protected GameHistory mHistory;

    protected IGameEventListener mEventListener;

    protected GameState mCurrentState;

    protected Player mCurrentTurnedPlayer;

    protected HashMap<Class<? extends GameState>, GameState> mStateCache = new HashMap<>();

    public void setEventListener(IGameEventListener pListener)
    {
        mEventListener = pListener;
    }

    // msg from upper level
    public abstract void prepare(Player[] pPlayers);

    public abstract void startGame();

    public abstract PlayerAction onPlayerShowCard(Player pPlayer, List<Card> pCards);

    public abstract PlayerAction onPlayerPass(Player pPlayer);

    public abstract boolean isShowCardAllowed(Player pPlayer, List<Card> pCards);

    public abstract boolean isShowCardAllowed(Player pPlayer, CardGroup pCards);

    public abstract boolean isPassAllowed(Player pPlayer);

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

    public void onPrepared()
    {
        if (mEventListener != null)
        {
            mEventListener.onGamePrepared();
        }
    }

    public void onEnterNewRound()
    {
        GameRound newRound = mHistory.newRound();
        if (mEventListener != null)
        {
            mEventListener.onNewRound(newRound);
        }
    }

    public void onGameEnded()
    {
        if (mEventListener != null)
        {
            mEventListener.onGameEnded();
        }
    }

    public void setCurrentTurnedPlayer(Player pPlayer)
    {
        mCurrentTurnedPlayer = pPlayer;
        if (mEventListener != null)
        {
            TurnHint hint = new TurnHint(getCurrentRound(), pPlayer, this);
            mEventListener.onPlayerTurn(pPlayer, hint);
        }
    }

    // get context data
    public GameRound getCurrentRound()
    {
        return mHistory.getCurrentRound();
    }

    public Player getCurrentTurnedPlayer()
    {
        return mCurrentTurnedPlayer;
    }

    public Player getPlayer(int id)
    {
        return mPlayers[id];
    }

    public boolean isInState(Class<? extends GameState> pStateClass)
    {
        return mCurrentState.getClass() == pStateClass;
    }

    public boolean isValidAction(PlayerAction pAction)
    {
        if (pAction.getPlayer() != getCurrentTurnedPlayer())
            return false;

        if (pAction.getCardGroup() != null)
        {
            if (pAction.getCardGroup().getType() == EPatternType.Unknown)
                return false;
            if (!pAction.getPlayer().hasCards(pAction.getCardGroup().cards()))
                return false;
        }

        return true;
    }

    public abstract Player getNextPlayer(Player pThisPlayer);

    public abstract int getPlayerCount();
}

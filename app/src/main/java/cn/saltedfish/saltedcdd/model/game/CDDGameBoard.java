package cn.saltedfish.saltedcdd.model.game;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;

import cn.saltedfish.saltedcdd.ShowCardResult;

public class CDDGameBoard implements PlayerActionListener {
    protected CDDGameBase mGame;
    protected CDDPlayer[] mPlayerSeq;
    protected GameEventListener mListener;

    protected SparseArray<CDDPlayer> mPlayerDict;

    protected CDDPlayer mCurrentPlayer;
    protected GameStateBase mCurrentGameState;

    protected CardCollection mShowedCards;

    protected ArrayList<GameRound> mRounds = new ArrayList<>();

    public CDDGameBoard(CDDPlayer[] playerSeq, CDDGameBase game)
    {
        mGame = game;
        mPlayerSeq = Arrays.copyOf(playerSeq, playerSeq.length);

        mPlayerDict = new SparseArray<>(playerSeq.length);
        for (int i = 0; i < playerSeq.length; i++)
        {
            mPlayerDict.append(playerSeq[i].getPlayerId(), playerSeq[i]);
        }
    }

    public CDDPlayer getPlayer(int playerId)
    {
        return mPlayerDict.get(playerId);
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

    public void setGameEventListener(GameEventListener listener)
    {
        mListener = listener;
    }

    protected void reset()
    {
        mShowedCards.clear();
        mRounds.clear();
    }

    public void startGame()
    {
        reset();
        mGame.distributeCards(mPlayerSeq);

        if (mListener != null)
        {
            mListener.onGameStart();
        }

        setCurrentGameState(mGame.getInitialState());
    }

    public void endGame(GameResult result)
    {
        if (mListener != null)
        {
            mListener.onGameEnd(result);
        }
    }

    void enterPlayerTurn(CDDPlayer player, boolean isNewRound)
    {
        if (isNewRound)
        {
            mRounds.add(new GameRound());
        }

        if (mListener != null)
        {
            mListener.onPlayerTurn(player, isNewRound);
        }
    }

    void setCurrentGameState(GameStateBase newState)
    {
        mCurrentGameState = newState;
        mCurrentGameState.onEnterState(this);
    }

    public ShowCardResult tryPlayerShowCard(CDDPlayer player, CardCollection cards)
    {
        return mCurrentGameState.tryPlayerShowCard(this, player, cards);
    }

    public ShowCardResult onPlayerShowCard(CDDPlayer player, CardCollection cards)
    {
        ShowCardResult result = mCurrentGameState.onPlayerShowCard(this, player, cards);
        if (result == ShowCardResult.SUCCESS)
        {
            GameAction action = GameAction.createShowCardAction(player, cards);
            getCurrentRound().add(action);

            mShowedCards.addAll(cards);
        }
        return result;
    }

    public void onPlayerPass(CDDPlayer player, CardCollection cards)
    {
        mCurrentGameState.onPlayerPass(this, player);
        getCurrentRound().add(GameAction.createPassAction(player));
    }


}


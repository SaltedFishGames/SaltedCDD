package cn.saltedfish.saltedcdd.game.routine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameBoard;
import cn.saltedfish.saltedcdd.game.GameHistory;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class FourPlayerGame extends CDDGame {
    public static final int PlayerCount = 4;

    public static final int PlayerInitialCardNum = 52 / PlayerCount;

    public FourPlayerGame()
    {
        mBoard = new GameBoard();
        mCardFactory = new CardFactory();
        mHistory = new GameHistory();

        enterState(IdleState.class);
    }

    public void dealCards()
    {
        Card[] deck = mCardFactory.createDeck();
        List<Card> cardList = Arrays.asList(deck);

        Random rand = new Random();
        Collections.shuffle(cardList, rand);

        for (int i = 0; i < mPlayers.length; i++)
        {
            mPlayers[i].mId = i;
            mPlayers[i].mCards = new ArrayList<>(cardList.subList(i * PlayerInitialCardNum, (i + 1) * PlayerInitialCardNum));
        }
    }

    protected void decideFirstPlayer()
    {
        for (int i = 0; i < mPlayers.length; i++)
        {
            if (mPlayers[i].hasCard(ECardNumber.NUM_3, ECardSuit.DIAMOND))
            {
                setCurrentTurnedPlayer(mPlayers[i]);
                return;
            }
        }
    }

    @Override
    public void prepare(Player[] pPlayers)
    {
        mPlayers = Arrays.copyOf(pPlayers, pPlayers.length);

        if (pPlayers.length == PlayerCount)
        {
            mCurrentState.onPrepare(this);
        }
    }

    @Override
    public void startGame()
    {
        mCurrentState.onStartGame(this);
    }

    @Override
    public void onGameEnded()
    {
        if (mEventListener != null)
        {
            mEventListener.onGameEnded();
        }
    }

    @Override
    public boolean isActionAllowed(Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        if (mCurrentState != null)
        {
            return mCurrentState.isActionAllowed(this, pPlayer, pAction, pCards);
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onPlayerAction(Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        CardGroup group = PatternRecognizer.recognize(pCards);
        if (group.mType != EPatternType.Unknown)
        {
            mHistory.getCurrentRound().add(new PlayerAction(pPlayer.mId, pAction, group));
            if (mEventListener != null)
            {
                mEventListener.onPlayerAction(pPlayer, pAction, group.mCards);
            }
        }
    }

    @Override
    public void turnToNextPlayer()
    {
        setCurrentTurnedPlayer(mPlayers[(getCurrentTurnedPlayer().mId + 1) % PlayerCount]);
    }
}

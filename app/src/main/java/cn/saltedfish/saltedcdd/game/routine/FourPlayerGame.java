package cn.saltedfish.saltedcdd.game.routine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameBoard;
import cn.saltedfish.saltedcdd.game.GameHistory;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;

public class FourPlayerGame extends CDDGame {
    protected EnumMap<EGameState, GameState> mStateCache = new EnumMap<>(EGameState.class);

    protected static final int PlayerCount = 4;

    protected static final int PlayerInitialCardNum = 52 / PlayerCount;

    public FourPlayerGame()
    {
        mBoard = new GameBoard();
        mCardFactory = new CardFactory();
        mHistory = new GameHistory();

        for (EGameState state : EGameState.values())
        {
            try
            {
                mStateCache.put(state, state.getClazz().newInstance());
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
        }

        enterState(EGameState.Idle);
    }

    @Override
    public void prepare(Player[] pPlayers)
    {
        if (curStateIs(EGameState.Idle) && pPlayers.length == PlayerCount)
        {
            mPlayers = Arrays.copyOf(pPlayers, pPlayers.length);

            dealCards();
            enterState(EGameState.Prepared);
        }
    }

    protected void dealCards()
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

    @Override
    public void start()
    {
        if (curStateIs(EGameState.Prepared))
        {
            enterState(EGameState.RoundHead);
        }
    }

    @Override
    public void onPlayerAction(Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        // mHistory.getCurrentRound().add(new PlayerAction(pPlayer.mId, pAction, ));
    }

    @Override
    public void enterNewRound()
    {
        mHistory.newRound();
    }

    protected GameState getState(EGameState pState)
    {
        return mStateCache.get(pState);
    }

    public void enterState(EGameState pState)
    {
        enterState(getState(pState));
    }

    public boolean curStateIs(EGameState pState)
    {
        return mCurrentState == getState(pState);
    }
}

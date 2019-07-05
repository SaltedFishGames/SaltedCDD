package cn.saltedfish.saltedcdd.game.fourplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.GameBoard;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;

public class FourPlayerGame extends CDDGame {
    protected EnumMap<EGameState, GameState> mStateCache = new EnumMap<>(EGameState.class);

    protected static final int PlayerInitialCardNum = 52 / 4;

    public FourPlayerGame()
    {
        mBoard = new GameBoard();
        mCardFactory = new CardFactory();

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
    }

    @Override
    public void prepare(Player[] pPlayers)
    {
        mPlayers = Arrays.copyOf(pPlayers, pPlayers.length);

        dealCards();
        enterState(EGameState.Prepared);
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

    }

    @Override
    public void onPlayerAction()
    {

    }

    @Override
    protected void enterState(GameState pNewState)
    {
        super.enterState(pNewState);
    }

    public void enterState(EGameState pState)
    {
        enterState(mStateCache.get(pState));
    }
}

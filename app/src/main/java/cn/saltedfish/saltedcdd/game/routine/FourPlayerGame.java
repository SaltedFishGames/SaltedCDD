package cn.saltedfish.saltedcdd.game.routine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameBoard;
import cn.saltedfish.saltedcdd.game.GameHistory;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.CardFactory;

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
            List<Card> playerCards = mPlayers[i].cards();

            mPlayers[i].setId(i);

            playerCards.clear();
            playerCards.addAll(cardList.subList(i * PlayerInitialCardNum, (i + 1) * PlayerInitialCardNum));
            Collections.sort(playerCards);
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
    public boolean onPlayerShowCard(Player pPlayer, List<Card> pCards)
    {
        PlayerAction action = new PlayerAction(pPlayer, EActionType.ShowCard, pCards);

        return handlePlayerAction(action);
    }

    @Override
    public boolean onPlayerPass(Player pPlayer)
    {
        PlayerAction action = new PlayerAction(pPlayer, EActionType.Pass, null);

        return handlePlayerAction(action);
    }

    protected boolean handlePlayerAction(PlayerAction pAction)
    {
        if (!isValidAction(pAction))
            return false;

        mCurrentState.onPlayerAction(this, pAction);

        if (pAction.isAccepted())
        {
            mHistory.getCurrentRound().add(pAction);

            if (mEventListener != null)
            {
                mEventListener.onPlayerAction(pAction);
            }

            if (pAction.getEnterNewState() != null)
            {
                enterState(pAction.getEnterNewState());
            }

            if (pAction.getTurnToPlayer() != null)
            {
                setCurrentTurnedPlayer(pAction.getTurnToPlayer());
            }
        }

        return pAction.isAccepted();
    }

    @Override
    public boolean isShowCardAllowed(Player pPlayer, List<Card> pCards)
    {
        PlayerAction action = new PlayerAction(pPlayer, EActionType.ShowCard, pCards);

        return isPlayerActionAllowed(action);
    }

    @Override
    public boolean isPassAllowed(Player pPlayer)
    {
        PlayerAction action = new PlayerAction(pPlayer, EActionType.Pass, null);

        return isPlayerActionAllowed(action);
    }

    protected boolean isPlayerActionAllowed(PlayerAction pAction)
    {
        if (!isValidAction(pAction))
            return false;

        return mCurrentState.isActionAllowed(this, pAction);
    }

    @Override
    public Player getNextPlayer(Player pThisPlayer)
    {
        return mPlayers[(pThisPlayer.getId() + 1) % PlayerCount];
    }

    @Override
    public int getPlayerCount()
    {
        return PlayerCount;
    }
}

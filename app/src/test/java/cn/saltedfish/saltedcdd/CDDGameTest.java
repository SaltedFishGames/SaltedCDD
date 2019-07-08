package cn.saltedfish.saltedcdd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IGameEventListener;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.routine.FourPlayerGame;

import static org.junit.Assert.*;

public class CDDGameTest {
    @Test
    public void testDealCards()
    {
        FourPlayerGame game = new FourPlayerGame();
        Player[] ps = new Player[] {new Player(), new Player(), new Player(), new Player()};

        game.prepare(ps);

        assertEquals(13, ps[0].getCardCount());
        assertEquals(13, ps[1].getCardCount());
        assertEquals(13, ps[2].getCardCount());
        assertEquals(13, ps[3].getCardCount());
    }

    @Test
    public void testDecideFirstPlayer()
    {
        FourPlayerGame game = new FourPlayerGame();
        Player[] ps = new Player[] {new Player(), new Player(), new Player(), new Player()};

        Player exceptedPlayer = null;

        game.prepare(ps);

        for (Player p : ps)
        {
            if (p.hasCard(ECardNumber.NUM_3, ECardSuit.DIAMOND))
            {
                exceptedPlayer = p;
                break;
            }
        }

        game.startGame();

        assertEquals(exceptedPlayer, game.getCurrentTurnedPlayer());
    }


    protected static void printPlayerCards(Player player)
    {
        StringBuilder sb = new StringBuilder();

        for (Card card : player.cards())
        {
            sb.append(card.toString() + ", ");
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testFullRoutine()
    {
        final ValueContainer<Boolean> preparedFlag = new ValueContainer<>(false);
        final ValueContainer<Boolean> endedFlag = new ValueContainer<>(false);
        final ValueContainer<Player> currentTurnedPlayer = new ValueContainer<>(null);
        final ValueContainer<GameRound> currentRound = new ValueContainer<>(null);
        IGameEventListener listener = new IGameEventListener() {
            @Override
            public void onGamePrepared()
            {
                preparedFlag.setValue(true);
                System.out.println("[Game] Prepared");
            }

            @Override
            public void onNewRound(GameRound pNewRound)
            {
                System.out.println("[Game] New round");
            }

            @Override
            public void onPlayerTurn(Player pPlayer, GameRound pCurRound)
            {
                currentTurnedPlayer.setValue(pPlayer);
                currentRound.setValue(pCurRound);
                System.out.printf("[Game] Turned to Player #%d\n", pPlayer.getId());
            }

            @Override
            public void onPlayerAction(PlayerAction pAction)
            {
                switch (pAction.getType())
                {
                    case ShowCard:
                        System.out.printf("[Action] ShowCard Player #%d, Cards=%s\n", pAction.getPlayer().getId(), pAction.getCardGroup());
                        break;
                    case Pass:
                        System.out.printf("[Action] Pass Player #%d\n", pAction.getPlayer().getId());
                        break;
                }
            }

            @Override
            public void onGameEnded()
            {
                endedFlag.setValue(true);
                System.out.println("[Game] Ended");
            }
        };

        FourPlayerGame game = new FourPlayerGame();
        game.setEventListener(listener);

        // --- idle -> prepared ---
        Player[] players = new Player[] { new Player(), new Player(), new Player(), new Player() };
        game.prepare(players);

        assertTrue(preparedFlag.getValue());

        Player exceptedFirstPlayer = null;
        for (Player p : players)
        {
            if (p.hasCard(ECardNumber.NUM_3, ECardSuit.DIAMOND))
            {
                exceptedFirstPlayer = p;
                break;
            }
        }
        Player secondTurnedPlayer = players[(exceptedFirstPlayer.getId() + 1) % 4];

        // dealing card result check
        assertEquals(13, players[0].getCardCount());
        assertEquals(13, players[1].getCardCount());
        assertEquals(13, players[2].getCardCount());
        assertEquals(13, players[3].getCardCount());

        // --- first round ---
        game.startGame();
        GameRound firstRound = currentRound.getValue();
        assertEquals(exceptedFirstPlayer, currentTurnedPlayer.getValue());

        // first player cannot pass
        assertFalse(game.isPassAllowed(game.getCurrentTurnedPlayer()));
        assertFalse(game.onPlayerPass(game.getCurrentTurnedPlayer()));

        // not current player cannot do any action
        List<Card> cards = new ArrayList<>();
        cards.add(secondTurnedPlayer.cards().get(0));
        assertFalse(game.isShowCardAllowed(secondTurnedPlayer, new ArrayList<Card>(cards)));
        assertFalse(game.isPassAllowed(secondTurnedPlayer));

        // first player show a card
        cards.clear();
        cards.add(exceptedFirstPlayer.cards().get(0));
        assertTrue(game.isShowCardAllowed(exceptedFirstPlayer, new ArrayList<Card>(cards)));
        assertTrue(game.onPlayerShowCard(exceptedFirstPlayer, new ArrayList<Card>(cards)));
        assertEquals(12, exceptedFirstPlayer.getCardCount());

        // turned to next player
        assertEquals(secondTurnedPlayer, currentTurnedPlayer.getValue());

        // assume player 2 has only 2 cards now
        secondTurnedPlayer.takeAwayCards(secondTurnedPlayer.cards().subList(0, 11));

        assertEquals(2, secondTurnedPlayer.getCardCount());
        cards.clear();
        cards.add(secondTurnedPlayer.cards().get(0));
        assertTrue(game.onPlayerShowCard(secondTurnedPlayer, new ArrayList<Card>(cards)));

        // now player 2 has only 1 card

        // other player pass, enter a new round
        assertTrue(game.onPlayerPass(game.getCurrentTurnedPlayer()));
        assertTrue(game.onPlayerPass(game.getCurrentTurnedPlayer()));
        assertEquals(exceptedFirstPlayer, currentTurnedPlayer.getValue());
        assertEquals(currentRound.getValue(), firstRound);

        game.onPlayerPass(game.getCurrentTurnedPlayer());
        assertEquals(secondTurnedPlayer, currentTurnedPlayer.getValue());
        assertNotEquals(currentRound.getValue(), firstRound);

        cards.clear();
        cards.add(secondTurnedPlayer.cards().get(0));
        assertTrue(game.onPlayerShowCard(secondTurnedPlayer, new ArrayList<Card>(cards)));
        assertTrue(endedFlag.getValue());
    }
}


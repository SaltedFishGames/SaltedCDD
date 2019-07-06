package cn.saltedfish.saltedcdd;

import org.junit.Test;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.game.Player;
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

        /*
        StringBuilder sb = new StringBuilder();

        for (Card card : exceptedPlayer.mCards)
        {
            sb.append(card.toString() + ", ");
        }
        System.out.println(sb.toString());
         */
    }

    @Test
    public void testPairCard()
    {
        // CardGroup c1 = new CardGroup(new Card[] {  });
    }
}

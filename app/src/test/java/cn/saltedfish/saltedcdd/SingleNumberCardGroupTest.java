package cn.saltedfish.saltedcdd;

import org.junit.Test;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.model.game.CDDPlayer;
import cn.saltedfish.saltedcdd.model.game.SingleNumberCardGroup;

import static org.junit.Assert.*;

public class SingleNumberCardGroupTest {
    @Test
    public void testAdd()
    {
        SingleNumberCardGroup group = new SingleNumberCardGroup(CardNumberEnum.NUMBER_3);
        group.add(CardSuitEnum.CLUB);
        group.add(CardSuitEnum.CLUB);
        group.add(CardSuitEnum.HEART);

        assertTrue(group.contains(CardSuitEnum.CLUB));
        assertTrue(group.contains(CardSuitEnum.HEART));
        assertFalse(group.contains(CardSuitEnum.DIAMOND));
        assertFalse(group.contains(CardSuitEnum.SPADE));
    }

    @Test
    public void testRemove()
    {
        SingleNumberCardGroup group = new SingleNumberCardGroup(CardNumberEnum.NUMBER_3);
        group.add(CardSuitEnum.CLUB);
        group.add(CardSuitEnum.CLUB);
        group.add(CardSuitEnum.HEART);

        assertTrue(group.remove(CardSuitEnum.HEART));
        assertFalse(group.remove(CardSuitEnum.HEART));
        assertFalse(group.remove(CardSuitEnum.DIAMOND));

        assertTrue(group.contains(CardSuitEnum.CLUB));
        assertFalse(group.contains(CardSuitEnum.HEART));
    }

    @Test
    public void testIterator()
    {
        SingleNumberCardGroup group = new SingleNumberCardGroup(CardNumberEnum.NUMBER_3);
        group.add(CardSuitEnum.CLUB);
        group.add(CardSuitEnum.CLUB);

        group.add(CardSuitEnum.HEART);
        group.add(CardSuitEnum.HEART);

        group.add(CardSuitEnum.CLUB);

        group.add(CardSuitEnum.DIAMOND);

        group.remove(CardSuitEnum.HEART);
        group.remove(CardSuitEnum.DIAMOND);

        ArrayList<CardSuitEnum> result = new ArrayList<>();
        int clubCount = 0;
        int heartCount = 0;
        int diamondCount = 0;
        int spadeCount = 0;


        for (CardSuitEnum suit : group)
        {
            switch (suit)
            {
                case CLUB:
                    clubCount++;
                    break;
                case DIAMOND:
                    diamondCount++;
                    break;
                case HEART:
                    heartCount++;
                    break;
                case SPADE:
                    spadeCount++;
                    break;
                default:
                    assertTrue(false);
                    break;
            }
        }
        assertEquals(3, clubCount);
        assertEquals(1, heartCount);
        assertEquals(0, diamondCount);
        assertEquals(0, spadeCount);
    }
}
package cn.saltedfish.saltedcdd;

import org.junit.Test;

import java.util.ArrayList;

import cn.saltedfish.cdd.card.CardNumberEnum;
import cn.saltedfish.cdd.card.CardSuitEnum;
import cn.saltedfish.cdd.card.NumberedSuitCollection;

import static org.junit.Assert.*;

public class NumberedSuitCollectionTest {
    @Test
    public void testCardNumber()
    {
        NumberedSuitCollection collection = new NumberedSuitCollection(CardNumberEnum.NUM_3);

        assertEquals(CardNumberEnum.NUM_3, collection.getCardNumber());
    }

    @Test
    public void testAdd()
    {
        NumberedSuitCollection collection = new NumberedSuitCollection(CardNumberEnum.NUM_3);

        collection.add(CardSuitEnum.CLUB);
        collection.add(CardSuitEnum.CLUB);
        collection.add(CardSuitEnum.HEART);

        assertTrue(collection.contains(CardSuitEnum.CLUB));
        assertTrue(collection.contains(CardSuitEnum.HEART));
        assertFalse(collection.contains(CardSuitEnum.DIAMOND));
        assertFalse(collection.contains(CardSuitEnum.SPADE));

        assertEquals(2, collection.getCountBySuit(CardSuitEnum.CLUB));
        assertEquals(1, collection.getCountBySuit(CardSuitEnum.HEART));
        assertEquals(0, collection.getCountBySuit(CardSuitEnum.DIAMOND));
    }

    @Test
    public void testAddAll()
    {
        NumberedSuitCollection collection1 = new NumberedSuitCollection(CardNumberEnum.NUM_3);
        collection1.add(CardSuitEnum.CLUB);
        collection1.add(CardSuitEnum.CLUB);
        collection1.add(CardSuitEnum.HEART);

        NumberedSuitCollection collection2 = new NumberedSuitCollection(CardNumberEnum.NUM_3);
        collection2.add(CardSuitEnum.DIAMOND);
        collection2.add(CardSuitEnum.HEART);

        collection1.addAll(collection2);

        assertEquals(2, collection1.getCountBySuit(CardSuitEnum.CLUB));
        assertEquals(2, collection1.getCountBySuit(CardSuitEnum.HEART));
        assertEquals(1, collection1.getCountBySuit(CardSuitEnum.DIAMOND));
        assertEquals(0, collection1.getCountBySuit(CardSuitEnum.SPADE));
    }

    @Test
    public void testRemove()
    {
        NumberedSuitCollection collection = new NumberedSuitCollection(CardNumberEnum.NUM_3);
        collection.add(CardSuitEnum.CLUB);
        collection.add(CardSuitEnum.CLUB);
        collection.add(CardSuitEnum.HEART);

        assertTrue(collection.remove(CardSuitEnum.HEART));
        assertFalse(collection.remove(CardSuitEnum.HEART));
        assertFalse(collection.remove(CardSuitEnum.DIAMOND));

        assertTrue(collection.contains(CardSuitEnum.CLUB));
        assertFalse(collection.contains(CardSuitEnum.HEART));
    }

    @Test
    public void testGetAllSuits()
    {
        NumberedSuitCollection collection = new NumberedSuitCollection(CardNumberEnum.NUM_3);
        collection.add(CardSuitEnum.CLUB);
        collection.add(CardSuitEnum.CLUB);

        collection.add(CardSuitEnum.HEART);
        collection.add(CardSuitEnum.HEART);

        collection.add(CardSuitEnum.CLUB);

        collection.add(CardSuitEnum.DIAMOND);

        collection.remove(CardSuitEnum.HEART);
        collection.remove(CardSuitEnum.DIAMOND);

        ArrayList<CardSuitEnum> result = new ArrayList<>();
        int clubCount = 0;
        int heartCount = 0;
        int diamondCount = 0;
        int spadeCount = 0;

        for (CardSuitEnum suit : collection.suits())
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
                    fail();
                    break;
            }
        }
        assertEquals(3, clubCount);
        assertEquals(1, heartCount);
        assertEquals(0, diamondCount);
        assertEquals(0, spadeCount);
    }

    @Test
    public void testClear()
    {
        NumberedSuitCollection collection = new NumberedSuitCollection(CardNumberEnum.NUM_3);
        collection.add(CardSuitEnum.CLUB);
        collection.add(CardSuitEnum.CLUB);

        collection.add(CardSuitEnum.HEART);
        collection.add(CardSuitEnum.HEART);

        collection.add(CardSuitEnum.CLUB);

        assertEquals(5, collection.getTotalCount());
        assertEquals(3, collection.getCountBySuit(CardSuitEnum.CLUB));
        assertEquals(0, collection.getCountBySuit(CardSuitEnum.DIAMOND));

        collection.clear();

        assertEquals(0, collection.getTotalCount());
        assertEquals(0, collection.getCountBySuit(CardSuitEnum.CLUB));
        assertEquals(0, collection.getCountBySuit(CardSuitEnum.DIAMOND));
    }
}
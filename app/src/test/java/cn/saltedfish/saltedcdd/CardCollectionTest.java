package cn.saltedfish.saltedcdd;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

import cn.saltedfish.cdd.card.CardEnum;
import cn.saltedfish.cdd.card.CardNumberEnum;
import cn.saltedfish.cdd.card.CardCollection;

public class CardCollectionTest {
    @Test
    public void testCreate()
    {
        CardCollection collection1 = new CardCollection();
        assertEquals(0, collection1.getTotalCount());

        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_10);

        CardCollection collection2 = new CardCollection(cards1);
        assertEquals(3, collection2.getTotalCount());
        assertEquals(2, collection2.getCountForNumber(CardNumberEnum.NUM_2));
        assertEquals(1, collection2.getCountForNumber(CardNumberEnum.NUM_10));
        assertEquals(0, collection2.getCountForNumber(CardNumberEnum.NUM_4));
    }

    @Test
    public void testAdd()
    {
        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_10);

        CardCollection collection1 = new CardCollection(cards1);
        assertEquals(3, collection1.getTotalCount());
        assertEquals(2, collection1.getCountForNumber(CardNumberEnum.NUM_2));
        assertEquals(1, collection1.getCountForNumber(CardNumberEnum.NUM_10));
        assertEquals(0, collection1.getCountForNumber(CardNumberEnum.NUM_4));

        collection1.add(CardEnum.SPADE_2);
        assertEquals(4, collection1.getTotalCount());
        assertEquals(3, collection1.getCountForNumber(CardNumberEnum.NUM_2));

        collection1.add(CardEnum.DIAMOND_2);
        assertEquals(5, collection1.getTotalCount());
        assertEquals(4, collection1.getCountForNumber(CardNumberEnum.NUM_2));

        collection1.add(CardEnum.DIAMOND_3);
        assertEquals(6, collection1.getTotalCount());
        assertEquals(1, collection1.getCountForNumber(CardNumberEnum.NUM_3));

        ArrayList<CardEnum> cards2 = new ArrayList<>();
        cards2.add(CardEnum.SPADE_2);
        cards2.add(CardEnum.DIAMOND_3);
        cards2.add(CardEnum.HEART_10);

        collection1.addAll(cards2);
        assertEquals(9, collection1.getTotalCount());
        assertEquals(2, collection1.getCountForNumber(CardNumberEnum.NUM_10));

        assertTrue(collection1.contains(CardEnum.HEART_10));
        assertTrue(collection1.contains(CardEnum.DIAMOND_3));
        assertFalse(collection1.contains(CardEnum.DIAMOND_4));
        assertTrue(collection1.contains(CardEnum.SPADE_2));

        assertTrue(collection1.contains(CardNumberEnum.NUM_3));
        assertTrue(collection1.contains(CardNumberEnum.NUM_3, 2));
        assertFalse(collection1.contains(CardNumberEnum.NUM_3, 3));
        assertFalse(collection1.contains(CardNumberEnum.NUM_5, 2));
        assertFalse(collection1.contains(CardNumberEnum.NUM_5));
    }

    @Test
    public void testRemove()
    {
        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.DIAMOND_2);
        cards1.add(CardEnum.SPADE_4);
        cards1.add(CardEnum.SPADE_10);

        CardCollection collection1 = new CardCollection(cards1);
        assertTrue(collection1.remove(CardEnum.SPADE_2));
        assertTrue(collection1.remove(CardEnum.SPADE_2));
        assertFalse(collection1.remove(CardEnum.SPADE_2));

        assertFalse(collection1.contains(CardEnum.SPADE_2));

        assertTrue(collection1.remove(CardEnum.SPADE_4));
        assertFalse(collection1.remove(CardEnum.SPADE_4));

        assertFalse(collection1.remove(CardEnum.SPADE_3));
    }

    @Test
    public void testClear()
    {
        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.DIAMOND_2);
        cards1.add(CardEnum.SPADE_4);
        cards1.add(CardEnum.SPADE_10);

        CardCollection collection1 = new CardCollection(cards1);
        collection1.clear();

        assertFalse(collection1.contains(CardEnum.SPADE_2));
        assertFalse(collection1.contains(CardEnum.SPADE_2));

        assertEquals(0, collection1.getTotalCount());
        assertEquals(0, collection1.getCountForNumber(CardNumberEnum.NUM_2));
    }

    @Test
    public void testAddAll()
    {
        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.DIAMOND_2);
        cards1.add(CardEnum.SPADE_4);
        cards1.add(CardEnum.SPADE_10);

        ArrayList<CardEnum> cards2 = new ArrayList<>();
        cards2.add(CardEnum.SPADE_2);
        cards2.add(CardEnum.DIAMOND_3);
        cards2.add(CardEnum.CLUB_3);
        cards2.add(CardEnum.HEART_4);
        cards2.add(CardEnum.HEART_4);


        CardCollection collection1 = new CardCollection(cards1);
        assertEquals(5, collection1.getTotalCount());

        CardCollection collection2 = new CardCollection(cards2);
        collection2.remove(CardEnum.HEART_4);
        collection2.remove(CardEnum.HEART_4);

        collection1.addAll(collection2);
        assertEquals(8, collection1.getTotalCount());
    }

    @Test
    public void testGetNumberedCollections()
    {
        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.DIAMOND_2);
        cards1.add(CardEnum.SPADE_4);
        cards1.add(CardEnum.SPADE_10);

        CardCollection collection1 = new CardCollection(cards1);
        assertEquals(3, collection1.numberedCollections().size());
    }

    @Test
    public void testGetCards()
    {
        ArrayList<CardEnum> cards1 = new ArrayList<>();
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.SPADE_2);
        cards1.add(CardEnum.DIAMOND_2);
        cards1.add(CardEnum.SPADE_4);
        cards1.add(CardEnum.SPADE_10);

        CardCollection collection1 = new CardCollection(cards1);
        assertEquals(5, collection1.cards().size());
    }

}

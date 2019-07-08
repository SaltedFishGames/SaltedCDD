package cn.saltedfish.saltedcdd.game.pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

import static org.junit.Assert.*;

public class EPatternTypeTest {
    public static List<Card> createCardList(String... cardDescArray)
    {
        ArrayList<Card> cards = new ArrayList<>();
        for (String cardDesc : cardDescArray)
        {
            Card card = Card.fromString(cardDesc);
            if (card != null)
            {
                cards.add(card);
            }
        }
        return cards;
    }

    @Test
    public void recognizeSingleTest()
    {
        List<Card> cardList = createCardList("♦4", "♦3", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Single.potentialCardGroup(annotatedCards);
        assertEquals(3, potentialList.size());

        CardGroup c = potentialList.get(0);
        c.recognize();
        assertEquals("♦3", c.getCriticalCard().toString());

        c = potentialList.get(1);
        c.recognize();
        assertEquals("♦4", c.getCriticalCard().toString());

        c = potentialList.get(2);
        c.recognize();
        assertEquals("♠5", c.getCriticalCard().toString());
    }

    @Test
    public void recognizePairTest()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Pair.potentialCardGroup(annotatedCards);
        assertEquals(1, potentialList.size());

        CardGroup c = potentialList.get(0);
        c.recognize();
        assertEquals("♠4", c.getCriticalCard().toString());
    }

    @Test
    public void recognizePairTest2()
    {
        List<Card> cardList = createCardList("♦4", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Pair.potentialCardGroup(annotatedCards);
        assertEquals(0, potentialList.size());
    }

    @Test
    public void recognizePairTest3()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Pair.potentialCardGroup(annotatedCards);
        assertEquals(3, potentialList.size());
    }

    @Test
    public void recognizePairTest4()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♣4", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Pair.potentialCardGroup(annotatedCards);
        assertEquals(6, potentialList.size());
    }

    @Test
    public void recognizeTripleTest()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Triple.potentialCardGroup(annotatedCards);
        assertEquals(1, potentialList.size());

        CardGroup c = potentialList.get(0);
        c.recognize();
        assertEquals("♠4", c.getCriticalCard().toString());
    }

    @Test
    public void recognizeTripleTest2()
    {
        List<Card> cardList = createCardList("♦4", "♣4", "♥4", "♠4", "♠5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Triple.potentialCardGroup(annotatedCards);
        assertEquals(4, potentialList.size());

        CardGroup c = potentialList.get(0);
        c.recognize();
        assertEquals("♥4", c.getCriticalCard().toString());

        c = potentialList.get(3);
        c.recognize();
        assertEquals("♠4", c.getCriticalCard().toString());
    }

    @Test
    public void recognizeStraightTest()
    {
        List<Card> cardList = createCardList("♦3", "♦4", "♦5", "♦6", "♦7", "♦8");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Straight.potentialCardGroup(annotatedCards);

        assertEquals(2, potentialList.size());
    }

    @Test
    public void recognizeStraightTest2()
    {
        List<Card> cardList = createCardList("♦3", "♦4", "♦5", "♦6", "♦7", "♦8", "♦9");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Straight.potentialCardGroup(annotatedCards);

        assertEquals(3, potentialList.size());
    }

    @Test
    public void recognizeStraightTest3()
    {
        List<Card> cardList = createCardList("♦3", "♦4", "♦6", "♦7", "♦8", "♦9", "♦10", "♦J");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Straight.potentialCardGroup(annotatedCards);

        assertEquals(2, potentialList.size());
    }

    @Test
    public void recognizeFlushTest()
    {
        List<Card> cardList = createCardList("♦3", "♦4", "♦5", "♦6", "♦7", "♦8");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.Flush.potentialCardGroup(annotatedCards);

        assertEquals(6, potentialList.size());
    }

    @Test
    public void recognizeStraightFlushTest()
    {
        List<Card> cardList = createCardList("♦3", "♦4", "♦5", "♦6", "♦7", "♦8");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.StraightFlush.potentialCardGroup(annotatedCards);
        System.out.println(potentialList.get(0));
        System.out.println(potentialList.get(1));
        assertEquals(2, potentialList.size());

        CardGroup c = potentialList.get(0);
        c.recognize();
        assertEquals("♦7", c.getCriticalCard().toString());

        c = potentialList.get(1);
        c.recognize();
        assertEquals("♦8", c.getCriticalCard().toString());
    }

    @Test
    public void recognizeThreeTwoTest()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♠5", "♦5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.FullHouse.potentialCardGroup(annotatedCards);

        assertEquals(1, potentialList.size());
    }

    @Test
    public void recognizeThreeTwoTest2()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♠5", "♦5", "♥5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.FullHouse.potentialCardGroup(annotatedCards);

        assertEquals(6, potentialList.size());
    }

    @Test
    public void recognizeFourOneTest()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♠5", "♦5", "♥5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.FourOfAKind.potentialCardGroup(annotatedCards);

        assertEquals(0, potentialList.size());
    }

    @Test
    public void recognizeFourOneTest2()
    {
        List<Card> cardList = createCardList("♦4", "♠4", "♥4", "♣4", "♦5", "♥5");
        AnnotatedCards annotatedCards = new AnnotatedCards(cardList);
        List<CardGroup> potentialList = EPatternType.FourOfAKind.potentialCardGroup(annotatedCards);

        assertEquals(2, potentialList.size());
    }
}
package cn.saltedfish.saltedcdd.game.card;

import android.util.SparseArray;

public class CardFactory {
    protected static CardFactory sInstance = null;

    protected int mCardCounter = 0;

    protected SparseArray<Card> mCardCache = new SparseArray<>(52);

    public static CardFactory getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new CardFactory();
        }
        return sInstance;
    }

    protected CardFactory()
    {

    }

    public Card createCard(CardNumber pNumber, CardSuit pSuit)
    {
        Card card = new Card(pNumber, pSuit);
        card.setId(++mCardCounter);

        mCardCache.setValueAt(card.getId(), card);

        return card;
    }

    public Card[] createDeck()
    {
        CardNumber numbers[] = CardNumber.values();
        CardSuit suits[] = CardSuit.values();
        Card[] deck = new Card[numbers.length * suits.length];

        int index = 0;

        for (CardNumber number : numbers)
        {
            for (CardSuit suit : suits)
            {
                deck[index++] = createCard(number, suit);
            }
        }

        return deck;
    }

    public void recycle(Card card)
    {
        int id = card.getId();
        if (mCardCache.get(id) == card)
        {
            mCardCache.removeAt(id);
        }
    }

    public void reset()
    {
        mCardCache.clear();
        mCardCounter = 0;
    }
}

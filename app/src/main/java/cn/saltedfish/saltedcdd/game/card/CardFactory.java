package cn.saltedfish.saltedcdd.game.card;

import java.util.ArrayList;

public class CardFactory {
    protected ArrayList<Card> mCardCache = new ArrayList<>(52);

    public Card createCard(ECardNumber pNumber, ECardSuit pSuit)
    {
        Card card = new Card(pNumber, pSuit);
        card.setId(mCardCache.size());

        mCardCache.add(card);

        return card;
    }

    public Card[] createDeck()
    {
        ECardNumber numbers[] = ECardNumber.values();
        ECardSuit suits[] = ECardSuit.values();
        Card[] deck = new Card[numbers.length * suits.length];

        int index = 0;

        for (ECardNumber number : numbers)
        {
            for (ECardSuit suit : suits)
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
            mCardCache.remove(id);
        }
    }

    public void reset()
    {
        mCardCache.clear();
    }
}

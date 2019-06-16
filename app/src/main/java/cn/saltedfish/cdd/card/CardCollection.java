package cn.saltedfish.cdd.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CardCollection {
    protected HashMap<CardNumberEnum, NumberedSuitCollection> mCards = new HashMap<>(16);

    public CardCollection()
    {
    }

    public CardCollection(Collection<CardEnum> cards)
    {
        addAll(cards);
    }

    public NumberedSuitCollection getCollectionForNumber(CardNumberEnum number)
    {
        return mCards.get(number);
    }

    protected NumberedSuitCollection requireCollectionForNumber(CardNumberEnum number)
    {
        NumberedSuitCollection collectionForNumber = mCards.get(number);
        if (collectionForNumber == null)
        {
            collectionForNumber = new NumberedSuitCollection(number);
            mCards.put(number, collectionForNumber);
        }
        return collectionForNumber;
    }

    public void add(CardEnum card)
    {
        NumberedSuitCollection collection = requireCollectionForNumber(card.getNumber());
        collection.add(card.getSuit());
    }

    public void addAll(CardCollection collectionToAdd)
    {
        for (NumberedSuitCollection suitsOfNumber : collectionToAdd.mCards.values())
        {
            if (suitsOfNumber.getTotalCount() > 0)
            {
                NumberedSuitCollection collection = requireCollectionForNumber(suitsOfNumber.getCardNumber());
                collection.addAll(suitsOfNumber);
            }
        }
    }

    public void addAll(Collection<CardEnum> cards)
    {
        for (CardEnum card : cards)
        {
            add(card);
        }
    }

    public boolean contains(CardEnum card)
    {
        NumberedSuitCollection suitsOfNumber = getCollectionForNumber(card.getNumber());
        return suitsOfNumber != null && suitsOfNumber.contains(card.getSuit());
    }

    public boolean contains(CardNumberEnum number, int moreThanOrEquals)
    {
        NumberedSuitCollection collectionForNumber = mCards.get(number);
        if (collectionForNumber != null)
        {
            return collectionForNumber.getTotalCount() >= moreThanOrEquals;
        }
        else
        {
            return false;
        }
    }

    public boolean contains(CardNumberEnum number)
    {
        return contains(number, 1);
    }

    public boolean remove(CardEnum card)
    {
        NumberedSuitCollection collection = getCollectionForNumber(card.getNumber());
        if (collection != null)
        {
            return collection.remove(card.getSuit());
        }
        else
        {
            return false;
        }
    }

    public void clear()
    {
        mCards.clear();
    }

    public int getTotalCount()
    {
        int totalCount = 0;
        for (NumberedSuitCollection suitsForNumber : mCards.values())
        {
            totalCount += suitsForNumber.getTotalCount();
        }
        return totalCount;
    }

    public int getCountForNumber(CardNumberEnum number)
    {
        NumberedSuitCollection collection = getCollectionForNumber(number);
        if (collection != null)
        {
            return collection.getTotalCount();
        }
        else
        {
            return 0;
        }
    }

    public Collection<CardEnum> cards()
    {
        ArrayList<CardEnum> result = new ArrayList<>(8);
        for (NumberedSuitCollection suitsForNumber : mCards.values())
        {
            CardNumberEnum number = suitsForNumber.getCardNumber();
            for (CardSuitEnum suit : suitsForNumber.suits())
            {
                result.add(CardEnum.getCard(number, suit));
            }
        }
        return result;
    }

    public Collection<NumberedSuitCollection> numberedCollections()
    {
        return mCards.values();
    }
}

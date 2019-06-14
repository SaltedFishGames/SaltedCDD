package cn.saltedfish.saltedcdd.model.game;

import java.util.List;

import cn.saltedfish.saltedcdd.CardEnum;

public class CDDPlayer {
    protected int mPlayerId;

    protected String mNickname;
    protected CardCollection mCardsInHand = new CardCollection();

    public CDDPlayer(int playerId, String nickname)
    {
        mPlayerId = playerId;
        mNickname = nickname;
    }

    public int getPlayerId()
    {
        return mPlayerId;
    }

    public String getNickname()
    {
        return mNickname;
    }

    public void addCard(CardEnum card)
    {
        mCardsInHand.add(card);
    }

    public void setCardsInHand(List<CardEnum> cards)
    {
        mCardsInHand.clear();
        mCardsInHand.addAll(cards);
    }

    public boolean hasCard(CardEnum query)
    {
        return mCardsInHand.contains(query);
    }

    public boolean removeCards(CardCollection group)
    {
        return false;
    }


}

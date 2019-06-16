package cn.saltedfish.cdd.game;

import cn.saltedfish.cdd.PlayerInfo;
import cn.saltedfish.cdd.card.CardCollection;

public class GamePlayer {
    protected PlayerInfo mPlayerInfo;

    protected int mCardsInHandNumber;

    protected CardCollection mCardsInHand;

    public GamePlayer(PlayerInfo player)
    {
        mPlayerInfo = player;
    }
}

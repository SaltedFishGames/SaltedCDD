package cn.saltedfish.saltedcdd.game;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public interface IPlayerActionReceiver {
    void showCard(Player pPlayer, List<Card> pCards);

    void pass(Player pPlayer);
}

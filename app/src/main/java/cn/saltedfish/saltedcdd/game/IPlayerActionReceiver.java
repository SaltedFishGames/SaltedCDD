package cn.saltedfish.saltedcdd.game;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public interface IPlayerActionReceiver {
    void showCard(List<Card> pCards);

    void pass();
}

package cn.saltedfish.saltedcdd.game;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;

public interface IGameEventListener {
    void onGameEnded();
    void onNewRound();
    void onPlayerAction(Player pPlayer, EActionType pAction, Collection<Card> pCards);
    void onPlayerTurn(Player pPlayer);
}

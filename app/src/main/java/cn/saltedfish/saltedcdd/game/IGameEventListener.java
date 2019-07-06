package cn.saltedfish.saltedcdd.game;

import cn.saltedfish.saltedcdd.game.pattern.CardGroup;

public interface IGameEventListener {
    void onGameEnded();
    void onNewRound();
    void onPlayerAction(Player pPlayer, EActionType pAction, CardGroup pCards);
    void onPlayerTurn(Player pPlayer);
}

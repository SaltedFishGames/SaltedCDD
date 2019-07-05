package cn.saltedfish.saltedcdd.game;

public interface IGameEventListener {
    void onGameEnd();
    void onNewTurn();
    void onPlayerAction();
    void onPlayerTurn(Player pPlayer);
}

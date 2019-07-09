package cn.saltedfish.saltedcdd.game;

public interface IGameEventListener {
    void onGamePrepared();

    void onNewRound(GameRound pNewRound);
    void onPlayerTurn(Player pPlayer, TurnHint pHint);
    void onPlayerAction(PlayerAction action);

    void onGameEnded();
}

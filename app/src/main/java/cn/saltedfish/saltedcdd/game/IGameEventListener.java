package cn.saltedfish.saltedcdd.game;

public interface IGameEventListener {
    void onGamePrepared();

    void onNewRound(GameRound pNewRound);
    void onPlayerTurn(Player pPlayer, GameRound pCurRound);
    void onPlayerAction(PlayerAction action);

    void onGameEnded();
}

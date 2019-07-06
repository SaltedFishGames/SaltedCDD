package cn.saltedfish.saltedcdd.game;

public interface IGameOperationBridge {
    void dealCards();
    void enterState(Class<? extends GameState> pNewStateClass);
    void setCurrentTurnedPlayer(Player pPlayer);

    void onEnterNewRound();
    void onGameEnded();

    GameRound getCurrentRound();

    Player getCurrentTurnedPlayer();
    Player getPlayer(int id);
    int getPlayerCount();

    boolean curStateIs(Class<? extends GameState> pStateClass);
}

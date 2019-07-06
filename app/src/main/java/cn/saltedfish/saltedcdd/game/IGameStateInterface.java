package cn.saltedfish.saltedcdd.game;

public interface IGameStateInterface {
    void dealCards();
    void enterState(Class<? extends GameState> pNewStateClass);
    void enterNewRound();
    void setCurrentTurnedPlayer(Player pPlayer);

    GameRound getCurrentRound();
    Player getCurrentTurnedPlayer();
    boolean curStateIs(Class<? extends GameState> pStateClass);
    Player getPlayer(int id);
}

package cn.saltedfish.saltedcdd.model.game;

public abstract class CDDGameBase {
    public abstract void distributeCards(CDDPlayer[] players);

    public abstract GameResult checkGameEnd(CDDPlayer[] players);

    public abstract GameStateBase getInitialState();
}

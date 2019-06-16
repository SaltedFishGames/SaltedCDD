package cn.saltedfish.cdd.multiplayer.server;

public abstract class GameServerBase {



    public abstract void broadcastGameStateChangeMsg();

    public abstract void broadcastNewTurnMsg();

    public abstract void broadcastTurnBeginMsg();
    public abstract void broadcastTurnEndMsg();
}

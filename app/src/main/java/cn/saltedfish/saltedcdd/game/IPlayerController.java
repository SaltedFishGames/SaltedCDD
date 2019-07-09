package cn.saltedfish.saltedcdd.game;

public interface IPlayerController extends IGameEventListener {
    void setActionReceiver(IPlayerActionReceiver pActionReceiver);
}

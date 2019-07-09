package cn.saltedfish.saltedcdd.game;

import cn.saltedfish.saltedcdd.stage.gameplay.PlayerModel;

public interface IGamePlayer extends PlayerModel.IEventListener {

    void setActionReceiver(IPlayerActionReceiver pGameModel);

}

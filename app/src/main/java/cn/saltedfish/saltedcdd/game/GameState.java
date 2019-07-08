package cn.saltedfish.saltedcdd.game;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;

public abstract class GameState {

    public void onPrepare(IGameOperationBridge pGame)
    {

    }

    public void onStartGame(IGameOperationBridge pGame)
    {

    }

    public void onEnterState(IGameOperationBridge pGame)
    {

    }

    public void onLeaveState(IGameOperationBridge pGame)
    {

    }

    public boolean isActionAllowed(IGameOperationBridge pGame, PlayerAction pAction)
    {
        return false;
    }

    public void onPlayerAction(IGameOperationBridge pGame, PlayerAction pAction)
    {
        pAction.reject();
    }
}

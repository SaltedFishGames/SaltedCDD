package cn.saltedfish.saltedcdd.game.routine;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;

public class EndedState extends GameState {
    @Override
    public void onEnterState(IGameOperationBridge pGame)
    {
        pGame.onGameEnded();
    }
}

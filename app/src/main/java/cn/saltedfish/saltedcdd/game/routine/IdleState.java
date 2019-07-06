package cn.saltedfish.saltedcdd.game.routine;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;

public class IdleState extends GameState {
    @Override
    public void onPrepare(IGameOperationBridge pGame)
    {
        pGame.dealCards();
        pGame.enterState(PreparedState.class);
    }
}

package cn.saltedfish.saltedcdd.game.routine;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameStateInterface;

public class IdleState extends GameState {
    public void onPrepare(IGameStateInterface pGame)
    {
        pGame.dealCards();
        pGame.enterState(PreparedState.class);
    }
}

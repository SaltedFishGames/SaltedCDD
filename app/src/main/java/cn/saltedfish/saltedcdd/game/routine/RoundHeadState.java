package cn.saltedfish.saltedcdd.game.routine;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.GameState;

public class RoundHeadState extends GameState {
    @Override
    public void onEnterState(CDDGame pGame)
    {
        super.onEnterState(pGame);

        pGame.enterNewRound();
    }
}

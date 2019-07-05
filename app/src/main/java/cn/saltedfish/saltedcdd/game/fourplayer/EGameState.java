package cn.saltedfish.saltedcdd.game.fourplayer;

import cn.saltedfish.saltedcdd.game.GameState;

public enum EGameState {
    Idle(IdleState.class),
    Prepared(PreparedState.class),
    RoundHead(RoundHeadState.class),
    InRound(InRoundState.class),
    Ended(EndedState.class);

    Class<? extends GameState> mClazz;

    EGameState(Class<? extends GameState> pClazz)
    {
        this.mClazz = pClazz;
    }

    public Class<? extends GameState> getClazz()
    {
        return this.mClazz;
    }
}
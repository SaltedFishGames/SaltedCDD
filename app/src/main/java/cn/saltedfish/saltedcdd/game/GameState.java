package cn.saltedfish.saltedcdd.game;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.card.Card;

public abstract class GameState {

    public void onPrepare(IGameStateInterface pGame)
    {

    }

    public void onStartGame(IGameStateInterface pGame)
    {

    }

    public void onGameEnded(IGameStateInterface pGame)
    {

    }

    public void onEnterState(IGameStateInterface pGame)
    {

    }

    public void onLeaveState(IGameStateInterface pGame)
    {

    }

    public boolean isActionAllowed(IGameStateInterface pGame, Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        return false;
    }

    public boolean onPlayerAction(IGameStateInterface pGame, Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        return false;
    }
}

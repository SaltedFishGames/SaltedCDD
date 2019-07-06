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

    public boolean isShowCardAllowed(IGameOperationBridge pGame, Player pPlayer, List<Card> pCards)
    {
        return false;
    }

    public boolean isPassAllowed(IGameOperationBridge pGame, Player pPlayer)
    {
        return false;
    }

    public boolean onPlayerShowCard(IGameOperationBridge pGame, Player pPlayer, CardGroup pCards)
    {
        return false;
    }

    public boolean onPlayerPass(IGameOperationBridge pGame, Player pPlayer)
    {
        return false;
    }
}

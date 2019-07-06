package cn.saltedfish.saltedcdd.game.routine;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameStateInterface;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;

public class RoundHeadState extends GameState {
    @Override
    public void onEnterState(IGameStateInterface pGame)
    {
        super.onEnterState(pGame);

        pGame.enterNewRound();
    }

    @Override
    public boolean isActionAllowed(IGameStateInterface pGame, Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        return false;
    }

    @Override
    public boolean onPlayerAction(IGameStateInterface pGame, Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        // TODO
        return false;
    }
}

package cn.saltedfish.saltedcdd.game.routine;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;
import cn.saltedfish.saltedcdd.game.PlayerAction;

public class RoundHeadState extends GameState {
    @Override
    public void onEnterState(IGameOperationBridge pGame)
    {
        pGame.onEnterNewRound();
    }


    @Override
    public boolean isActionAllowed(IGameOperationBridge pGame, PlayerAction pAction)
    {
        switch (pAction.getType())
        {
            case ShowCard:
                return true;
            case Pass:
                return false;
            default:
                return false;
        }
    }

    @Override
    public void onPlayerAction(IGameOperationBridge pGame, PlayerAction pAction)
    {
        if (!isActionAllowed(pGame, pAction))
        {
            pAction.reject();
            return;
        }

        switch (pAction.getType())
        {
            case ShowCard:
                pAction.getPlayer().takeAwayCards(pAction.getCardGroup().cards());

                if (pAction.getPlayer().getCardCount() == 0)
                {
                    pAction.setEnterNewState(EndedState.class);
                }
                else
                {
                    pAction.setEnterNewState(InRoundState.class);
                    pAction.setTurnToPlayer(pGame.getNextPlayer(pAction.getPlayer()));
                }
                pAction.accept();
                break;
            case Pass:
                pAction.reject();
                break;
        }
    }
}

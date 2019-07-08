package cn.saltedfish.saltedcdd.game.routine;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;

public class InRoundState extends GameState {
    public boolean isActionAllowed(IGameOperationBridge pGame, PlayerAction pAction)
    {
        CardGroup lastGroup = pGame.getCurrentRound().getLastShowCardAction().getCardGroup();

        switch (pAction.getType())
        {
            case ShowCard:
                return lastGroup.isComparableTo(pAction.getCardGroup())
                        && pAction.getCardGroup().compareTo(lastGroup) > 0;
            case Pass:
                return true;
            default:
                return false;
        }
    }

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
                    pAction.setTurnToPlayer(pGame.getNextPlayer(pAction.getPlayer()));
                }

                pAction.accept();
                break;
            case Pass:
                if (pGame.getCurrentRound().getContinuousPassActionCount() + 1 ==
                        pGame.getPlayerCount() - 1)
                {
                    pAction.setEnterNewState(RoundHeadState.class);
                }

                pAction.setTurnToPlayer(pGame.getNextPlayer(pAction.getPlayer()));
                pAction.accept();
                break;
        }
    }

}

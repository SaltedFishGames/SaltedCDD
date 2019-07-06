package cn.saltedfish.saltedcdd.game.routine;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameStateInterface;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;

public class InRoundState extends GameState {
    @Override
    public boolean isActionAllowed(IGameStateInterface pGame, Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        if (pGame.getCurrentTurnedPlayer() != pPlayer)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean onPlayerAction(IGameStateInterface pGame, Player pPlayer, EActionType pAction, Collection<Card> pCards)
    {
        if (pGame.getCurrentTurnedPlayer() != pPlayer)
        {
            return false;
        }
        switch (pAction)
        {
            case Pass:
                if (pGame.getCurrentRound().getContinuousPassActionCount() - 1
                        == FourPlayerGame.PlayerCount - 1)
                {
                    pGame.enterState(RoundHeadState.class);
                }
                break;
            case ShowCard:

                break;
        }
        return true;
    }
}

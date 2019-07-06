package cn.saltedfish.saltedcdd.game.routine;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class InRoundState extends GameState {
    @Override
    public boolean isShowCardAllowed(IGameOperationBridge pGame, Player pPlayer, Collection<Card> pCards)
    {
        if (pPlayer == pGame.getCurrentTurnedPlayer())
        {
            return PatternRecognizer.getPatternType(pCards) != EPatternType.Unknown;
        }
        return false;
    }

    @Override
    public boolean isPassAllowed(IGameOperationBridge pGame, Player pPlayer)
    {
        return pPlayer == pGame.getCurrentTurnedPlayer();
    }

    @Override
    public boolean onPlayerShowCard(IGameOperationBridge pGame, Player pPlayer, CardGroup pCards)
    {
        if (pPlayer != pGame.getCurrentTurnedPlayer())
            return false;

        CardGroup lastGroup = pGame.getCurrentRound().getLastShowCardAction().mCards;

        return lastGroup.isSameType(pCards) && pCards.compareTo(lastGroup) > 0;
    }

    @Override
    public boolean onPlayerPass(IGameOperationBridge pGame, Player pPlayer)
    {
        if (pPlayer != pGame.getCurrentTurnedPlayer())
            return false;

        if (pGame.getCurrentRound().getContinuousPassActionCount() + 1 ==
            pGame.getPlayerCount() - 1)
        {
            pGame.enterState(RoundHeadState.class);
        }
        return true;
    }
}

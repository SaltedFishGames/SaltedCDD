package cn.saltedfish.saltedcdd.game.routine;

import java.util.List;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class InRoundState extends GameState {
    @Override
    public boolean isShowCardAllowed(IGameOperationBridge pGame, Player pPlayer, List<Card> pCards)
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
    public boolean onPlayerShowCard(IGameOperationBridge pGame, Player pPlayer, CardGroup pCardGroup)
    {
        if (pPlayer != pGame.getCurrentTurnedPlayer())
            return false;

        if (!pPlayer.hasCards(pCardGroup.mCards))
            return false;

        CardGroup lastGroup = pGame.getCurrentRound().getLastShowCardAction().mCards;

        if (lastGroup.isSameType(pCardGroup) && pCardGroup.compareTo(lastGroup) > 0)
        {
            pPlayer.takeAwayCards(pCardGroup.mCards);

            if (pPlayer.getCardCount() == 0)
            {
                pGame.enterState(EndedState.class);
            }
            return true;
        }
        else
        {
            return false;
        }
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

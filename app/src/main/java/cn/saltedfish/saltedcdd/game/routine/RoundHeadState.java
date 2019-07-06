package cn.saltedfish.saltedcdd.game.routine;

import java.util.Collection;
import java.util.List;

import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class RoundHeadState extends GameState {
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
        // player cannot pass at the beginning of a round
        return false;
    }

    @Override
    public boolean onPlayerShowCard(IGameOperationBridge pGame, Player pPlayer, CardGroup pCardGroup)
    {
        if (pPlayer != pGame.getCurrentTurnedPlayer())
            return false;

        if (!pPlayer.hasCards(pCardGroup.mCards))
            return false;

        pPlayer.takeAwayCards(pCardGroup.mCards);

        if (pPlayer.getCardCount() == 0)
        {
            pGame.enterState(EndedState.class);
        }
        return true;
    }

    @Override
    public boolean onPlayerPass(IGameOperationBridge pGame, Player pPlayer)
    {
        // player cannot pass at the beginning of a round
        return false;
    }

    @Override
    public void onEnterState(IGameOperationBridge pGame)
    {
        pGame.onEnterNewRound();
    }
}

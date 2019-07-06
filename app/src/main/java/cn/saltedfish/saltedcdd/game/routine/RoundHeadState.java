package cn.saltedfish.saltedcdd.game.routine;

import java.util.Collection;
import java.util.regex.Pattern;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameOperationBridge;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class RoundHeadState extends GameState {
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
        // player cannot pass at the beginning of a round
        return false;
    }

    @Override
    public boolean onPlayerShowCard(IGameOperationBridge pGame, Player pPlayer, CardGroup pCards)
    {
        if (pPlayer != pGame.getCurrentTurnedPlayer())
            return false;

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

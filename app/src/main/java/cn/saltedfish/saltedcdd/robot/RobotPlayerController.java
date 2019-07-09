package cn.saltedfish.saltedcdd.robot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IPlayerActionReceiver;
import cn.saltedfish.saltedcdd.game.IPlayerController;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.TurnHint;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.AnnotatedCards;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class RobotPlayerController implements IPlayerController {
    protected IPlayerActionReceiver mActionReceiver;

    protected Player mPlayer;

    public RobotPlayerController(Player pPlayer)
    {
        mPlayer = pPlayer;
    }

    @Override
    public void setActionReceiver(IPlayerActionReceiver pActionReceiver)
    {
        mActionReceiver = pActionReceiver;
    }

    @Override
    public void onGamePrepared()
    {

    }

    @Override
    public void onNewRound(GameRound pNewRound)
    {

    }

    @Override
    public void onPlayerTurn(Player pPlayer, TurnHint pHint)
    {
        if (pPlayer != mPlayer)
            return;
        if (pHint.getPossibleMinimumCardGroup() != null)
        {
            CardGroup suggestion = suggest(pPlayer, pHint);
            if (suggestion != null)
            {
                mActionReceiver.showCard(suggestion.cards());
                return;
            }
        }
        mActionReceiver.pass();
    }

    protected CardGroup suggest(Player pPlayer, TurnHint pHint)
    {
        Random r = new Random();

        AnnotatedCards annotatedCards = new AnnotatedCards(pPlayer.cards());
        PlayerAction lastAction = pHint.getRound().getLastShowCardAction();
        if (lastAction != null)
        {
            if (r.nextDouble() < 0.1)
                return null;

            EPatternType[] patterns = PatternRecognizer.getAvailablePatterns(lastAction.getCardGroup().count());
            for (EPatternType pattern : patterns)
            {
                if (pattern.compareWeight(lastAction.getCardGroup().getType()) < 0) continue;

                if (r.nextDouble() < 0.25) continue;
                List<CardGroup> potentialCards = pattern.potentialCardGroup(annotatedCards);
                if (potentialCards != null && potentialCards.size() > 0)
                {
                    for (CardGroup c : potentialCards) {
                        if (pHint.getGame().isShowCardAllowed(pPlayer, c.cards()))
                        {
                            return c;
                        }
                    }

                }
            }

            return null;
        }
        else
        {
            EPatternType[] patterns = EPatternType.values();
            for (int i = patterns.length - 1; i >= 0; i--)
            {
                EPatternType pattern = patterns[i];

                if (pattern.getWeight() == 0) continue;

                List<CardGroup> potentialCards = pattern.potentialCardGroup(annotatedCards);
                if (potentialCards != null && potentialCards.size() > 0)
                {
                    if (r.nextDouble() < 0.1) continue;
                    for (CardGroup c : potentialCards)
                    {
                        if (pHint.getGame().isShowCardAllowed(pPlayer, c.cards()))
                        {
                            return c;
                        }
                    }
                }
            }

            List<CardGroup> potentialCards = EPatternType.Single.potentialCardGroup(annotatedCards);
            return potentialCards.get(r.nextInt(potentialCards.size()));
        }
    }

    @Override
    public void onPlayerAction(PlayerAction action)
    {

    }

    @Override
    public void onGameEnded()
    {

    }
}

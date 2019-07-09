package cn.saltedfish.saltedcdd.game;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.AnnotatedCards;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class TurnHint {
    protected GameRound mRound;

    protected Player mPlayer;

    protected CardGroup mPossibleMinimumCardGroup;

    protected boolean mPassAllowed;

    protected CDDGame mGame;

    public TurnHint(GameRound pRound, Player pPlayer, CDDGame pGame)
    {
        mRound = pRound;
        mPlayer = pPlayer;
        mPassAllowed = pGame.isPassAllowed(pPlayer);
        mGame = pGame;
        mPossibleMinimumCardGroup = suggest(pPlayer.cards(), pRound.getLastShowCardAction());
    }

    protected CardGroup suggest(List<Card> pAvailableCards, PlayerAction pLastShowCard)
    {
        AnnotatedCards annotatedCards = new AnnotatedCards(pAvailableCards);
        if (pLastShowCard != null)
        {
            EPatternType[] patterns = PatternRecognizer.getAvailablePatterns(pLastShowCard.getCardGroup().count());
            for (EPatternType pattern : patterns)
            {
                if (pattern.compareWeight(pLastShowCard.mCardGroup.getType()) < 0) continue;

                List<CardGroup> potentialCards = pattern.potentialCardGroup(annotatedCards);
                if (potentialCards != null && potentialCards.size() > 0)
                {
                    for (CardGroup c : potentialCards) {
                        if (mGame.isShowCardAllowed(mPlayer, c.cards()))
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
            List<CardGroup> potentialCards = EPatternType.Single.potentialCardGroup(annotatedCards);
            return potentialCards.get(0);
        }
    }

    public GameRound getRound()
    {
        return mRound;
    }

    public CardGroup getPossibleMinimumCardGroup()
    {
        return mPossibleMinimumCardGroup;
    }

    public Player getPlayer()
    {
        return mPlayer;
    }

    public boolean isPassAllowed()
    {
        return mPassAllowed;
    }

    public CDDGame getGame()
    {
        return mGame;
    }
}

package cn.saltedfish.saltedcdd.game;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class PlayerAction {
    protected EActionType mType;

    protected CardGroup mCardGroup;

    protected Player mPlayer;

    protected Class<? extends GameState> mEnterNewState = null;

    protected Player mTurnToPlayer = null;

    protected boolean mIsAccepted = false;

    public PlayerAction(Player pPlayer, EActionType pType)
    {
        mPlayer = pPlayer;
        mType = pType;
        mCardGroup = null;
    }

    public PlayerAction(Player pPlayer, EActionType pType, List<Card> pCards)
    {
        mPlayer = pPlayer;
        mType = pType;
        if (pCards != null)
            mCardGroup = PatternRecognizer.recognize(pCards);
    }

    public PlayerAction(Player pPlayer, EActionType pType, CardGroup pCardGroup)
    {
        mPlayer = pPlayer;
        mType = pType;
        if (pCardGroup != null)
        {
            mCardGroup = pCardGroup;
            mCardGroup.recognize();
        }
    }

    public EActionType getType()
    {
        return mType;
    }

    public Player getPlayer()
    {
        return mPlayer;
    }

    public CardGroup getCardGroup()
    {
        return mCardGroup;
    }

    public Class<? extends GameState> getEnterNewState()
    {
        return mEnterNewState;
    }

    public Player getTurnToPlayer()
    {
        return mTurnToPlayer;
    }

    public boolean isAccepted()
    {
        return mIsAccepted;
    }

    public void setEnterNewState(Class<? extends GameState> pEnterNewState)
    {
        mEnterNewState = pEnterNewState;
    }

    public void setTurnToPlayer(Player pTurnToPlayer)
    {
        mTurnToPlayer = pTurnToPlayer;
    }

    public void accept()
    {
        mIsAccepted = true;
    }

    public void reject()
    {
        mIsAccepted = false;
    }
}

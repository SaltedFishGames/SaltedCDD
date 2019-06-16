package cn.saltedfish.cdd.game;

import java.util.UUID;

import cn.saltedfish.cdd.PlayerInfo;
import cn.saltedfish.cdd.card.CardCollection;
import cn.saltedfish.cdd.card.CardEnum;
import cn.saltedfish.cdd.multiplayer.server.GameServerMsgListener;

public abstract class GameBase implements GameServerMsgListener {
    protected GamePlayer[] mPlayers;

    protected GameStateBase mCurState;
    protected GameRuleBase mCurRule;

    protected GameProgressListener mProgressListener;

    public GameBase(PlayerInfo[] players)
    {
        mPlayers = new GamePlayer[players.length];
        for (int i = 0; i < players.length; i++)
        {
            mPlayers[i] = new GamePlayer(players[i]);
        }
    }

    public boolean canFollow(CardCollection collection)
    {
        // todo
        return false;
    }

    protected void emitTurnBeginEvent(GamePlayer who)
    {
        if (mProgressListener != null)
        {
            // todo
        }
    }

    protected void emitTurnEndEvent(GamePlayer who, GameAction what)
    {
        if (mProgressListener != null)
        {
            // todo
        }
    }

    public void onGameStateChangeNotify(int stateId, int ruleId) {}

    public void onNewRoundNotify(int roundNum) {}

    public void onTurnBeginNotify(UUID playerId) {}
    public void onTurnEndNotify(UUID playerId, TurnActionTypeEnum actionType, CardEnum[] cards) {}

    public void onGameEndNotify(GameResult result) {}
}

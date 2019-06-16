package cn.saltedfish.cdd.multiplayer.server;

import java.util.UUID;

import cn.saltedfish.cdd.game.GameResult;
import cn.saltedfish.cdd.game.TurnActionTypeEnum;
import cn.saltedfish.cdd.card.CardEnum;

public interface GameServerMsgListener {
    void onGameStateChangeNotify(int stateId, int ruleId);

    void onNewRoundNotify(int roundNum);

    void onTurnBeginNotify(UUID playerId);
    void onTurnEndNotify(UUID playerId, TurnActionTypeEnum actionType, CardEnum[] cards);

    void onGameEndNotify(GameResult result);
}

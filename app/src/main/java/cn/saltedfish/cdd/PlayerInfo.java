package cn.saltedfish.cdd;

import java.util.List;
import java.util.UUID;

import cn.saltedfish.cdd.card.CardCollection;
import cn.saltedfish.cdd.card.CardEnum;

public class PlayerInfo {
    protected UUID mPlayerId;

    protected String mNickname;

    public PlayerInfo(UUID playerId, String nickname)
    {
        mPlayerId = playerId;
        mNickname = nickname;
    }

    public UUID getPlayerId()
    {
        return mPlayerId;
    }

    public String getNickname()
    {
        return mNickname;
    }

}

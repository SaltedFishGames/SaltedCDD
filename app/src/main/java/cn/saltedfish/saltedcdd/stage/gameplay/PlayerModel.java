package cn.saltedfish.saltedcdd.stage.gameplay;

import cn.saltedfish.saltedcdd.game.Player;

public class PlayerModel {
    protected String mNickname;

    protected Player mGamePlayer;

    public PlayerModel(String pNickname)
    {
        mNickname = pNickname;
        mGamePlayer = new Player();
    }

    public String getNickname()
    {
        return mNickname;
    }

    public Player getGamePlayer()
    {
        return mGamePlayer;
    }
}

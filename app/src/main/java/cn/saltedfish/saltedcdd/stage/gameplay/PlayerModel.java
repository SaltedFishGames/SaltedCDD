package cn.saltedfish.saltedcdd.stage.gameplay;

import cn.saltedfish.saltedcdd.game.IPlayerController;
import cn.saltedfish.saltedcdd.game.Player;

public class PlayerModel {
    protected Player mPlayer;

    protected String mNickname;

    protected IPlayerController mPlayerController;

    public PlayerModel()
    {
        mPlayer = new Player();
    }

    public IPlayerController getPlayerController()
    {
        return mPlayerController;
    }

    public void setPlayerController(IPlayerController pPlayerController)
    {
        mPlayerController = pPlayerController;
    }

    public String getNickname()
    {
        return mNickname;
    }

    public void setNickname(String pNickname)
    {
        mNickname = pNickname;
    }

    public Player getPlayer()
    {
        return mPlayer;
    }
}
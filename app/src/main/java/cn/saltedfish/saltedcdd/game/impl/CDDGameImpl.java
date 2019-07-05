package cn.saltedfish.saltedcdd.game.impl;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.GameBoard;
import cn.saltedfish.saltedcdd.game.GameState;

public class CDDGameImpl extends CDDGame {
    public CDDGameImpl()
    {
        mBoard = new GameBoard();
    }

    @Override
    public void handleStartGame()
    {

    }

    @Override
    public void handlePlayerAction()
    {

    }

    @Override
    public void enterState(GameState pNewState)
    {
        super.enterState(pNewState);
    }
}

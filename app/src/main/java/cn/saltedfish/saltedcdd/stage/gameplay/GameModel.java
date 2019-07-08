package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.ArrayList;
import java.util.Collection;

import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IGameEventListener;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.routine.FourPlayerGame;

public class GameModel implements IGameEventListener {
    protected CDDGame mGame;

    public GameModel()
    {

    }

    public void newGame()
    {
        mGame = new FourPlayerGame();
    }

    public void setGamePlayers(Collection<PlayerModel> pPlayers)
    {
        Player[] gamePlayers = new Player[pPlayers.size()];
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
    public void onPlayerTurn(Player pPlayer, GameRound pCurRound)
    {

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

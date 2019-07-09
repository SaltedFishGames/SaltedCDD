package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.List;

import cn.saltedfish.saltedcdd.game.IPlayerActionReceiver;
import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IGameEventListener;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.routine.FourPlayerGame;

public class GameModel implements IGameEventListener, IPlayerActionReceiver {
    protected CDDGame mGame;

    protected PlayerModel[] mPlayerModels;

    public GameModel()
    {

    }

    public void newGame()
    {
        mGame = new FourPlayerGame();
    }

    public void prepare(PlayerModel[] pPlayerModels)
    {
        mPlayerModels = pPlayerModels;

        Player[] gamePlayers = new Player[pPlayerModels.length];

        for (int i = 0; i < gamePlayers.length; i++)
        {
            gamePlayers[i] = pPlayerModels[i].getGamePlayer();
        }

        mGame.prepare(gamePlayers);
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

    @Override
    public void showCard(List<Card> pCards)
    {

    }

    @Override
    public void pass()
    {

    }
}
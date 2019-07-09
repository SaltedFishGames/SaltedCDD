package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.List;

import cn.saltedfish.saltedcdd.game.IPlayerActionReceiver;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;

public class GamePlayPresenter implements GamePlayContract.Presenter {
    protected GamePlayContract.View mGamePlayView;

    protected GameModel mGameModel;

    public void GamePlayPresenter(GameModel pGameModel, GamePlayView pView)
    {
        mGameModel = pGameModel;

        mGamePlayView = pView;
        mGamePlayView.setPresenter(this);
    }

    @Override
    public void start()
    {

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
    public void startGame()
    {

    }

    @Override
    public void abortGame()
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

    @Override
    public void setActionReceiver(IPlayerActionReceiver pGameModel)
    {

    }
}

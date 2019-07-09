package cn.saltedfish.saltedcdd.stage.gameplay;

import android.util.Log;
import android.view.View;

import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IPlayerController;
import cn.saltedfish.saltedcdd.game.IPlayerActionReceiver;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.ActionHint;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.stage.Navigator;

public class GamePlayPresenter implements GamePlayContract.Presenter, IPlayerController {
    protected GameModel mGameModel;

    protected GamePlayContract.View mView;

    protected Navigator mNavigatorRestartGame;

    protected Navigator mNavigatorQuitGame;

    protected IPlayerActionReceiver mActionReceiver;

    protected IPlayerController mPlayerController;

    protected boolean mAutoPlay = true;

    public GamePlayPresenter(GameModel pModel, Navigator pNavigatorRestartGame, Navigator pNavigatorQuitGame, GamePlayContract.View pView)
    {
        mGameModel = pModel;
        mView = pView;
        mNavigatorQuitGame = pNavigatorQuitGame;
        mNavigatorRestartGame = pNavigatorRestartGame;

        pView.setPresenter(this);

        mPlayerController = new PlayerControllerProxy();

        mGameModel.attachHumanPlayer(mPlayerController);
    }

    @Override
    public void start()
    {
        mGameModel.prepareGame();
    }

    @Override
    public void onRestartGameClicked()
    {
        mNavigatorRestartGame.navigate();
    }

    @Override
    public void onResumeGameClicked()
    {
        mGameModel.resumeGame();
        mView.setPauseMenuVisibility(false);
        mView.setPauseButtonVisibility(true);
    }

    @Override
    public void onPauseGameClicked()
    {
        mGameModel.pauseGame();
        mView.setPauseMenuVisibility(true);
        mView.setPauseButtonVisibility(false);
    }

    @Override
    public void onQuitGameClicked()
    {
        mNavigatorQuitGame.navigate();
    }

    @Override
    public void onShowCardClicked()
    {

    }

    @Override
    public void onPassClicked()
    {

    }

    @Override
    public void onCardSelectionModified()
    {

    }

    @Override
    public void setActionReceiver(IPlayerActionReceiver pActionReceiver)
    {
        mActionReceiver = pActionReceiver;
    }

    @Override
    public void onGamePrepared()
    {
        Log.d("CDD", "Game.prepared");
        mGameModel.startGame();
    }

    @Override
    public void onNewRound(GameRound pNewRound)
    {
        Log.d("CDD", "Game.newRound");
    }

    @Override
    public void onPlayerTurn(Player pPlayer, ActionHint pHint)
    {
        Log.d("CDD", "Game.turnTo Player#" + pPlayer.getId());
        if (pHint.getPossibleMinimumCardGroup() != null)
        {
            mActionReceiver.showCard(pHint.getPossibleMinimumCardGroup().cards());
        }
        else
        {
            mActionReceiver.pass();
        }
    }

    @Override
    public void onPlayerAction(PlayerAction action)
    {
        if (!action.isAccepted())
        {
            Log.e("CDD", "Game.actionRejected Player#" + action.getPlayer().getId());
        }

        switch (action.getType())
        {
            case Pass:
                Log.d("CDD", "Game.pass Player#" + action.getPlayer().getId());
                break;
            case ShowCard:
                Log.d("CDD", "Game.showCard Player#" + action.getPlayer().getId() + ", cards=" + action.getCardGroup().toString());
                break;
        }
        StringBuilder sb = new StringBuilder();
        for (Card c : action.getPlayer().cards())
        {
            sb.append(c.toString());
            sb.append(", ");
        }
        Log.d("CDD", "Game.now Player#" + action.getPlayer().getId() + " has cards " + sb.toString());
    }

    @Override
    public void onGameEnded()
    {
        GameResult result = new GameResult();
        // TODO: generate game result
        mView.showGameResult(result);
        Log.d("CDD", "Game.ended");
    }

    public IPlayerController getPlayerController()
    {
        return mPlayerController;
    }

    class PlayerControllerProxy implements IPlayerController
    {
        @Override
        public void setActionReceiver(IPlayerActionReceiver pActionReceiver)
        {
            mActionReceiver = pActionReceiver;
        }

        @Override
        public void onGamePrepared()
        {
            mView.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    GamePlayPresenter.this.onGamePrepared();
                }
            });
        }

        @Override
        public void onNewRound(final GameRound pNewRound)
        {
            mView.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    GamePlayPresenter.this.onNewRound(pNewRound);
                }
            });
        }

        @Override
        public void onPlayerTurn(final Player pPlayer, final ActionHint pHint)
        {
            mView.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    GamePlayPresenter.this.onPlayerTurn(pPlayer, pHint);
                }
            });

        }

        @Override
        public void onPlayerAction(final PlayerAction action)
        {
            mView.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    GamePlayPresenter.this.onPlayerAction(action);
                }
            });
        }

        @Override
        public void onGameEnded()
        {
            mView.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    GamePlayPresenter.this.onGameEnded();
                }
            });
        }
    }
}

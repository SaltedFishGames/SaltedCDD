package cn.saltedfish.saltedcdd.stage.gameplay;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IPlayerController;
import cn.saltedfish.saltedcdd.game.IPlayerActionReceiver;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.ActionHint;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.stage.Navigator;

public class GamePlayPresenter implements GamePlayContract.Presenter, IPlayerController {
    protected GameModel mGameModel;

    protected GamePlayContract.View mView;

    protected Navigator mNavigatorRestartGame;

    protected Navigator mNavigatorQuitGame;

    protected IPlayerActionReceiver mActionReceiver;

    protected IPlayerController mPlayerController;

    protected boolean mAutoPlay = false;

    protected ActionHint mCurrentHint;

    protected PlayerModel mThisPlayerModel;

    public GamePlayPresenter(GameModel pModel, Navigator pNavigatorRestartGame, Navigator pNavigatorQuitGame, GamePlayContract.View pView)
    {
        mGameModel = pModel;
        mView = pView;
        mNavigatorQuitGame = pNavigatorQuitGame;
        mNavigatorRestartGame = pNavigatorRestartGame;

        pView.setPresenter(this);

        mPlayerController = new PlayerControllerProxy();

        mGameModel.attachHumanPlayer(mPlayerController);

        mThisPlayerModel = mGameModel.getHumanPlayer();
    }

    @Override
    public void start()
    {
        mView.setPlayerInfo(0, mGameModel.getPlayerModel(0).getNickname(), PlayerInfoGameView.AvatarType.Player);
        mView.setPlayerInfo(1, mGameModel.getPlayerModel(1).getNickname(), PlayerInfoGameView.AvatarType.RobotRight);
        mView.setPlayerInfo(2, mGameModel.getPlayerModel(2).getNickname(), PlayerInfoGameView.AvatarType.RobotRight);
        mView.setPlayerInfo(3, mGameModel.getPlayerModel(3).getNickname(), PlayerInfoGameView.AvatarType.RobotLeft);

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
        mActionReceiver.showCard(mView.getCardSelection());
    }

    @Override
    public void onPassClicked()
    {
        mActionReceiver.pass();
    }

    @Override
    public void onSwitchAutoplay()
    {
        mAutoPlay = !mAutoPlay;
        mView.setPlayerAvatar(0, mAutoPlay ? PlayerInfoGameView.AvatarType.PlayerAutoplay : PlayerInfoGameView.AvatarType.Player);
        if (mAutoPlay && mCurrentHint != null)
        {
            onPlayerTurn(mThisPlayerModel.getPlayer(), mCurrentHint);
        }
    }

    @Override
    public void onHintClicked()
    {
        if (mCurrentHint == null) return;
        CardGroup possibleGroup = mCurrentHint.getPossibleMinimumCardGroup();
        if (possibleGroup != null)
        {
            mView.clearCardSelection();
            mView.setCardSelection(possibleGroup.cards());
        }
        mView.repaint();
    }

    @Override
    public void setActionReceiver(IPlayerActionReceiver pActionReceiver)
    {
        mActionReceiver = pActionReceiver;
    }

    @Override
    public void onGamePrepared()
    {
        for (int i = 0; i < 4; i++)
        {
            mView.setPlayerCards(i, mGameModel.getPlayerModel(i).getPlayer().cards());
        }
        mView.repaint();
        mGameModel.startGame();
    }

    @Override
    public void onNewRound(GameRound pNewRound)
    {
    }

    @Override
    public void onPlayerTurn(Player pPlayer, ActionHint pHint)
    {
        if (pPlayer == mThisPlayerModel.getPlayer())
        {
            mCurrentHint = pHint;
            mView.showTurnToMyself(pHint.getPossibleMinimumCardGroup() != null, pHint.isPassAllowed());
            if (mAutoPlay)
            {
                if (pHint.getPossibleMinimumCardGroup() != null)
                {
                    mActionReceiver.showCard(pHint.getPossibleMinimumCardGroup().cards());
                }
                else
                {
                    mActionReceiver.pass();
                }
            }
        }
        else
        {
            mCurrentHint = null;
            mView.showTurnToOthers(pPlayer.getId());
        }
        mView.repaint();
    }

    @Override
    public void onPlayerAction(PlayerAction action)
    {
        if (!action.isAccepted() && action.getPlayer() == mThisPlayerModel.getPlayer())
        {
            if (action.getType() == EActionType.ShowCard)
            {
                if (action.getCardGroup().getType() == EPatternType.Unknown)
                {
                    mView.showToast("牌型不合法");
                }
                else
                {
                    mView.showToast("所选牌不大于上家的牌");
                }
            }
        }

        if (action.isAccepted())
        {
            int i = action.getPlayer().getId();
            switch (action.getType()) {
                case Pass:
                    mView.showPlayerPass(i);
                    break;
                case ShowCard:
                    mView.showPlayerShowCard(i, action.getCardGroup().cards());
                    break;
            }
            mView.setPlayerCards(i, mGameModel.getPlayerModel(i).getPlayer().cards());
            mView.repaint();
        }
    }

    @Override
    public void onGameEnded()
    {
        List<PlayerModel> mPlayerModels = new ArrayList<>();

        for (int i = 0; i < 4; i++)
        {
            PlayerModel playerModel = mGameModel.getPlayerModel(i);
            mPlayerModels.add(playerModel);

            List<Card> cardsLeft = playerModel.getPlayer().cards();
            if (cardsLeft.size() > 0)
            {
                mView.showPlayerShowCard(i, cardsLeft);
            }
            mView.setPlayerCards(i, null);
        }

        Collections.sort(mPlayerModels, new Comparator<PlayerModel>() {
            @Override
            public int compare(PlayerModel o1, PlayerModel o2)
            {
                return o2.getScore() - o1.getScore();
            }
        });

        mView.setResultMyselfRank(1 + mPlayerModels.indexOf(mThisPlayerModel));

        for (int i = 0; i < 4; i++)
        {
            PlayerModel playerModel = mPlayerModels.get(i);
            mView.setResultPlayerRank(i, playerModel.getNickname(), playerModel.getScore());
        }

        mView.showGameResult();
        mView.repaint();
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

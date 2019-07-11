package cn.saltedfish.saltedcdd.stage.gameplay;

import android.os.Handler;
import android.os.HandlerThread;

import java.util.List;

import cn.saltedfish.saltedcdd.data.Config;
import cn.saltedfish.saltedcdd.game.CDDGame;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.IGameEventListener;
import cn.saltedfish.saltedcdd.game.IPlayerActionReceiver;
import cn.saltedfish.saltedcdd.game.IPlayerController;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.ActionHint;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.routine.FourPlayerGame;
import cn.saltedfish.saltedcdd.robot.RobotPlayerController;

public class GameModel {
    protected CDDGame mGame;

    protected PlayerModel[] mPlayerModels = new PlayerModel[4];

    protected HandlerThread mHandlerThread;

    protected Handler mHandler;

    protected boolean mIsPaused;

    protected int mPlayerActionToken = 0;

    public GameModel()
    {
        mHandlerThread = new HandlerThread("GameBackgroundThread");
        mHandlerThread.start();

        mGame = new FourPlayerGame();
        mGame.setEventListener(new EventDispatcher());

        for (int i = 0; i < mPlayerModels.length; i++)
        {
            mPlayerModels[i] = new PlayerModel();
        }

        mHandler = new Handler(mHandlerThread.getLooper());

        for (int i = 0; i < mPlayerModels.length; i++)
        {
            mPlayerModels[i] = new PlayerModel();
        }

        for (int i = 1; i < mPlayerModels.length; i++)
        {
            attachPlayerController(i, new RobotPlayerController(mPlayerModels[i].getPlayer()));
            mPlayerModels[i].setRobot(true);
        }

        mPlayerModels[0].setNickname(Config.getNickname());
        mPlayerModels[1].setNickname("大咸鱼");
        mPlayerModels[2].setNickname("二咸鱼");
        mPlayerModels[3].setNickname("三咸鱼");
    }

    public PlayerModel getPlayerModel(int index)
    {
        return mPlayerModels[index];
    }

    public void attachPlayerController(int index, IPlayerController pPlayerController)
    {
        mPlayerModels[index].setPlayerController(pPlayerController);
        pPlayerController.setActionReceiver(new PlayerActionReceiver(mPlayerModels[index]));
    }

    public void attachHumanPlayer(IPlayerController pPlayerController)
    {
        attachPlayerController(0, pPlayerController);
    }

    public PlayerModel getHumanPlayer()
    {
        return mPlayerModels[0];
    }

    public void destroy()
    {
        if (mHandlerThread != null)
        {
            mHandlerThread.quit();
        }
    }

    public void prepareGame()
    {
        mHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Player[] players = new Player[mPlayerModels.length];
                for (int i = 0; i < players.length; i++)
                {
                    players[i] = mPlayerModels[i].getPlayer();
                }
                mGame.prepare(players);
            }
        });
    }

    public void startGame()
    {
        mHandler.post(new Runnable() {
            @Override
            public void run()
            {
                mGame.startGame();
            }
        });
    }

    public void pauseGame()
    {
        if (!mIsPaused)
        {
            mPlayerActionToken++;
            mIsPaused = true;
        }
    }

    public void resumeGame()
    {
        if (mIsPaused)
        {
            mIsPaused = false;
            mGame.setCurrentTurnedPlayer(mGame.getCurrentTurnedPlayer());
        }
    }

    class EventDispatcher implements IGameEventListener
    {
        @Override
        public void onGamePrepared()
        {
            for (PlayerModel player : mPlayerModels)
            {
                player.getPlayerController().onGamePrepared();
            }
        }

        @Override
        public void onNewRound(GameRound pNewRound)
        {
            for (PlayerModel player : mPlayerModels)
            {
                player.getPlayerController().onNewRound(pNewRound);
            }
        }

        @Override
        public void onPlayerTurn(Player pPlayer, ActionHint pHint)
        {
            for (PlayerModel player : mPlayerModels)
            {
                player.getPlayerController().onPlayerTurn(pPlayer, pHint);
            }
        }

        @Override
        public void onPlayerAction(PlayerAction action)
        {
            for (PlayerModel player : mPlayerModels)
            {
                player.getPlayerController().onPlayerAction(action);
            }
        }

        @Override
        public void onGameEnded()
        {
            for (PlayerModel player : mPlayerModels)
            {
                player.getPlayerController().onGameEnded();
            }
        }
    }

    class PlayerActionReceiver implements IPlayerActionReceiver {
        protected PlayerModel mPlayerModel;

        public PlayerActionReceiver(PlayerModel pPlayerModel)
        {
            mPlayerModel = pPlayerModel;
        }

        @Override
        public void showCard(final List<Card> pCards)
        {
            final int actionToken = mPlayerActionToken;
            Runnable runnable = new Runnable() {
                @Override
                public void run()
                {
                    if (actionToken != mPlayerActionToken)
                    {
                        return;
                    }
                    mGame.onPlayerShowCard(mPlayerModel.getPlayer(), pCards);
                }
            };
            if (mPlayerModel.isRobot())
            {
                mHandler.postDelayed(runnable, (int)(1500 + Math.random() * 1500));
            }
            else
            {
                mHandler.post(runnable);
            }
        }

        @Override
        public void pass()
        {
            final int actionToken = mPlayerActionToken;
            Runnable runnable = new Runnable() {
                @Override
                public void run()
                {
                    if (actionToken != mPlayerActionToken)
                    {
                        return;
                    }
                    mGame.onPlayerPass(mPlayerModel.getPlayer());
                }
            };
            if (mPlayerModel.isRobot())
            {
                mHandler.postDelayed(runnable, (int)(500 + Math.random() * 1000));
            }
            else
            {
                mHandler.post(runnable);
            }
        }
    }
}
package cn.saltedfish.saltedcdd.stage.gameplay;

import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.card.Card;

public class GamePlayView implements GamePlayContract.View {
    protected WeakReference<GamePlayActivity> mActivity;

    protected ConstraintLayout mLayoutMenu;

    protected ImageButton mBtnPauseGame;

    protected ImageButton mBtnGameEndToHome;

    protected ImageButton mBtnGameEndToRefresh;

    protected GameBoardView mGameBoardView;

    protected GamePlayContract.Presenter mPresenter;

    public GamePlayView(GamePlayActivity pActivity)
    {
        mActivity = new WeakReference<>(pActivity);

        mLayoutMenu = pActivity.findViewById(R.id.layout_menu);

        mBtnPauseGame = pActivity.findViewById(R.id.button_pauseGame);
        mBtnPauseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onPauseGameClicked();
            }
        });

        mBtnGameEndToHome = pActivity.findViewById(R.id.button_gameEndToHome);
        mBtnGameEndToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onQuitGameClicked();
            }
        });

        mBtnGameEndToRefresh = pActivity.findViewById(R.id.button_gameEndToRefresh);
        mBtnGameEndToRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRestartGameClicked();
            }
        });

        ImageButton btn_continueGame = pActivity.findViewById(R.id.button_continueGame);
        btn_continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onResumeGameClicked();
            }
        });

        ImageButton btn_returnHome = pActivity.findViewById(R.id.button_returnHome);
        btn_returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onQuitGameClicked();
            }
        });

        ImageButton btn_refreshGame = pActivity.findViewById(R.id.button_refreshGame);
        btn_refreshGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRestartGameClicked();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        pActivity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);

        mGameBoardView = pActivity.findViewById(R.id.gameBoard);
        mGameBoardView.setLayoutHelper(new LayoutHelper(dm));

        mGameBoardView.getActionBarGameView().setActionListener(new ActionBarGameView.IActionListener() {
            @Override
            public void onPass()
            {
                mPresenter.onPassClicked();
            }

            @Override
            public void onShowCard()
            {
                mPresenter.onShowCardClicked();
            }

            @Override
            public void onHint()
            {
                mPresenter.onHintClicked();
            }
        });

        mGameBoardView.getPlayerGameView(0).getPlayerInfoView().setAvatarClickListener(new SpiritGameView.OnClickListener() {
            @Override
            public void onClick(SpiritGameView pView)
            {
                mPresenter.onSwitchAutoplay();
            }
        });
    }

    @Override
    public void setPauseMenuVisibility(boolean pVisible)
    {
        mGameBoardView.setBlockTouchEvent(pVisible);
        mLayoutMenu.setVisibility(pVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPauseButtonVisibility(boolean pVisible)
    {
        mBtnPauseGame.setVisibility(pVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPlayerInfo(int index, String pNickname, PlayerInfoGameView.AvatarType pAvatarType)
    {
        PlayerInfoGameView playerInfoView = mGameBoardView.getPlayerGameView(index)
                .getPlayerInfoView();
        playerInfoView.setPlayerNickname(pNickname);
        playerInfoView.setAvatar(pAvatarType);
    }

    @Override
    public void playDealCardsAnimation()
    {

    }

    @Override
    public void setPlayerCards(int index, List<Card> pCards)
    {
        mGameBoardView.getPlayerGameView(index).setHandCards(pCards);
    }


    @Override
    public void showGameResult()
    {
        mGameBoardView.getActionBarGameView().hide();

        setPauseButtonVisibility(false);
        mGameBoardView.setBlockTouchEvent(true);

        mBtnGameEndToHome.setVisibility(View.VISIBLE);
        mBtnGameEndToRefresh.setVisibility(View.VISIBLE);

        mGameBoardView.getResultGameView().setVisible(true);
    }

    @Override
    public void showPlayerPass(int index)
    {
        PlayerGameView playerGameView = mGameBoardView.getPlayerGameView(index);
        playerGameView.removeLastAction();
        playerGameView.setPassAction();
    }

    @Override
    public void showPlayerShowCard(int index, List<Card> pCards)
    {
        PlayerGameView playerGameView = mGameBoardView.getPlayerGameView(index);
        playerGameView.removeLastAction();
        playerGameView.setShowCardAction(pCards);
    }

    @Override
    public void showTurnToMyself(boolean pShowCard, boolean pPass)
    {
        mGameBoardView.getPlayerGameView(0).removeLastAction();
        mGameBoardView.getActionBarGameView().show(pShowCard, pPass, pShowCard);

        showPlayerTurnHighlight(0);
    }

    @Override
    public void showTurnToOthers(int index)
    {
        mGameBoardView.getActionBarGameView().hide();
        mGameBoardView.getPlayerGameView(index).removeLastAction();

        showPlayerTurnHighlight(index);
    }

    protected void showPlayerTurnHighlight(int pTurnTo)
    {
        for (int i = 0; i < 4; i++)
        {
            mGameBoardView.getPlayerGameView(i).getPlayerInfoView().setHighlight(i == pTurnTo);
        }
    }

    @Override
    public List<Card> getCardSelection()
    {
        return mGameBoardView.getPlayerGameView(0).getHandCardView().getSelectedCards();
    }

    @Override
    public void setCardSelection(List<Card> pCards)
    {
        mGameBoardView.getPlayerGameView(0).getHandCardView().selectAll(pCards);
    }

    @Override
    public void clearCardSelection()
    {
        mGameBoardView.getPlayerGameView(0).getHandCardView().deselectAll();
    }

    @Override
    public void setPlayerAvatar(int index, PlayerInfoGameView.AvatarType pAvatarType)
    {
        PlayerInfoGameView playerInfoView = mGameBoardView.getPlayerGameView(index)
                .getPlayerInfoView();
        playerInfoView.setAvatar(pAvatarType);
    }

    @Override
    public void setResultMyselfRank(int pRank)
    {
        mGameBoardView.getResultGameView().setRank(pRank);
    }

    @Override
    public void setResultPlayerRank(int index, String pNickname, int pScore)
    {
        mGameBoardView.getResultGameView().setLine(index, pNickname, pScore);
    }

    @Override
    public void setPresenter(GamePlayContract.Presenter pPresenter)
    {
        mPresenter = pPresenter;
    }

    public void runOnUiThread(Runnable pRunnable)
    {
        mActivity.get().runOnUiThread(pRunnable);
    }

    @Override
    public void showToast(String pToast)
    {
        Toast.makeText(mActivity.get(), pToast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void repaint()
    {
        mGameBoardView.invalidate();
    }

    public void onDestroy()
    {
        if (mGameBoardView != null)
        {
            mGameBoardView.onDestroy();
        }
    }
}

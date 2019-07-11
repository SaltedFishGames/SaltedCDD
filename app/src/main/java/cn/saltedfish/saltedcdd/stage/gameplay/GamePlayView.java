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
    public void setPlayerInfo(int index, String pNickname)
    {

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
    public void showGameResult(GameResult pResult)
    {
        mGameBoardView.getActionBarGameView().hide();
        mBtnPauseGame.setVisibility(View.GONE);
        mBtnGameEndToHome.setVisibility(View.VISIBLE);
        mBtnGameEndToRefresh.setVisibility(View.VISIBLE);
        for (int i = 0; i < 4; i++)
        {
            PlayerGameView playerGameView = mGameBoardView.getPlayerGameView(i);
            playerGameView.removeLastAction();
            playerGameView.setShowCards(pResult.getCardsLeft(i));
            playerGameView.setHandCards(null);
        }
    }

    @Override
    public void showPlayerPass(int index)
    {
        mGameBoardView.getPlayerGameView(index).showPass();
    }

    @Override
    public void showPlayerShowCard(int index, List<Card> pCards)
    {
        mGameBoardView.getPlayerGameView(index).setShowCards(pCards);
    }

    @Override
    public void showTurnToMyself(boolean pShowCard, boolean pPass)
    {
        mGameBoardView.getPlayerGameView(0).removeLastAction();
        mGameBoardView.getActionBarGameView().show(pShowCard, pPass, pShowCard);
    }

    @Override
    public void showTurnToOthers(int index)
    {
        mGameBoardView.getActionBarGameView().hide();
        mGameBoardView.getPlayerGameView(index).removeLastAction();
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

package cn.saltedfish.saltedcdd.stage.gameplay;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.card.Card;

public class GamePlayView implements GamePlayContract.View {
    protected WeakReference<GamePlayActivity> mActivity;

    ConstraintLayout mLayoutMenu;

    ImageButton mBtnPauseGame;

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
    }

    @Override
    public void setPauseMenuVisibility(boolean pVisible)
    {
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

    }

    @Override
    public void showGameResult(GameResult pResult)
    {

    }

    @Override
    public void showPlayerPass(int index)
    {

    }

    @Override
    public void showPlayerShowCard(int index, List<Card> pCards)
    {

    }

    @Override
    public void showTurnToMyself()
    {

    }

    @Override
    public void showTurnToOthers()
    {

    }

    @Override
    public void setPassEnable(boolean pIsEnable)
    {

    }

    @Override
    public void setShowCardEnable(boolean pIsEnable)
    {

    }

    @Override
    public List<Card> getCardSelection()
    {
        return null;
    }

    @Override
    public void clearCardSelection()
    {

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
}

package cn.saltedfish.saltedcdd.stage.gameplay;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class GamePlayView implements GamePlayContract.View {
    protected WeakReference<GamePlayActivity> mActivity;

    protected GamePlayContract.Presenter mPresenter;

    public GamePlayView(GamePlayActivity pActivity)
    {
        mActivity = new WeakReference<>(pActivity);
    }

    @Override
    public void showPlayerInfo(int index, String pNickname)
    {

    }

    @Override
    public void playDealCardsAnimation()
    {

    }

    @Override
    public void updatePlayerCard(int index, List<Card> pCards)
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

package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.stage.BasePresenter;
import cn.saltedfish.saltedcdd.stage.BaseView;

public interface GamePlayContract {
    interface View extends BaseView<Presenter> {
        void setPauseMenuVisibility(boolean pVisible);

        void setPauseButtonVisibility(boolean pVisible);

        void setPlayerInfo(int index, String pNickname);

        void playDealCardsAnimation(); // 播放发牌动画

        void setPlayerCards(int index, List<Card> pCards); // 更新指定玩家手牌（数目 / 手牌）

        void showGameResult(GameResult pResult); // 游戏结束，展示游戏结果

        void showPlayerPass(int index);

        void showPlayerShowCard(int index, List<Card> pCards);

        void showTurnToMyself();

        void showTurnToOthers();

        void setPassEnable(boolean pIsEnable);

        void setShowCardEnable(boolean pIsEnable);

        List<Card> getCardSelection();

        void clearCardSelection();

        void runOnUiThread(Runnable pRunnable);
    }

    interface Presenter extends BasePresenter {
        void onRestartGameClicked();

        void onResumeGameClicked();

        void onPauseGameClicked();

        void onQuitGameClicked();

        void onShowCardClicked();

        void onPassClicked();

        void onCardSelectionModified();
    }
}

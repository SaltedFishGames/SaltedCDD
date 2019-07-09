package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.List;

import cn.saltedfish.saltedcdd.game.IGamePlayer;
import cn.saltedfish.saltedcdd.game.GameRound;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.stage.BasePresenter;
import cn.saltedfish.saltedcdd.stage.BaseView;

public interface GamePlayContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter, IGamePlayer {
        void onGamePrepared();

        void onNewRound(GameRound pNewRound);
        void onPlayerTurn(Player pPlayer, GameRound pCurRound);
        void onPlayerAction(PlayerAction action);

        void onGameEnded();

        void startGame();

        void abortGame();

        void showCard(List<Card> pCards);

        void pass();
    }
}

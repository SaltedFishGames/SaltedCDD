package cn.saltedfish.saltedcdd.game.routine;

import java.util.Collection;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.GameState;
import cn.saltedfish.saltedcdd.game.IGameStateInterface;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;

public class PreparedState extends GameState {
    public void onStartGame(IGameStateInterface pGame)
    {
        if (pGame.curStateIs(PreparedState.class))
        {
            pGame.enterState(RoundHeadState.class);
            for (int i = 0; i < FourPlayerGame.PlayerCount; i++)
            {
                Player player = pGame.getPlayer(i);
                if (player.hasCard(ECardNumber.NUM_3, ECardSuit.DIAMOND))
                {
                    pGame.setCurrentTurnedPlayer(player);
                    break;
                }
            }
        }
    }
}

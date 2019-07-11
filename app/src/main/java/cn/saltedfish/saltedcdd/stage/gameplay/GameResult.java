package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.HashMap;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;

public class GameResult {
    public HashMap<Integer, List<Card>> mCardsLeft;

    public GameResult(GameModel pGameModel)
    {
        mCardsLeft = new HashMap<>();

        for (int i = 0; i < 4; i++)
        {
            mCardsLeft.put(i, pGameModel.getPlayerModel(i).getPlayer().cards());
        }
    }

    public List<Card> getCardsLeft(int index)
    {
        return mCardsLeft.get(index);
    }
}

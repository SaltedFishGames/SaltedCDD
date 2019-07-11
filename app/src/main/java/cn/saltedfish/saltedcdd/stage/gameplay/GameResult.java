package cn.saltedfish.saltedcdd.stage.gameplay;

import cn.saltedfish.saltedcdd.game.Player;

public class GameResult {
    public static int calcScore(Player pPlayer)
    {
        return (13 - pPlayer.cards().size() + 2) / 3 * 100 - 100;
    }
}

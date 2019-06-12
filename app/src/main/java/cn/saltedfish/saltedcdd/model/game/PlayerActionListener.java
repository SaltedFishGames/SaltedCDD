package cn.saltedfish.saltedcdd.model.game;

import cn.saltedfish.saltedcdd.ShowCardResult;

public interface PlayerActionListener {
    public ShowCardResult onPlayerShowCard(CDDPlayer player, CardGroup cards);

    public void onPlayerPass(CDDPlayer player, CardGroup cards);
}
package cn.saltedfish.saltedcdd.model.game;

import cn.saltedfish.saltedcdd.ShowCardResult;

public interface PlayerActionListener {
    public ShowCardResult onPlayerShowCard(CDDPlayer player, CardCollection cards);

    public void onPlayerPass(CDDPlayer player, CardCollection cards);
}
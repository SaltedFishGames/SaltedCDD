package cn.saltedfish.saltedcdd.model.game;

import cn.saltedfish.saltedcdd.ShowCardResult;

public abstract class GameStateBase {
    public abstract void onEnterState(CDDGameBoard board);

    public abstract ShowCardResult onPlayerShowCard(CDDGameBoard board, CDDPlayer player, CardCollection card);

    public abstract ShowCardResult tryPlayerShowCard(CDDGameBoard board, CDDPlayer player, CardCollection card);

    public abstract void onPlayerPass(CDDGameBoard board, CDDPlayer player);
}


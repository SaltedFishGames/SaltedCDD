package cn.saltedfish.saltedcdd.model.game;

interface GameEventListener {
    public void onGameStart();

    public void onPlayerTurn(CDDPlayer player, boolean isNewRound);

    public void onGameEnd(GameResult result);
}

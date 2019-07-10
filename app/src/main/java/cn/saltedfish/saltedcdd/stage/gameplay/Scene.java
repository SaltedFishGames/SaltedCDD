package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.GameObject;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.GameViewInfo;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.Physics;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.PlayerRenderer;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.Transform;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.Vector3;


/**
 * Description：用于放置部件的scene
 *
 * @author AUSTER on 19.7.9.
 */
public class Scene {
    public ArrayList<GameObject> gameObjectArrayList;
    private PlayerRenderer []playerRenderer;
    public Physics physics;
    public static boolean prepared = false;
    public boolean clear = false;
    /**左右玩家据中心的偏移量*/
    private int xDistance = 750;
    /**上下玩家据中心的偏移量*/
    private int yDistance = 420;
    /**单例模式*/
    private static Scene instance;
    public static Scene getInstance(){
        if (instance == null){
            instance = new Scene();
            prepared = false;
        }
        return instance;
    }

    public void prepareScene(){
        clear = false;
        physics = new Physics();
    }

    public void startNewScene(){
        test();
    }

    public void Clear(){
        if (clear){
            for (int i = 0; i < gameObjectArrayList.size(); i++) {
                gameObjectArrayList.get(i).Destroy();
            }
            gameObjectArrayList.clear();
            clear = false;
            instance = null;
        }
    }

    /**播放发牌动画*/
    public void playDealCardsAnimation(){

    }
    /**更新指定玩家手牌(数目/手牌)*/
    public void updatePlayerCard(Player pPlayer){
        playerRenderer[pPlayer.mId].updateCards();
    }
    /**显示指定的玩家动作(出牌/不出)*/
    public void showPlayerAction(PlayerAction pAction){
        PlayerRenderer player = playerRenderer[pAction.mPlayerId];
        if (pAction.mAction == EActionType.Pass){
            player.isPass = true;
        } else if(pAction.mAction == EActionType.ShowCard){
            ArrayList<Card> cards = pAction.mCards.mCards;
            for (int i = 0; i < cards.size(); i++){
                player.addSelectedCards(cards.get(i));
            }
            player.isShowCards = true;
        }
    }
    /**轮到某玩家(轮到人类玩家则显示操作按钮，否则隐藏)*/
    public void showTurn(Player pPlayer){

    }


    private void test(){
        Player player = new Player();
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(ECardNumber.NUM_3, ECardSuit.CLUB);
        Card card2 = new Card(ECardNumber.NUM_4, ECardSuit.CLUB);
        Card card3 = new Card(ECardNumber.NUM_5, ECardSuit.CLUB);
        Card card4 = new Card(ECardNumber.NUM_6, ECardSuit.CLUB);
        Card card5 = new Card(ECardNumber.NUM_7, ECardSuit.CLUB);
        Card card6 = new Card(ECardNumber.NUM_8, ECardSuit.CLUB);
        Card card7 = new Card(ECardNumber.NUM_9, ECardSuit.CLUB);
        Card card8 = new Card(ECardNumber.NUM_10, ECardSuit.CLUB);
        Card card9 = new Card(ECardNumber.NUM_10, ECardSuit.DIAMOND);
        Card card10 = new Card(ECardNumber.NUM_8, ECardSuit.SPADE);
        Card card11 = new Card(ECardNumber.NUM_7, ECardSuit.HEART);
        Card card12 = new Card(ECardNumber.NUM_8, ECardSuit.HEART);
        Card card13 = new Card(ECardNumber.NUM_6, ECardSuit.DIAMOND);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);
        cards.add(card12);
        cards.add(card13);
        player.mCards = cards;

        Transform transform = new Transform(new Vector3(GameViewInfo.centerW, GameViewInfo.centerH + yDistance, 0), 0, Vector3.ONE);

        playerRenderer = new PlayerRenderer[4];
        playerRenderer[0] = new PlayerRenderer(transform,player,true);

        player.mId = 2;
        Transform transform2 = new Transform(new Vector3(GameViewInfo.centerW, GameViewInfo.centerH - yDistance, 0), 0, Vector3.ONE);
        playerRenderer[2] = new PlayerRenderer(transform2,player);

        player.mId = 1;
        Transform transform1 = new Transform(new Vector3(GameViewInfo.centerW + xDistance, GameViewInfo.centerH, 0), 0, Vector3.ONE);
        playerRenderer[1] = new PlayerRenderer(transform1,player);

        player.mId = 3;
        Transform transform3 = new Transform(new Vector3(GameViewInfo.centerW - xDistance, GameViewInfo.centerH, 0), 0, Vector3.ONE);
        playerRenderer[3] = new PlayerRenderer(transform3,player);
    }

}

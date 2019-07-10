package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.stage.gameplay.GameView;
import cn.saltedfish.saltedcdd.stage.gameplay.Scene;


/**
 * Description：游戏参与者渲染器，用于管理卡牌等的渲染
 *
 * @author AUSTER on 19.7.9.
 */
public class PlayerRenderer extends Renderer{

    public ButtonRenderer btn_pass;
    public ButtonRenderer btn_show;
    public ArrayList<CardRenderer> cardRenderers;
    public ArrayList<CardRenderer> selectedCards;
    public ArrayList<CardRenderer> outCards;
    private Player player;
    private boolean isPlayer = false;
    /**卡牌方向，0为正，1为侧*/
    private int direction;
    public boolean isShowCards = false;
    public boolean isPass = false;
    public boolean isTurn = true;
    public Transform transform;
    public Transform showTransform;
    public int cardsNum;
    private int xOffset = 80, yOffset = 110;
    /**卡牌距离*/
    private int xDistance = 60, yDistance = 30;

    public PlayerRenderer(Transform transform, Player player){
        this(transform,player,false);
    }

    public PlayerRenderer(Transform transform, Player player, boolean isPlayer){
        this.transform = transform;
        this.player = player;
        this.isPlayer = isPlayer;
        showTransform = new Transform(new Vector3(GameViewInfo.centerW - 180,
                GameViewInfo.centerH, 25), 0, Vector3.ONE);
        showTransform.setScale(new Vector3(0.5f,0.5f,1));
        getCardsNum();
        cardRenderers = new ArrayList<>();
        selectedCards = new ArrayList<>();
        outCards = new ArrayList<>();

        if (player.mId % 2 == 0){
            direction = 0;
        } else {direction = 1;}

        drawCards();

        if (isPlayer){
            drawButtons();
        }
    }

    private void getCardsNum(){
        cardsNum = player.mCards.size();
    }

    /**绘制按钮*/
    private void drawButtons(){
        Transform transformBtnPass = new Transform(new Vector3(GameViewInfo.centerW - xOffset*5,
                GameViewInfo.centerH + yOffset, 20), 0, Vector3.ONE);
        btn_pass = new ButtonRenderer(R.drawable.pass_up, R.drawable.pass_down,R.drawable.pass_lock,transformBtnPass);
        btn_pass.setPlayer(this);
        Transform transformBtnShow = new Transform(new Vector3(GameViewInfo.centerW + xOffset,
                GameViewInfo.centerH + yOffset, 20), 0, Vector3.ONE);
        btn_show = new ButtonRenderer(R.drawable.show_card_up, R.drawable.show_card_down,
                R.drawable.show_card_lock,transformBtnShow);
        btn_show.setPlayer(this);
    }

    /**显示玩家卡组*/
    private void drawCards(){
        Vector3 position = transform.getPosition();
        Vector3 distanceVector3;
        Vector3 firstPosition;
        if (direction == 0){
            distanceVector3 = new Vector3(xDistance,0,1);
            firstPosition = new Vector3((position.x - xDistance * cardsNum / 2),position.y,position.z);
        } else {
            distanceVector3 = new Vector3(0, yDistance,0);
            firstPosition = new Vector3(position.x,(position.y - yDistance * cardsNum / 2),position.z);
        }
        Collections.sort(player.mCards);
        for (int i = 0; i < cardsNum; i++){
            Card card = player.mCards.get(i);
            CardRenderer cardRenderer = new CardRenderer(card.getNumber(),card.getSuit(),
                    new Transform(firstPosition, 0, Vector3.ONE), isPlayer);
            cardRenderer.setPlayer(this);
            cardRenderers.add(cardRenderer);
            Scene.getInstance().gameObjectArrayList.add(cardRenderer);
            firstPosition = firstPosition.add(distanceVector3);
        }

    }

    public void updateCards() {
        cardsNum = cardRenderers.size();
        Vector3 position = transform.getPosition();
        Vector3 distanceVector3;
        Vector3 firstPosition;
        if (direction == 0){
            distanceVector3 = new Vector3(xDistance,0,1);
            firstPosition = new Vector3((position.x - xDistance * cardsNum / 2),position.y,position.z);
        } else {
            distanceVector3 = new Vector3(0, yDistance,0);
            firstPosition = new Vector3(position.x,(position.y - yDistance * cardsNum / 2),position.z);
        }
        Collections.sort(cardRenderers);
        for (int i = 0; i < cardRenderers.size();i++) {
            cardRenderers.get(i).setTransform(new Transform(firstPosition, 0, Vector3.ONE));
            firstPosition = firstPosition.add(distanceVector3);
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        /*绘制pass*/
        if (isPass){
            Renderer.rendererArrayList.removeAll(outCards);
            Scene.getInstance().gameObjectArrayList.removeAll(outCards);
            outCards.removeAll(outCards);
            Bitmap pass = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), R.drawable.pass);
            canvas.drawBitmap(pass, showTransform.transformMatrix, null);
        }
    }

    @Override
    public void Update(){
        /*玩家出牌*/
        if (isShowCards){
            System.out.println("Hand Cards Number:" + cardRenderers.size());
            System.out.println("RemoveResult:" + cardRenderers.removeAll(selectedCards));
            System.out.println("After Show Cards Number:" + cardRenderers.size());
            Renderer.rendererArrayList.removeAll(outCards);
            Scene.getInstance().gameObjectArrayList.removeAll(outCards);
            outCards.removeAll(outCards);
            System.out.println("Out Cards Number:" + outCards.size());
            outCards.addAll(selectedCards);

            selectedCards.clear();
            outCardAnimation();
            updateCards();
            isShowCards = false;
        }
        if (isTurn){
//            isPass = false;
//            isShowCards = false;
        }
    }

    public void outCardAnimation() {
        for (CardRenderer card:outCards
        ) {
            card.setCanSelectCard(false);
            System.out.println("Can SelectCard<-"+ card.isCanSelectCard());
        }
        int distance = 60;
        Vector3 distanceVector3 = new Vector3(distance, 0, 0);
        Vector3 firstPosition = getOutCardsFirstPosition();
        Collections.sort(outCards);
        for (int i = 0; i < outCards.size(); i++) {
            CardRenderer card = outCards.get(i);
            Vector3 cardPosition = Vector3.lerp(firstPosition, transform.getPosition(), (float) 0.3);
            card.outCardsAnimation(cardPosition);
            firstPosition = firstPosition.add(distanceVector3);
        }
    }

    public void addSelectedCards(Card card){
        for (int i = 0; i < cardRenderers.size();i++){
            CardRenderer cardR = cardRenderers.get(i);
            if (card.equals(cardR.pNumber, cardR.pSuit)){
                cardR.selecting = true;
                this.selectedCards.add(cardR);
                return;
            }
        }
    }

    private Vector3 getOutCardsFirstPosition(){
        Vector3 firstPosition;
        switch (player.mId){
            case 0:
                firstPosition = new Vector3(GameViewInfo.centerW - xDistance * outCards.size() / 2,
                        GameViewInfo.centerH - 100, 0);
                break;
            case 1:
                firstPosition = new Vector3(GameViewInfo.centerW - xDistance * outCards.size() / 2,
                        GameViewInfo.centerH, 0);
                break;
            case 2:
                firstPosition = new Vector3(GameViewInfo.centerW - xDistance * outCards.size() / 2,
                        GameViewInfo.centerH + 100, 0);
                break;
            case 3:
                firstPosition = new Vector3(GameViewInfo.centerW - xDistance * outCards.size() / 2,
                        GameViewInfo.centerH, 0);
                break;
            default:
                firstPosition = new Vector3(GameViewInfo.centerW - xDistance * outCards.size() / 2,
                         GameViewInfo.centerH - 100, 0);
        }

        return firstPosition;
    }
}

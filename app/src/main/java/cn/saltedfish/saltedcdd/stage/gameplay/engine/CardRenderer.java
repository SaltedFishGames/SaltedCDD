package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;
import cn.saltedfish.saltedcdd.stage.gameplay.GameView;

/**
 * Description：卡牌渲染器
 *
 * @author AUSTER on 19.7.9.
 */
public class CardRenderer extends Renderer{
    public Transform transform;
    private PlayerRenderer playerRenderer;
    private Bitmap suit, figure, background, cardBack;
    private int suitID, figureID, backgroundID, cardBackID;
    /**卡牌正面显示标志位*/
    private boolean side;
    /**卡牌是否在选中状态*/
    public boolean selecting = false;
    /**卡牌是否可选*/
    private boolean canSelectCard = true;
    /**确保一次触摸对一张卡牌只响应一次*/
    private boolean canTouchCard = true;
    private int bitmapWidth, bitmapHeight;
    private boolean set;

    /**保存数字和花色，用于比较*/
    public ECardNumber pNumber;
    public ECardSuit pSuit;
    private TransformToTarget transformToTarget = new TransformToTarget();

    public CardRenderer(Transform transform){
        this(transform, false);
    }
    public CardRenderer(Transform transform, boolean side){
        this(ECardNumber.NUM_3, ECardSuit.DIAMOND, transform, side);
    }
    public CardRenderer(ECardNumber pNumber, ECardSuit pSuit, Transform transform, boolean side){
        setTransform(transform);
        setSuitAndFigure(pNumber, pSuit);
        this.backgroundID = R.drawable.card_background;
        this.cardBackID = R.drawable.card_back;
        this.side = side;
        set = true;
    }

    private void setSuitAndFigure(ECardNumber pNumber, ECardSuit pSuit){
        this.pNumber = pNumber;
        this.pSuit = pSuit;
        switch (pSuit){
            case DIAMOND:
                suitID = R.drawable.diamond;
                setRedFigureID(pNumber);
                break;
            case CLUB:
                suitID = R.drawable.club;
                setBlackFigureID(pNumber);
                break;
            case HEART:
                suitID = R.drawable.heart;
                setRedFigureID(pNumber);
                break;
            case SPADE:
                suitID = R.drawable.spade;
                setBlackFigureID(pNumber);
                break;
            default:
        }
    }

    private void setRedFigureID(ECardNumber pNumber){
        switch (pNumber){
            case NUM_3:
                figureID = R.drawable.red_three;
                break;
            case NUM_4:
                figureID = R.drawable.red_four;
                break;
            case NUM_5:
                figureID = R.drawable.red_five;
                break;
            case NUM_6:
                figureID = R.drawable.red_six;
                break;
            case NUM_7:
                figureID = R.drawable.red_seven;
                break;
            case NUM_8:
                figureID = R.drawable.red_eight;
                break;
            case NUM_9:
                figureID = R.drawable.red_nine;
                break;
            case NUM_10:
                figureID = R.drawable.red_ten;
                break;
            case NUM_J:
                figureID = R.drawable.red_joker;
                break;
            case NUM_Q:
                figureID = R.drawable.red_queen;
                break;
            case NUM_K:
                figureID = R.drawable.red_king;
                break;
            case NUM_A:
                figureID = R.drawable.red_ace;
                break;
            case NUM_2:
                figureID = R.drawable.red_two;
                break;

            default:
        }

    }

    private void setBlackFigureID(ECardNumber pNumber){
        switch (pNumber){
            case NUM_3:
                figureID = R.drawable.black_three;
                break;
            case NUM_4:
                figureID = R.drawable.black_four;
                break;
            case NUM_5:
                figureID = R.drawable.black_five;
                break;
            case NUM_6:
                figureID = R.drawable.black_six;
                break;
            case NUM_7:
                figureID = R.drawable.black_seven;
                break;
            case NUM_8:
                figureID = R.drawable.black_eight;
                break;
            case NUM_9:
                figureID = R.drawable.black_nine;
                break;
            case NUM_10:
                figureID = R.drawable.black_ten;
                break;
            case NUM_J:
                figureID = R.drawable.black_joker;
                break;
            case NUM_Q:
                figureID = R.drawable.black_queen;
                break;
            case NUM_K:
                figureID = R.drawable.black_king;
                break;
            case NUM_A:
                figureID = R.drawable.black_ace;
                break;
            case NUM_2:
                figureID = R.drawable.black_two;
                break;

                default:
        }

    }

    public void setSide(boolean side){
        this.side = side;
    }

    public void setCanSelectCard(boolean canSelectCard){ this.canSelectCard = canSelectCard;}

    private void setBitmap(){
        suit = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), suitID);
        background = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), backgroundID);
        bitmapWidth = background.getWidth();
        bitmapHeight = background.getHeight();
        cardBack = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), cardBackID);
        figure = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), figureID);
    }

    public boolean isCanSelectCard(){return canSelectCard;}

    @Override
    public void draw(Canvas canvas, Paint paint){
        if (set) {
            /*设置图片资源时*/
            if (GameView.surfaceContext != null) {
                setBitmap();
                set = false;
            }
        }
        if (suit != null && background != null && cardBack != null) {
            Vector3 pivot = transform.getPivot();
            Vector3 position = transform.getPosition();
            Vector3 scale = transform.getScale();
            /*卡牌正面显示*/
            if (side) {
                transform.setPivot(new Vector3((float) background.getWidth() / 2, (float) background.getHeight() / 2, 0));
                canvas.drawBitmap(background, transform.transformMatrix, null);
                transform.setPivot(new Vector3((float) suit.getWidth() / 2, (float) suit.getHeight() / 2, 0));
                canvas.drawBitmap(suit, transform.transformMatrix, null);
                //Draw figure
                Vector3 distance = new Vector3(bitmapWidth, bitmapHeight, 0).divide(new Vector3(3, 5, 1)).
                        multiply(new Vector3((float) 2.8, (float) 2.8, 1));
                transform.setPivot(transform.getPivot().add(distance));
                transform.setScale(scale.divide(new Vector3((float) 2.8, (float) 2.8, 1)));
                canvas.drawBitmap(suit, transform.transformMatrix, null);
                distance = new Vector3(0, bitmapHeight, 0).divide(new Vector3(1, (float) 6, 1)).
                        multiply(new Vector3((float) 2.8, (float) 2.8, 1));
                transform.setPivot(transform.getPivot().add(distance));
                canvas.drawBitmap(figure, transform.transformMatrix, null);

            } else {
                transform.setPivot(new Vector3((float) cardBack.getWidth() / 2, (float) cardBack.getHeight() / 2, 0));
                canvas.drawBitmap(cardBack, transform.transformMatrix, null);
            }
            transform.setPivot(pivot);
            transform.setPosition(position);
            transform.setScale(scale);
            setCollider();
            transformToTarget.setTransform(transform);
        }
        zDepth = transform.getPosition().z;
        if (toBeDestroy) {
            destroy();
        }
    }

    private void destroy(){
        Renderer.rendererArrayList.remove(this);
    }

    public void setTransform(Transform transform){
        this.transform = transform;
        /*缩放卡牌*/
        this.transform.setScale(new Vector3(0.5f,0.5f,1));
    }

    public void setCollider(){
        collider.transform = transform;
        collider.setSize(new Vector2(bitmapWidth * transform.getScale().x, bitmapHeight * transform.getScale().y));
    }

    @Override
    public void Update(){
        if (canSelectCard && Input.touching && side && canTouchCard) {
            if (collider != null) {
                if (Physics.raycast(Input.touchPosition) == collider) {
                    if (selecting){
                        selecting = false;
                        playerRenderer.selectedCards.remove(this);
                    }else {
                        selecting = true;
                        playerRenderer.selectedCards.add(this);
                    }
                    System.out.println("card selecting:" + playerRenderer.selectedCards.size());
                    selectAnimation();
                    canTouchCard = false;
                }
            }
        }
        if (Input.touchUp){
            canTouchCard = true;
        }
        transformToTarget.Update();
    }

    public void setPlayer(PlayerRenderer player){
        playerRenderer = player;
    }

    private void selectAnimation(){
        Vector3 targetPosition = transform.getPosition();
        System.out.println(targetPosition.toString());
        if (selecting) {
            System.out.println("selecting");
            targetPosition.y -= 50;
        } else {
            System.out.println("don't selecting");
            targetPosition.y += 50;
        }
        transformToTarget.beginMove(targetPosition, 30 * GameViewInfo.deltaTime);
    }

    public void outCardsAnimation(Vector3 targetPosition){

        transformToTarget.beginMove(targetPosition, 30 * GameViewInfo.deltaTime);
    }

    /*public int getBitmapWidth(){return bitmapWidth;}
    public int getBitmapHeight(){return bitmapHeight;}*/

}

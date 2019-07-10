package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.gameplay.GameView;

/**
 * Description：
 *
 * @author AUSTER on 19.7.10.
 */
public class ButtonRenderer extends Renderer {
    static public ArrayList<ButtonRenderer> rendererList = new ArrayList<>();

    private PlayerRenderer playerRenderer;
    public static boolean clear = false;

    protected Transform transform;
    private Bitmap normalResource, focusResource, disableResource;
    private int state;
    private int bitmapHeight, bitmapWidth;
    private int normalID, focusID, disableID;
    private boolean enablePass = true;


    public ButtonRenderer() {
        this(R.drawable.default_sprite, R.drawable.default_sprite, R.drawable.default_sprite, new Transform());
    }

    public ButtonRenderer(int normal, int focus, int disable, Transform transform) {
        normalID = normal;
        focusID = focus;
        disableID = disable;
        this.transform = transform;
        /*缩放*/
        this.transform.setScale(new Vector3(0.6f,0.6f,1));
        clear = false;
        synchronized (rendererList) {
            rendererList.add(this);
        }
        state = 0;
    }
    public void setPlayer(PlayerRenderer player){
        playerRenderer = player;
    }
    public void setNormal(){
        state = 0;
    }
    public void setFocus(){
        state = 1;
    }
    public void setDisable(){
        state = 2;
    }

    public Bitmap getBitmapResource() {
        return normalResource;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (playerRenderer.isTurn){
            if (GameView.surfaceContext != null) {
                if (normalResource == null) {
                    normalResource = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), normalID);
                    bitmapWidth = normalResource.getWidth();
                    bitmapHeight = normalResource.getHeight();
                }
                if (focusResource == null) {
                    focusResource = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), focusID);
                }
                if (disableResource == null) {
                    disableResource = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), disableID);
                }
            }
            if(normalResource != null && focusResource != null && disableResource != null){
                switch (state){
                    case 0:
                        canvas.drawBitmap(normalResource, transform.transformMatrix, null);
                        break;
                    case 1:
                        canvas.drawBitmap(focusResource, transform.transformMatrix, null);
                        break;
                    case 2:
                        canvas.drawBitmap(disableResource, transform.transformMatrix, null);
                        break;
                    default:
                }
                setCollider();
            }
            zDepth = transform.getPosition().z;
        }
        if (toBeDestroy) {
            Destroy();
        }
    }

    public void setCollider(){
        collider.transform = transform;
        collider.setSize(new Vector2(bitmapWidth * transform.getScale().x, bitmapHeight * transform.getScale().y));
        collider.setOffset(new Vector2(bitmapWidth* transform.getScale().x / 2, bitmapHeight* transform.getScale().y /2));
    }

    @Override
    public void Update(){
        if(!enablePass){
            setDisable();
        }
        else {
            setNormal();
            if(Input.touching){
                if(collider != null){
                    if(Physics.raycast(Input.touchPosition) == collider){
                        setFocus();
                    }
                    else {
                        setNormal();
                    }
                }

            }
            if(Input.touchUp && Physics.raycast(Input.touchPosition) == collider){
//                playerRenderer.isTurn = false;
                if (normalID == R.drawable.pass_up){
                    playerRenderer.isPass = true;
                    playerRenderer.isShowCards =false;
                } else if (normalID == R.drawable.show_card_up){
                    playerRenderer.isPass = false;
                    playerRenderer.isShowCards = true;
                }

            }
        }
    }

    public int getBitMapWidth() {
        return bitmapWidth;
    }

    public int getBitMapHeight() {
        return bitmapHeight;
    }
}

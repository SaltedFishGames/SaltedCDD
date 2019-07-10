package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.gameplay.GameView;

/**
 * Description：渲染器父类
 *
 * @author AUSTER on 19.7.9.
 */
public class Renderer extends GameObject implements Comparable<Renderer> {
    public static ArrayList<Renderer> rendererArrayList = new ArrayList<>();

    private Bitmap bitmapResource;
    private int bitmapWidth, bitmapHeight;
    private int bitmapID;
    private int lastID;

    protected float zDepth;
    public Transform transform;
    private boolean setBitmap = false;
    public static boolean clear = false;

    public Renderer(){
        this(R.drawable.default_sprite);
    }
    public Renderer(int id){
        bitmapID = id;
        clear = false;
        synchronized (rendererArrayList){
            rendererArrayList.add(this);
        }
    }

    public void draw(Canvas canvas, Paint paint){
        System.out.println("draw");
        if (bitmapResource == null){
            if (GameView.surfaceContext != null){
                setBitmapResource();
            }
        } else {
            canvas.drawBitmap(bitmapResource, transform.transformMatrix, paint);
            if (setBitmap){
                if (bitmapID != lastID){
                    setBitmapResource();
                }
                setBitmap = false;
            }
        }
        zDepth = transform.getPosition().z;

    }

    private void setBitmapResource(){
        bitmapResource = BitmapFactory.decodeResource(GameView.surfaceContext.getResources(), bitmapID);
        bitmapWidth = bitmapResource.getWidth();
        bitmapHeight = bitmapResource.getHeight();
        lastID = bitmapID;
    }

    public void setBitmapID(int id){
        bitmapID = id;
        setBitmap = true;
    }

    @Override
    public void Destroy() {
        synchronized (rendererArrayList){
            rendererArrayList.remove(this);
            super.Destroy();
        }
    }

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    @Override
    public int compareTo(Renderer o) {
        if (zDepth > o.zDepth) {
            return 1;
        } else if (zDepth == o.zDepth){
            return 0;
        } else {
            return -1;
        }
    }
}

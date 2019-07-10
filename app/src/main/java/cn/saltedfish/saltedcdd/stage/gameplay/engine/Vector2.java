package cn.saltedfish.saltedcdd.stage.gameplay.engine;

/**
 * Description：二维向量，用于部件面积和偏移量
 *
 * @author AUSTER on 19.7.8.
 */
public class Vector2 implements Cloneable {
    public static Vector2 ZERO = new Vector2(0,0), ONE = new Vector2(1,1);
    public float x;
    public float y;

    public Vector2(){
        this(0, 0);
    }

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**向量运算*/
    public Vector2 add(Vector2 vector2){
        return new Vector2(this.x + vector2.x, this.y + vector2.y);
    }

    public Vector2 minus(Vector2 vector2){
        return new Vector2(this.x - vector2.x, this.y - vector2.y);
    }

    @Override
    public Vector2 clone(){
        return new Vector2(this.x, this.y);
    }

    @Override
    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    /**向量差值*/
    public static Vector2 lerp(Vector2 from, Vector2 to, float time){
        Vector2 vector2 = new Vector2();
        /*运动时间大于等于0小于等于1*/
        time = Math.max(0, Math.min(time, 1));
        vector2.x = from.x + (to.x - from.x)*time;
        vector2.y = from.y + (to.y - from.y)*time;
        return vector2;
    }
}

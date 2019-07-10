package cn.saltedfish.saltedcdd.stage.gameplay.engine;

/**
 * Description：三维向量，用于部件坐标定位
 *
 * @author AUSTER on 19.7.9.
 */
public class Vector3 implements Cloneable {
    public float x, y, z;
    public static final Vector3 ZERO = new Vector3();
    public static final Vector3 ONE = new Vector3(1, 1, 1);

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(){
        this(0, 0, 0);
    }

    public Vector3 add(Vector3 vector3){
        return new Vector3(x + vector3.x, y + vector3.y, z + vector3.z);
    }
    public Vector3 minus(Vector3 vector3){
        return new Vector3(x - vector3.x, y - vector3.y, z - vector3.z);
    }

    public Vector3 multiply(Vector3 vector3){
        return new Vector3(x * vector3.x, y * vector3.y, z * vector3.z);
    }

    public Vector3 divide(Vector3 vector3){
        vector3.x = Math.max((float) 0.0001, vector3.x);
        vector3.y = Math.max((float) 0.0001, vector3.y);
        vector3.z = Math.max((float) 0.0001, vector3.z);
        return new Vector3(x / vector3.x, y / vector3.y, z / vector3.z);
    }

    public boolean equal(Vector3 vector3){
        return (vector3.x == x && vector3.y == y && vector3.z == z);
    }

    @Override
    public Vector3 clone(){
        Vector3 vector3 = new Vector3(x, y, z);
        return vector3;
    }

    @Override
    public String toString(){
        return ("(" + x + "," + y + "," + z + ")");
    }

    /**向量差值，用于补间动画*/
    public static Vector3 lerp(Vector3 from, Vector3 to, float time){
        Vector3 vector3 = new Vector3();
        time = Math.max(0, Math.min(time, 1));
        vector3.x = from.x + (to.x - from.x) * time;
        vector3.y = from.y + (to.y - from.y) * time;
        vector3.z = from.z + (to.z - from.z) * time;
        return vector3;
    }
}

package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Description：获取部件所在的区域
 *
 * @author AUSTER on 19.7.10.
 */
public class BoxCollider implements Comparable<BoxCollider> {
    private Vector2 size;
    private Vector2 offset;
    public Transform transform;

    public BoxCollider() {
        this(Vector2.ZERO, Vector2.ZERO, new Transform());
    }

    public BoxCollider(Vector2 size, Vector2 offset, Transform transform) {
        this.size = size.clone();
        this.offset = offset.clone();
        this.transform = transform;
        if(Physics.colliders == null){
            Physics.colliders = new ArrayList<>();
        }
        Physics.colliders.add(this);
    }

    public void setSize(Vector2 size) {
        this.size = size.clone();
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset.clone();
    }

    public Vector2 getSize() {
        return new Vector2(size.x / GameViewInfo.screenW * GameViewInfo.fixedW,
                size.y / GameViewInfo.screenH * GameViewInfo.fixedH);
    }

    public Vector2 getOffset() {
        return offset;
    }

    @Override
    public int compareTo(@NonNull BoxCollider b) {
        float aZ = transform.getPosition().z;
        float bZ = b.transform.getPosition().z;
        if(aZ > bZ){
            return -1;
        }
        else if(aZ == bZ){
            return 0;
        }
        else{
            return 1;
        }

    }
}

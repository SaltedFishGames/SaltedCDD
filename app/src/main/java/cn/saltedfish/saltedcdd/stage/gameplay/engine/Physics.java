package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Description：物体的空间属性
 *
 * @author AUSTER on 19.7.10.
 */
public class Physics {
    public static ArrayList<BoxCollider> colliders;
    /**检测物体区域碰撞*/
    public static BoxCollider raycast(Vector2 ray){
        if(colliders != null){
            Collections.sort(colliders);
            for (int i = 0; i < colliders.size(); i++) {
                BoxCollider collider = colliders.get(i);
                Transform transform = collider.transform;
                Vector2 offset = collider.getOffset();
                Vector2 size = collider.getSize();
                if(ray.x < transform.getPosition().x + offset.x - size.x / 2 )
                {continue;}
                if(ray.x > transform.getPosition().x + offset.x + size.x / 2 )
                {continue;}
                if(ray.y < transform.getPosition().y + offset.y - size.y / 2 )
                {continue;}
                if(ray.y > transform.getPosition().y + offset.y + size.y / 2 )
                {continue;}
                return collider;
            }
        }
        return null;
    }
    public static void Clear(){
        if(colliders != null){
            colliders.clear();
        }
    }
}

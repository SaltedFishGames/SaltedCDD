package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import java.util.ArrayList;

import cn.saltedfish.saltedcdd.stage.gameplay.Scene;

/**
 * Description：视图部件父类
 *
 * @author AUSTER on 19.7.9.
 */
public class GameObject {

    public Transform transform;
    public BoxCollider collider;
    /**游戏对象销毁标志位*/
    public boolean toBeDestroy;


    public GameObject(){this(new Transform());}
    public GameObject(Transform transform){
        this.transform = transform;
        collider = new BoxCollider();

        if(Scene.getInstance().gameObjectArrayList == null){
            Scene.getInstance().gameObjectArrayList = new ArrayList<>();
        }
        Scene.getInstance().gameObjectArrayList.add(this);
        toBeDestroy = false;
    }

    public void Update(){

    }

    public void Destroy(){
        Scene.getInstance().gameObjectArrayList.remove(this);
        toBeDestroy = true;
    }

}

package cn.saltedfish.saltedcdd.stage.gameplay.engine;

import android.graphics.Matrix;

import java.util.ArrayList;

/**
 * Description：坐标变换
 *
 * @author AUSTER on 19.7.9.
 */
public class Transform{
    private static Transform world = new Transform(null);
    private Vector3 position;
    private Vector3 localPosition;
    private float rotation;
    private float localRotation;
    private Vector3 scale;
    private Vector3 localScale;
    private Vector3 pivot = new Vector3();
    public Matrix transformMatrix = new Matrix();
    private Transform parent;
    private ArrayList<Transform> children = new ArrayList<>();

    public Transform(Transform parent){
        position = Vector3.ZERO;
        localPosition = Vector3.ZERO;
        rotation = 0;
        localRotation = 0;
        scale = Vector3.ONE;
        localScale = Vector3.ONE;
        updateMatrix();
    }
    public Transform(){
        this(Vector3.ZERO, 0, Vector3.ZERO);
    }
    public Transform(Vector3 position, float rotation, Vector3 pivot){
        this(position, rotation,Vector3.ONE, pivot);
    }
    public Transform(Vector3 position, float rotation, Vector3 localScale, Vector3 pivot){
        this(position, rotation, world, localScale, pivot);
    }
    public Transform(Vector3 position, float rotation, Transform parent, Vector3 localScale, Vector3 pivot){
        this.position = position.clone();
        this.localPosition = position.minus(parent.getPosition());
        this.rotation = rotation;
        this.localRotation = rotation - parent.rotation;
        this.scale = localScale.multiply(parent.getScale());
        this.localScale = localScale.clone();
        this.pivot = pivot.clone();
        updateMatrix();
        this.parent = parent;
        this.parent.addChildren(this);
    }
    public Transform(Vector3 localPosition, float localRotation, Vector3 localScale, Transform parent, Vector3 pivot){
        this.position = localPosition.add(parent.position);
        this.localPosition = localPosition.clone();
        this.rotation = localRotation + parent.rotation;
        this.localRotation = localRotation;
        this.scale = localScale.multiply(parent.getScale());
        this.localScale = localScale.clone();
        this.pivot = pivot.clone();
        updateMatrix();
        this.parent = parent;
        this.parent.addChildren(this);
    }

    private void addChildren(Transform transform){
        children.add(transform);
    }

    public Vector3 getPosition(){return position.clone(); }
    public float getRotation(){return rotation;}
    public Vector3 getScale(){return scale.clone();}
    public Vector3 getPivot(){return pivot;}

    public void setParent(Transform parent){
        this.parent = parent;
        this.localPosition = position.minus(parent.getPosition());
        this.localRotation = rotation - parent.getRotation();
        this.localScale = scale.divide(parent.getScale());
    }
    public void setPosition(Vector3 position){
        this.position = position.clone();
        this.localPosition = position.minus(parent.getPosition());
        updateMatrix();
    }
    public void setRotation(float rotation){
        this.rotation = rotation;
        this.localRotation = rotation - parent.getRotation();
        updateMatrix();
    }
    public void setScale(Vector3 scale){
        this.scale = scale.clone();
        this.localScale = scale.divide(parent.getScale());
        updateMatrix();
    }
    public void setPivot(Vector3 pivot){
        this.pivot = pivot.clone();
        updateMatrix();
    }


    private void updateMatrix(){
        if (parent != null){
            position = localPosition.add(parent.getPosition());
            rotation = localRotation + parent.getRotation();
            scale = localScale.multiply(parent.getScale());
        }
        transformMatrix.setTranslate(position.x - pivot.x, position.y - pivot.y);
        transformMatrix.preRotate(rotation, pivot.x, pivot.y);
        transformMatrix.preScale(scale.x, scale.y, pivot.x, pivot.y);
    }

    public void update(){updateMatrix();}

}

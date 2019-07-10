package cn.saltedfish.saltedcdd.stage.gameplay.engine;

/**
 * Description：平移缩放动画
 *
 * @author AUSTER on 19.7.9.
 */
public class TransformToTarget {
    private Transform transform;
    private boolean move = false;
    private boolean scale = false;

    /**Move*/
    private Vector3 position;
    private float moveSpeed;
    /**Scale*/
    private Vector3 targetScale;
    private float scaleSpeed;

    public void setTransform(Transform transform){
        this.transform = transform;
    }

    public void Update(){
        move();
    }

    public void beginMove(Vector3 position, float moveSpeed){
        this.position = position.clone();
        this.moveSpeed = moveSpeed;
        move = true;
    }

    private void move(){
        if(move){
            Vector3 originPosition = transform.getPosition();
            transform.setPosition(Vector3.lerp(originPosition, position, moveSpeed));
            if(originPosition.equal(position)){
                move = false;
            }

        }
    }

}

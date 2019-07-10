package cn.saltedfish.saltedcdd.stage.gameplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Collections;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.EActionType;
import cn.saltedfish.saltedcdd.game.Player;
import cn.saltedfish.saltedcdd.game.PlayerAction;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.GameViewInfo;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.Input;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.PlayerRenderer;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.Renderer;
import cn.saltedfish.saltedcdd.stage.gameplay.engine.Vector2;

/**
 * Description：
 *
 * @author AUSTER on 19.7.8.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    /**需要渲染的内容*/
    public static Context surfaceContext;
    /**控制SurfaceView，处理Surface的生命周期*/
    private SurfaceHolder mSurfaceHolder;
    /**绘画的画布*/
    private Canvas mCanvas;
    /**画笔*/
    Paint mPaint;
    /**控制绘画线程的标志位*/
    private boolean mIsDrawing;
    /**游戏线程*/
    GameThread mGameThread;
    /**每30帧刷新一次屏幕*/
    private int frameDeltaTime = 30;
    /**屏幕的宽和高*/
    private int screenWidth, screenHeight;
    /**view的宽高比*/
    private int viewWidth = 16, viewHeight = 9;
    /**触摸定位*/
    private int touchX, touchY;
    /**触摸事件标志位*/
    private boolean touchDown = false;
    private boolean touchUp = true;
    private boolean touching;
    /**游戏结束标志位*/
    private boolean isGameOver = false;

    public GameView(Context context){ this(context, null); }
    public GameView(Context context, AttributeSet attributeSet){
        this(context, attributeSet, 0);
    }

    public GameView(Context context, AttributeSet attributeSet, int differentStyleAttribute){
        super(context, attributeSet, differentStyleAttribute);
        surfaceContext = context;
        initView();
    }

    private void initView(){
        //获取SurfaceHolder对象
        mSurfaceHolder = getHolder();
        //注册SurfaceHolder的回调方法
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
        //新建画笔
        mPaint = new Paint();
        //准备场景
        Scene.getInstance().prepareScene();
        //设置焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    /**暂停游戏*/
    public void pauseGameView(){
        mGameThread.onThreadPause();
    }
    /**继续游戏*/
    public void continueGameView(){
        mGameThread.onThreadResume();
    }
    /**退出游戏*/
    public void exitGameView(){
        mGameThread.isRunning = false;
    }

    /**游戏运行逻辑*/
    private void gameLogic(){
        /*设置全局属性*/
        GameViewInfo.screenW = screenWidth;
        GameViewInfo.screenH = screenHeight;
        updateInput();
        /*根据输入更新视图部件*/
        synchronized (Scene.getInstance()){
            if (Scene.getInstance().gameObjectArrayList != null){
                for (int i = 0; i < Scene.getInstance().gameObjectArrayList.size(); i++){
                    Scene.getInstance().gameObjectArrayList.get(i).Update();
                }
            }
            Scene.getInstance().Clear();
        }

    }

    /**游戏界面渲染*/
    private void viewRender(Canvas canvas){
        /*设置背景颜色*/
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), mPaint);
        /*更新渲染视图部件*/
        synchronized (Renderer.rendererArrayList) {
            if (Renderer.rendererArrayList != null && !Renderer.clear) {
                Collections.sort(Renderer.rendererArrayList);
                for (int i = 0; i < Renderer.rendererArrayList.size(); i++) {
                    Renderer.rendererArrayList.get(i).draw(canvas, mPaint);
                }
            }
        }
    }

    private void updateInput(){
        if (Input.touchPosition != null){
            Input.touchPosition = new Vector2((float) touchX / GameViewInfo.screenW * GameViewInfo.fixedW,
                    (float) touchY / GameViewInfo.screenH * GameViewInfo.fixedH);
        }
        Input.touching = touching;
        Input.touchDown = false;
        Input.touchUp = false;
        if (touchDown){
            Input.touchDown = true;
            touchDown = false;
        }
        if (touchUp){
            Input.touchUp = true;
            touchUp = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        touchX = (int) event.getX();
        touchY = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchDown = true;
                touching = true;
                System.out.println("(" + touchX + "," + touchY + ")");
                return true;
            case MotionEvent.ACTION_MOVE:
                touching = true;
                return true;
            case MotionEvent.ACTION_UP:
                touchUp = true;
                touching = false;
                return true;
                default:
        }
        /*表示此View拦截处理触摸事件*/
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed, left, top, right, bottom);
        screenWidth = getWidth();
        System.out.println("screen width is:" + screenWidth);
        /*以宽度为基准，固定长宽比*/
        screenHeight = (int)((float) screenWidth / viewWidth * viewHeight);
        mSurfaceHolder.setFixedSize(screenWidth,screenHeight);
    }

    /**播放发牌动画*/
    public void playDealCardsAnimation(){
        Scene.getInstance().playDealCardsAnimation();
    }
    /**更新指定玩家手牌(数目/手牌)*/
    public void updatePlayerCard(Player pPlayer){
        Scene.getInstance().updatePlayerCard(pPlayer);
    }
    /**游戏结束，展示游戏结果*/
    public void showGameResult(){
        isGameOver = true;
        Scene.getInstance().gameObjectArrayList.clear();
        Renderer.rendererArrayList.clear();
        if (true){
            Bitmap bitmap = BitmapFactory.decodeResource(surfaceContext.getResources(), R.drawable.win);
            mCanvas.drawBitmap(bitmap, GameViewInfo.centerW, GameViewInfo.centerH,mPaint);
        }
    }
    /**显示指定的玩家动作(出牌/不出)*/
    public void showPlayerAction(PlayerAction pAction){
        Scene.getInstance().showPlayerAction(pAction);
    }
    /**轮到某玩家(轮到人类玩家则显示操作按钮，否则隐藏)*/
    public void showTurn(Player pPlayer){
        Scene.getInstance().showTurn(pPlayer);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();
        mIsDrawing = true;
        setZOrderOnTop(false);
        setZOrderMediaOverlay(false);
        /*开启游戏线程*/
        mGameThread = new GameThread(getHolder(), this);
        mGameThread.setRunning(true);
        mGameThread.start();

        Scene.getInstance().startNewScene();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        /*父线程会等待 mGameThread 子线程结束后再继续运行*/
        mGameThread.setRunning(false);
        boolean retry = true;
        while (retry){
            try {
                mGameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mIsDrawing = false;
        surfaceContext = null;
    }

    /**游戏线程*/
    class GameThread extends Thread{
        private SurfaceHolder surfaceHolder;
        private GameView gameView;
        private boolean isRunning = false;
        private boolean isPause = false;

        private GameThread(SurfaceHolder surfaceHolder, GameView gameView){
            this.surfaceHolder = surfaceHolder;
            this.gameView = gameView;
        }

        /**暂停线程*/
        public synchronized void onThreadPause() {
            isPause = true;
        }

        /**线程继续运行*/
        public synchronized void onThreadResume() {
            isPause = false;
            this.notify();
        }

        public boolean isRunning() {
            return isRunning;
        }

        /**设置游戏线程运行状态*/
        public void setRunning(boolean run){
            this.isRunning = run;
        }

        @Override
        public void run(){
            while (isRunning){
                if (isPause){
                    Thread.yield();
                } else {
                    gameLogic();
                    /*取得更新之前的时间*/
                    long startTime = System.currentTimeMillis();
                    try {
                        /*获取Canvas对象进行绘制*/
                        mCanvas = surfaceHolder.lockCanvas();
                        if (mCanvas != null) {
                            /*加上线程安全锁*/
                            synchronized (surfaceHolder) {
                                gameView.viewRender(mCanvas);
                            }
                        }
                    } finally {
                        if (mCanvas != null) {
                            /*对画布内容进行提交*/
                            surfaceHolder.unlockCanvasAndPost(mCanvas);
                        }
                    }
                    /*取得更新之后的时间*/
                    long endTime = System.currentTimeMillis();
                    /*计算出一次更新的毫秒数*/
                    int diffTime = (int) (endTime - startTime);
                    /*确保每次更新时间为固定帧数frameDeltaTime*/
                    while (diffTime < frameDeltaTime) {
                        diffTime = (int) (System.currentTimeMillis() - startTime);
                        /*线程等待*/
                        Thread.yield();
                    }
                }
            }
        }
    }
}


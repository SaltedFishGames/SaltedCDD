package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cn.saltedfish.saltedcdd.R;

public class PlayerInfoGameView extends BaseGameView {
    protected SpiritGameView mAvatarView;

    protected SpiritGameView mAvatarHighlightView;

    protected String mPlayerNickname = "";

    protected NicknameShowType mNicknameShowType = NicknameShowType.LR;

    protected int mNicknamePosX;
    protected int mNicknamePosY;

    protected static final int sAvatarWidth = 96;
    protected static final int sAvatarHeight = 96;

    protected static final int sNicknameSize = 36;

    protected static Paint sNicknamePaint;

    static
    {
        sNicknamePaint = new Paint();
        sNicknamePaint.setColor(Color.WHITE);
        sNicknamePaint.setAntiAlias(true);
        sNicknamePaint.setTextSize(sNicknameSize);
    }

    public PlayerInfoGameView(int pAvatarX, int pAvatarY, int pNicknameX, int pNicknameY, NicknameShowType pNicknameShowType)
    {
        mAvatarView = new SpiritGameView();
        mAvatarView.setPosX(pAvatarX);
        mAvatarView.setPosY(pAvatarY);

        mAvatarView.setWidth(sAvatarWidth);
        mAvatarView.setHeight(sAvatarHeight);

        mAvatarHighlightView = new SpiritGameView();
        mAvatarHighlightView.setPosX(pAvatarX);
        mAvatarHighlightView.setPosY(pAvatarY);

        mAvatarHighlightView.setVisible(false);
        mAvatarHighlightView.setWidth(sAvatarWidth);
        mAvatarHighlightView.setHeight(sAvatarHeight);

        mNicknamePosX = pNicknameX;
        mNicknamePosY = pNicknameY;

        mNicknameShowType = pNicknameShowType;

        mAvatarHighlightView.setBitmap(GameBoardView.getInstance().getBitmapFactory().getBitmap(R.drawable.icon_head_action));
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        mAvatarView.onDraw(pCanvas);
        mAvatarHighlightView.onDraw(pCanvas);

        drawNickname(pCanvas);
    }

    protected void drawNickname(Canvas pCanvas)
    {
        switch (mNicknameShowType)
        {
            case LR:
                sNicknamePaint.setTextAlign(Paint.Align.LEFT);
                break;
            case RL:
                sNicknamePaint.setTextAlign(Paint.Align.RIGHT);
                break;
            case CLR:
                sNicknamePaint.setTextAlign(Paint.Align.CENTER);
                break;
        }
        pCanvas.drawText(
                mPlayerNickname,
                mNicknamePosX,
                mNicknamePosY,
                sNicknamePaint
        );
    }

    public void setPlayerNickname(String pPlayerNickname)
    {
        mPlayerNickname = pPlayerNickname;
    }

    public void setHighlight(boolean pHighlight)
    {
        mAvatarHighlightView.setVisible(pHighlight);
    }

    public void setAvatar(AvatarType pAvatar)
    {
        int resId = 0;
        switch (pAvatar)
        {
            case Player:
                resId = R.drawable.icon_head;
                break;
            case Robot:
                resId = R.drawable.icon_robot_head_right;
                break;
            case RobotLeft:
                resId = R.drawable.icon_robot_head_left;
                break;
            case RobotRight:
                resId = R.drawable.icon_robot_head_right;
                break;
            default:
                return;
        }
        mAvatarView.setBitmap(GameBoardView.getInstance().getBitmapFactory().getBitmap(resId));
    }

    public enum NicknameShowType
    {
        LR,
        RL,
        CUD,
        CLR
    }

    public enum AvatarType
    {
        Player,
        Robot,
        RobotLeft,
        RobotRight
    }

    public interface ISwitchAutoPlayListener
    {
        void setAutoPlay(boolean pAutoPlay);
    }
}

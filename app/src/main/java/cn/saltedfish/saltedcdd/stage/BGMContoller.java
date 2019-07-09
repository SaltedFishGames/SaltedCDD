package cn.saltedfish.saltedcdd.stage;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

import cn.saltedfish.saltedcdd.data.Config;

public class BGMContoller {
    private static BGMContoller sInstance;

    private MediaPlayer mMediaPlayer;

    private static final String CommonBGMFileName = "cdd_bgm.mp3";

    private boolean mIsPrepared = false;

    private BGMContoller()
    {
        sInstance = this;
        mMediaPlayer = new MediaPlayer();

        setVolume(Config.getBGMVolume());
    }

    public static BGMContoller getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BGMContoller();
        }
        return sInstance;
    }

    public void play()
    {
        if (!mIsPrepared)
        {
            mMediaPlayer.setLooping(true);
            try {
                AssetFileDescriptor fd = GameApplication.getAppContext().getAssets().openFd(CommonBGMFileName);
                mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                mMediaPlayer.prepare();
                mIsPrepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mMediaPlayer.start();
    }

    public void pause()
    {
        mMediaPlayer.pause();
    }

    public void setVolume(float volume)
    {
        mMediaPlayer.setVolume(volume, volume);
    }
}

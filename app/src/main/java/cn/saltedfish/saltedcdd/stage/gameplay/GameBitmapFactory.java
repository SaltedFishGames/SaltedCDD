package cn.saltedfish.saltedcdd.stage.gameplay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

public class GameBitmapFactory {
    protected SparseArray<WeakReference<Bitmap>> mBitmapCache = new SparseArray<>();

    protected Resources mResources;

    public GameBitmapFactory(Resources pResources)
    {
        mResources = pResources;
    }

    public Bitmap getBitmap(int pResId)
    {
        WeakReference<Bitmap> cached = mBitmapCache.get(pResId);
        if (cached != null)
        {
            return cached.get();
        }
        else
        {
            Bitmap bitmap = BitmapFactory.decodeResource(mResources, pResId);
            mBitmapCache.put(pResId, new WeakReference<>(bitmap));
            return bitmap;
        }
    }

    public void clear()
    {
        for (int i = 0; i < mBitmapCache.size(); i++)
        {
            int key = mBitmapCache.keyAt(i);
            WeakReference<Bitmap> obj = mBitmapCache.get(key);
            if (obj != null && obj.get() != null)
            {
                obj.get().recycle();
            }
        }
    }
}

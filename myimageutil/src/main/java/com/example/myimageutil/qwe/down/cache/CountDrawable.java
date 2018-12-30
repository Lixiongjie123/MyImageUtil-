package com.example.myimageutil.qwe.down.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDrawable extends BitmapDrawable implements Count{
    private AtomicInteger mReferenceCount;
    private AtomicBoolean isRecycle = new AtomicBoolean(false);
    private final BitmapPool bitmapPool = BitmapPool.getInstance();

    public CountDrawable(Bitmap bitmap){
        super(bitmap);
        mReferenceCount = new AtomicInteger(0);
    }

    @Override
    public void retain() {
        mReferenceCount.incrementAndGet();
    }

    @Override
    public void recycle() {
        if (!isRecycle.get()){
            mReferenceCount.decrementAndGet();
            Bitmap bitmap = getBitmap();
            Log.i("CountDrawable", "reference count: " + mReferenceCount);
            if (mReferenceCount.get() < 0){
                throw new IllegalStateException("reference to bitmap can't smaller than 0");
            } else if (mReferenceCount.get() == 0){
                isRecycle.set(true);
                if (bitmapPool.isRecycled){
                    bitmap.recycle();
                } else {
                    //回收内存
                    Log.i("CountDrawable", "recycle");
                    bitmapPool.putReuseBitmap(bitmap);
                }
            }
        }
    }
}

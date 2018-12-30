package com.example.myimageutil.qwe.down.cache;

import android.content.Context;

/**
 * 两层缓存的缓存实现
 */

public class DoubleCache implements ImageCache{
    private final DiskCache diskCache ;
    private final DecoratorLruCache lruCache = new DecoratorLruCache();

    public DoubleCache(Context context) {
        diskCache = DiskCache.getInstance(context);
    }

    @Override
    public void put(final String key, final CountDrawable drawable) {
        diskCache.put(key,drawable);
        lruCache.put(key,drawable);
    }

    @Override
    public CountDrawable get(String url, int width, int height) {
        CountDrawable drawble = lruCache.get(url,width,height);
        if (drawble == null){
            drawble = diskCache.get(url,width,height);
            carryToMermoy(url,drawble);
        }
        return  drawble;

    }

    /**
     * 把从硬盘中的缓存移到内存中
     * @param url
     * @param drawble
     */
    private void carryToMermoy(String url, CountDrawable drawble) {
        if (drawble != null){
            lruCache.put(url,drawble);
        }
    }

    @Override
    public void remove(String key) {
        diskCache.remove(key);
        lruCache.remove(key);
    }
}

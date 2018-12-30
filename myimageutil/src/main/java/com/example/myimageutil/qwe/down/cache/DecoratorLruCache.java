package com.example.myimageutil.qwe.down.cache;

import android.graphics.Bitmap;

public class DecoratorLruCache implements ImageCache {

    private  static  final  BitmapPoolLruCache cache;


    static {
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        cache = new BitmapPoolLruCache(cacheSize){
            @Override
            protected int sizeOf(String key, CountDrawable value) {
                Bitmap bitmap = value.getBitmap();
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String key, CountDrawable drawable) {
        cache.put(key,drawable);
    }

    @Override
    public CountDrawable get(String url, int width, int height) {
        return cache.get(url);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

}

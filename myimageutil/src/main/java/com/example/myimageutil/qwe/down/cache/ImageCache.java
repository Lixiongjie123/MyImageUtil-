package com.example.myimageutil.qwe.down.cache;

public interface ImageCache {
    void put(String key, CountDrawable drawable);
    CountDrawable get(String url, int width, int height);
    void  remove(String key);
}

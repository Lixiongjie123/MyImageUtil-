package com.example.myimageutil.qwe.down.cache;

public class NoCache implements ImageCache {
    @Override
    public void put(String key, CountDrawable drawable) {

    }

    @Override
    public CountDrawable get(String url, int width, int height) {
        return null;
    }

    @Override
    public void remove(String key) {

    }
}

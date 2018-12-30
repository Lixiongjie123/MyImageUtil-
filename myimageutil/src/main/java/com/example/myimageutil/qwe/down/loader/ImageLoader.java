package com.example.myimageutil.qwe.down.loader;

import com.example.myimageutil.qwe.down.request.BitmapRequest;

/**
 * 策略模式实现不同渠道的下载方式
 */
public  interface ImageLoader {
    void loadImage(BitmapRequest request);
}

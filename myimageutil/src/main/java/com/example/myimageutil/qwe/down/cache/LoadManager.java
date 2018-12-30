package com.example.myimageutil.qwe.down.cache;

import com.example.myimageutil.qwe.down.loader.ImageLoader;
import com.example.myimageutil.qwe.down.request.BitmapRequest;

/**
 * 分配类
 */
public class LoadManager {



    private LoadManager(){

    }

    private static  class InstanceHolder {
        private  static  final  LoadManager instance = new LoadManager();
    }

    public static LoadManager getSingleton(){
        return  InstanceHolder.instance;
    }

    public void  loadImageView(ImageLoader loader, BitmapRequest bitmapRequest){
        loader.loadImage(bitmapRequest);
    }

}

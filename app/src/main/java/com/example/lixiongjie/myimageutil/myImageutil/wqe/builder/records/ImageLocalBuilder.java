package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalListener;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.ImageLocalProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.AlbumProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.CameraProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.BaseCrop;


public class ImageLocalBuilder {
    protected boolean isCrop = false;
    protected boolean isAblum = false;
    protected boolean isCamera = false;
    protected boolean isListener = false;
    protected ImageLocalProvider imageProvider;
    protected int cutX = 200 ;
    protected int cutY = 200 ;
    protected Activity activity ;
    protected Fragment fragment;
    protected ImageLocalListener imageLocalListener;

    public  ImageLocalBuilder crop(BaseCrop crop, int cutX , int cutY){
        //TODO 完成剪切
        return  this;
    }



    public  ImageLocalBuilder camera() throws IllegalAccessException {
            imageProvider = new CameraProvider();
            isCamera = true;
            return this;
    }


    public ImageLocalBuilder  ablum() throws IllegalAccessException {
            imageProvider = new AlbumProvider();
            isCamera = true;
        return this;
    }

    public ImageLocalBuilder with(Activity activity ){
        this.activity = activity;
        return this;
    }

    public ImageLocalBuilder with(Fragment fragment){
        this.fragment = fragment;
        return this;
    }

    public ImageLocalBuilder setBitmapListener(ImageLocalListener imageLocalListener){
        isListener = true;
        this.imageLocalListener = imageLocalListener;
        return this;
    }

}

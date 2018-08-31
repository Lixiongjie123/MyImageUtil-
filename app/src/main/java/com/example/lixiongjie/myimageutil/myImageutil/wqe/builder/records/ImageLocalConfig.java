package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalListener;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.ImageLocalProvider;

public  class ImageLocalConfig implements ImageRecord {
    Activity activity;
    Fragment fragment;
    int cutX;
    int cutY;
    boolean isCrop;
    boolean isCamera;
    public  boolean isListener;
    boolean isAlbum;
    public ImageLocalListener imageLocalListener;
    ImageLocalProvider imageLocalProvider;


    private ImageLocalBuilder builder;


    public ImageLocalConfig(ImageLocalBuilder imageLocalBuilder ) throws IllegalAccessException {
        builder = imageLocalBuilder;
        this.checkAndInitParams();
    }

    @Override
    public void checkAndInitParams() throws IllegalAccessException {

        //TODO 检查传入的值是不是异常或者不合法

        isCamera = this.builder.isCamera;
        isCrop = this.builder.isCrop;
        isAlbum  =  this.builder.isAblum;

        if (isAlbum == isCamera){
            throw  new IllegalAccessException("Only one can be selected in photography and gallery") ;
        }

        if (isCrop){
            cutY = this.builder.cutY;
            cutX = this.builder.cutX;
            if (cutX <=0 && cutY <= 0){
                throw new IllegalArgumentException();
            }
        }


    }
}

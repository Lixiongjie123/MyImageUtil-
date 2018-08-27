package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalProvider;

public  class ImageLocalConfig implements ImageRecord {
    Activity activity;
    Fragment fragment;
    int cutX;
    int cutY;
    boolean isCrop;
    boolean isCamera;
    boolean isListener;
    boolean isAlbum;
    ImageLocalProvider imageLocalProvider;


    private ImageLocalBuilder builder;
    public ImageLocalConfig(ImageLocalBuilder imageLocalBuilder ) throws IllegalAccessException {
        builder = imageLocalBuilder;
        this.checkAndInitParams();
    }

    @Override
    public void checkAndInitParams() throws IllegalAccessException {
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

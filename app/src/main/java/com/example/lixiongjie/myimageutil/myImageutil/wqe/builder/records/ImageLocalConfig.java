package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalListener;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.ImageLocalProvider;

public  class ImageLocalConfig implements ImageRecord {

    public Activity activity;
    public Fragment fragment;

    int cutX;
    int cutY;
    boolean isCrop;

    private boolean isCamera;
    public  boolean isListener;
    private   boolean isAlbum;
    public ImageLocalListener imageLocalListener;
    public  ImageLocalProvider imageLocalProvider;
    public Context context;

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
        isAlbum  =  this.builder.isAlbum;
        this.imageLocalProvider = this.builder.imageProvider;

        if (!this.builder.isAlbum && !this.builder.isCamera){
            throw  new IllegalAccessError("Must choose an image source ，like camera() or Album() ") ;
        }else if (isAlbum && isCamera){
            throw new IllegalAccessError("Only one can be selected in photography and gallery");
        }else {
            this.activity = this.builder.activity;
            this.fragment = this.builder.fragment;
            if (this.builder.activity != null && this.builder.fragment != null){
                throw new IllegalAccessError( "Cannot pass in two contexts at once");
            }else if (this.builder.activity == null && this.builder.fragment == null){
                throw new IllegalAccessError("Context cannot be empty");
            }else {
                context = (Context)(this.builder.activity==null?this.builder.fragment:this.builder.activity);
                if (this.builder.isListener){
                    isListener = true;
                    imageLocalListener = this.builder.imageLocalListener;
                }
            }
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

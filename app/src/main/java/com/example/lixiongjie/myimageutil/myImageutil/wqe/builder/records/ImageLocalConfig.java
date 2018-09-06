package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalListener;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.ImageLocalProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.BaseCrop;

public  class ImageLocalConfig implements ImageRecord {

    public Activity activity;
    public Fragment fragment;

    public int cutX;
    public int cutY;
    public boolean isCrop = false;
    public BaseCrop baseCrop;

    private boolean isCamera = false;
    public  boolean isListener = false ;
    private boolean isAlbum = false;
    public ImageLocalListener imageLocalListener;
    public  ImageLocalProvider imageLocalProvider;
    public Context context;
    public  boolean isActivity = false;

    public  boolean isUriCrop =false;
    public Uri cropUri = null;

    private ImageLocalBuilder builder;


    public ImageLocalConfig(ImageLocalBuilder imageLocalBuilder )  {
        builder = imageLocalBuilder;
        this.checkAndInitParams();
    }

    @Override
    public void checkAndInitParams()  {
        isCamera = this.builder.isCamera;
        isCrop = this.builder.isCrop;
        isAlbum  =  this.builder.isAlbum;
        this.imageLocalProvider = this.builder.imageProvider;
        this.isUriCrop = this.builder.isUriCrop;



        if (isUriCrop){
            if (!isCamera && !isAlbum){
                this.cropUri = this.builder.uri;

                this.activity = this.builder.activity;
                this.fragment = this.builder.fragment;
                if (this.builder.activity != null && this.builder.fragment != null){
                    throw new IllegalAccessError( "Cannot pass in two contexts at once");
                }else if (this.builder.activity == null && this.builder.fragment == null){
                    throw new IllegalAccessError("Context cannot be empty");
                }else {
                    context = (Context)(this.builder.activity==null?this.builder.fragment:this.builder.activity);
                    isActivity = this.activity != null;
                    if (this.builder.isListener){
                        isListener = true;
                        imageLocalListener = this.builder.imageLocalListener;
                    }
                }
            }else {
                throw  new IllegalAccessError("Because we called Uri to trim the image, we don't need to call the gallery or camera function.") ;
            }
        }
         else  if (!this.builder.isAlbum && !this.builder.isCamera){
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
                isActivity = this.activity != null;
                if (this.builder.isListener){
                    isListener = true;
                    imageLocalListener = this.builder.imageLocalListener;
                    isListener = true;

                }
            }
        }

        //TODO 检查传入的值是不是异常或者不合法
        if (isCrop){
            this.baseCrop = this.builder.baseCrop;
            cutY = this.builder.cutY;
            cutX = this.builder.cutX;
            if (cutX <=0 && cutY <= 0){
                throw new IllegalAccessError("");
            }
        }
    }
}

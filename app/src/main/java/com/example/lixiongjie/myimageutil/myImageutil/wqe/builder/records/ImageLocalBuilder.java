package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalListener;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.CropProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.ImageLocalProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.AlbumProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers.CameraProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.BaseCrop;


public class ImageLocalBuilder {
    protected boolean isCrop = false;
    protected boolean isAlbum = false;
    protected boolean isCamera = false;
    protected boolean isListener = false;
    protected ImageLocalProvider imageProvider;
    protected int cutX = 200 ;
    protected int cutY = 200 ;
    protected Activity activity ;
    protected Fragment fragment;
    protected ImageLocalListener imageLocalListener;
    protected BaseCrop baseCrop;
    protected  boolean isUriCrop;
    protected  Uri uri ;
    public  ImageLocalBuilder crop(int cutX , int cutY){
        //TODO 完成剪切
        this.baseCrop = new CropProvider();
        this.isCrop = true;
        this.cutX = cutX;
        this.cutY = cutY;
        return  this;
    }

    public  ImageLocalBuilder crop(Uri uri , int cutX ,int cutY){
        this.baseCrop = new CropProvider();
        this.cutX = cutX;
        this.cutY = cutY;
        this.uri = uri;
        this.isCrop = true;
        this.isUriCrop = true;
        return  this;
    }

    public  ImageLocalBuilder camera()  {
            imageProvider = new CameraProvider();
            isCamera = true;
            return this;
    }


    public ImageLocalBuilder album()  {
            imageProvider = new AlbumProvider();
            isAlbum = true;
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

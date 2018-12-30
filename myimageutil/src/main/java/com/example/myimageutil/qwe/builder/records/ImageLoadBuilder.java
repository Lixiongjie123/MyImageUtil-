package com.example.myimageutil.qwe.builder.records;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.example.myimageutil.qwe.down.cache.ImageCache;

public class ImageLoadBuilder {
    protected boolean isDisk  = false;
    protected boolean isLruCache  = false;
    protected boolean isDoubleCache  = false;
    protected boolean isNocache  = false;
    protected boolean isDIYCache  = false;
    protected ImageView target;
    protected int holderId = -1;
    protected int errorId = -1;
    protected ImageCache cache;
    protected String uri;
    protected Context context;
    protected int requestWidth = -1 ;
    protected  int requestHeight = -1;


    public ImageLoadBuilder() {
    }


    public  ImageLoadBuilder with(Context context)
    {
        this.context = context;
        return this;
    }

    public ImageLoadBuilder load(String uri){
        this.uri = uri;
        return this;
    }

    public ImageLoadBuilder into(ImageView imageview){
        this.target = imageview;
        return this;
    }

    public ImageLoadBuilder setDisk(){
        this.isDisk = true;
        return this;
    }

    public ImageLoadBuilder setCache(){
        this.isLruCache = true;
        return this;
    }

    public ImageLoadBuilder setDoubleCache(){
        this.isDoubleCache = true;
        return  this;
    }

    public ImageLoadBuilder setNocache(){
        this.isNocache = true;
        return  this;
    }

    public ImageLoadBuilder setDIYChache(ImageCache imageCache){
        this.isDIYCache = true;
        this.cache = imageCache;

        return this;
    }

    public ImageLoadBuilder setHolderPlaceId(@DrawableRes int holderId){
        this.holderId = holderId;
        return this;
    }

    public ImageLoadBuilder setEorror(@DrawableRes int errorId){
        this.errorId = errorId;
        return this;
    }
    public ImageLoadBuilder Resize(int width , int height){
        this.requestWidth = width;
        this.requestHeight = height;
        return  this;
    }


}


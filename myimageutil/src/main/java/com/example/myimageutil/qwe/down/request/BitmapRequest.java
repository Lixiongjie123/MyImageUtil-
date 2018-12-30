package com.example.myimageutil.qwe.down.request;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.myimageutil.qwe.builder.records.ImageLoadConfig;
import com.example.myimageutil.qwe.down.GlobalLoadImageConfig;
import com.example.myimageutil.qwe.down.cache.ImageCache;
import com.example.myimageutil.qwe.down.policy.ImageLoadPolicy;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BitmapRequest implements Comparable<BitmapRequest> {


  private final  ImageLoadConfig config;

  public  String uri;
  private Reference<ImageView> imageViewReference;
  private ImageLoadPolicy policy;
  public int requestID;
  public ImageCache imageCache;
  public  int placeHolderID;
  public  int errorID;
  public  int requestWidth;
  public  int requestHeight;


    public BitmapRequest(ImageLoadConfig config) {
        this.config = config;
        init();
    }

    private void init() {
        this.uri = config.uri;
        config.target.setTag(uri);
        //弱引用实现防止内存泄漏
        this.imageViewReference = new WeakReference<>(config.target);
        this.imageCache = config.cache;
        this.errorID = config.errorId;
        this.placeHolderID = config.holderId;
        this.requestID = config.requestID;

        //设置插入队列顺序
        this.policy = GlobalLoadImageConfig.getInstance().getImageLoadPolicy();

    }

    //TODO 实现自适应宽高


    public ImageView getImageView(){
        return imageViewReference.get();
    }

    /**
     * 实现请求的比较
     * @param o 另外一个请求
     * @return
     */
    @Override
    public int compareTo(@NonNull BitmapRequest o) {
        return policy.compare(this,o);
    }


    /**
     * 重写equal的三部曲 1.地址 2.类型 3.属性比较
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitmapRequest other = (BitmapRequest) obj;
        if (uri == null) {
            if (other.uri != null)
                return false;
        } else if (!uri.equals(other.uri))
            return false;
        if (imageViewReference == null) {
            if (other.imageViewReference != null)
                return false;
        } else if (!imageViewReference.get().equals(other.imageViewReference.get())){
            return false;
        }else if (requestID != other.requestID) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        result = prime * result + ((imageViewReference == null) ? 0 : imageViewReference.get().hashCode());
        result = prime * result + requestID;
        return result;
    }


}

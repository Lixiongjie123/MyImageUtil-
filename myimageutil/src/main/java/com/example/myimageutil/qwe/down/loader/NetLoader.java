package com.example.myimageutil.qwe.down.loader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.myimageutil.qwe.down.cache.CountDrawable;
import com.example.myimageutil.qwe.down.execute.MapDrawable;
import com.example.myimageutil.qwe.down.request.BitmapRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;


public class NetLoader implements ImageLoader{
    private ThreadPoolExecutor downLoadExacter;
    private Future<CountDrawable> future;
    private CountDrawable countDrawable;
    private  UICallback uiCallback;

    private Thread callbackThread;

    //实现乱序的解决
    private final Map<BitmapCallBack,Future> container;
    public NetLoader(ThreadPoolExecutor downLoadExacter) {
        this.downLoadExacter = downLoadExacter;
        container = new HashMap<>();
    }

    @Override
    public void loadImage(final  BitmapRequest request) {
        uiCallback =  new UICallback(request);
        checkBeforeTask(request.uri ,request.getImageView());
        //TODO 乱序值得斟酌
    }

    /**
     *通过键值对实现 callable 来判断url是否相同，从而决定是否存在防止RecycleView加载乱序的问题
     *
     */
    private void checkBeforeTask(String uri, ImageView imageView) {
       Map<BitmapCallBack,Future>  futureMap = getContainer(imageView);
    }

    private Map<BitmapCallBack, Future> getContainer(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof MapDrawable){
            return  ((MapDrawable)drawable).getContainerMap();
        }else if (drawable instanceof CountDrawable){
            ((CountDrawable) drawable).recycle();
        }
        return null;
    }

}

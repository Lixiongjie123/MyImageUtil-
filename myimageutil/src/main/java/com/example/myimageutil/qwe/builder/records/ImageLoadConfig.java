package com.example.myimageutil.qwe.builder.records;

import android.content.Context;
import android.widget.ImageView;

import com.example.myimageutil.qwe.down.cache.DecoratorLruCache;
import com.example.myimageutil.qwe.down.cache.DiskCache;
import com.example.myimageutil.qwe.down.cache.DoubleCache;
import com.example.myimageutil.qwe.down.cache.ImageCache;
import com.example.myimageutil.qwe.down.cache.NoCache;

import java.util.concurrent.atomic.AtomicInteger;

public class ImageLoadConfig implements ImageRecord {
    private  ImageLoadBuilder builder;
    private Context context;

    public ImageView target;
    public int holderId = -1;
    public int errorId = -1;
    public  String uri;
    public ImageCache cache;
    public  int requestWidth = -1 ;
    public  int requestHeight = -1;


    /**
     * 线程安全的int，每一次自增，生成的 int 值作为当前请求的ID
     */
    private  static final AtomicInteger requestIDGenerator = new AtomicInteger(0);
    public final int requestID;


    public ImageLoadConfig(ImageLoadBuilder builder) {
        this.builder = builder;
        checkPermission();
      checkAndInitParams();

      requestID = requestIDGenerator.decrementAndGet();

    }



    @Override
    public void checkAndInitParams()  {
        // 把缓存策略遍历设置为局部变量 优化内存

        boolean isNocache =false;
        boolean isDIYCache = false;
        boolean isDoubleCache = false;
        boolean isDisk = false;
        boolean isLruCache = false;


        context = builder.context;

        if (context == null){
            throw new IllegalStateException("with() method must be invoked !!!!!!!!");
        }

        if (builder.target == null) throw new IllegalStateException("ImageView target must invoke");
        else target = builder.target;

        if (builder.uri == null)
            throw new IllegalArgumentException("uri should not be null!");
        else if (uri.split("://")[0].equals("http") || uri.split("://")[0].equals("https"))
            uri = builder.uri;
        else throw new IllegalArgumentException("uri must support start of 'http' or 'https'");


        //缓存确定
        isDisk = builder.isDisk;
        isDIYCache = builder.isDIYCache;
        isDoubleCache = builder.isDoubleCache;
        isLruCache = builder.isLruCache;
        isNocache = builder.isNocache;

        if (isDisk && !isDIYCache && !isDoubleCache && !isLruCache && !isNocache){
            cache = DiskCache.getInstance(context);
        }else if (!isDisk && isDIYCache && !isDoubleCache && !isLruCache && !isNocache){
            cache = builder.cache;
        }else if (!isDisk && !isDIYCache && isDoubleCache && !isLruCache && !isNocache){
            cache = new DoubleCache(context);
        }else if (!isDisk && !isDIYCache && !isDoubleCache && !isLruCache && !isNocache){
            cache = new DoubleCache(context);
        }else if (!isDisk && !isDIYCache && !isDoubleCache && isLruCache && !isNocache){
            cache = new DecoratorLruCache();
        }else if (!isDisk && !isDIYCache && !isDoubleCache && !isLruCache && isNocache){
            cache = new NoCache();
        }else {
            throw new IllegalStateException("cache must chose only one");
        }

        this.holderId = builder.holderId;
        this.errorId = builder.errorId;
        this.requestWidth = builder.requestWidth;
        this.requestHeight = builder.requestHeight;

    }

    @Override
    public void checkPermission() {
    //TODO 权限验证

    }
}

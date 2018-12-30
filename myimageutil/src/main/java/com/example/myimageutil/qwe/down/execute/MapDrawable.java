package com.example.myimageutil.qwe.down.execute;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.example.myimageutil.qwe.down.loader.BitmapCallBack;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Future;


/**
 * 使用一个虚引用保存的键值对，通过Callable 作为key ，保存ImageView所对应的任务的url，然后与BitmapRequest的url作
 * 比较如果不相同代表这是属于上一次的任务，所以取消掉上一次的callable.
 */
public class MapDrawable  extends BitmapDrawable{

    private  final Reference<Map<BitmapCallBack,Future>> mapReference;

    public MapDrawable(Resources res, Bitmap bitmap, Map<BitmapCallBack, Future> map) {
        super(res, bitmap);
        this.mapReference = new WeakReference<>(map);
    }

    public Map<BitmapCallBack,Future> getContainerMap(){
        return mapReference.get();
    }
}

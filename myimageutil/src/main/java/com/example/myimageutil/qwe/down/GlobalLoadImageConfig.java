package com.example.myimageutil.qwe.down;

import android.app.Application;
import android.content.Context;
import android.service.autofill.FillEventHistory;

import com.example.myimageutil.qwe.down.policy.FIFOPolicy;
import com.example.myimageutil.qwe.down.policy.ImageLoadPolicy;

/**
 * 思考良久抽象出的全局配置类
 * 主要是保存管理一些在整个APP生命周期进行全局初始化的配置。
 */
public class GlobalLoadImageConfig {

    //全局上下文
    public Context mApplicationContext;


    //加载策略，默认实现先进先加载策略
    private ImageLoadPolicy imageLoadPolicy = new FIFOPolicy();

    /**
     * 线程池的核心数
     * 网络请求是IO密集任务核心数*2
     */
    private  int threadCount = Runtime.getRuntime().availableProcessors() *2 ;

    /**
     * 单例实现
     * @return
     */
    public  static  GlobalLoadImageConfig getInstance(){
        return  InstanceHolder.globalLoadImageConfig;
    }

    private   static  class  InstanceHolder{
        private  static  final  GlobalLoadImageConfig globalLoadImageConfig= new GlobalLoadImageConfig();
    }

    /**
     * 实现的流式操作
     * @param imageLoadPolicy
     * @return
     */
    public  GlobalLoadImageConfig  setPlolicy(ImageLoadPolicy imageLoadPolicy){
        this.imageLoadPolicy = imageLoadPolicy;
        return  this;
    }

    public GlobalLoadImageConfig setThreadID (){
        this.imageLoadPolicy = imageLoadPolicy;
        return  this;
    }

    public  void init(Application application){
        this.mApplicationContext = application;
    }


    public Context getmApplicationContext() {
        return mApplicationContext;
    }


    public ImageLoadPolicy getImageLoadPolicy() {
        return imageLoadPolicy;
    }
}

package com.example.myimageutil.qwe.down.execute;

import android.util.Log;

import com.example.myimageutil.qwe.down.GlobalLoadImageConfig;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现优先级等待队列的自定义返回 Task
 * @param <CountDrawable>
 */

public class LoadTask<CountDrawable> extends FutureTask<CountDrawable> implements Comparable<LoadTask<CountDrawable>> {
    private int policy;
    private  String url;


    public LoadTask(Callable<CountDrawable> callable) {
        super(callable);

        //判断结果类callable必须实现优先级比较的接口

        if (!(callable instanceof ThreadPoolQueuePolicy)){
            throw new IllegalArgumentException("callable should be implements ThreadPoolQueuePolicy!");
        }
        policy = ((ThreadPoolQueuePolicy) callable).getPolicy();
        Log.i("LoadTask", policy + " ID 1");
    }

    public String getUrl(){
        return url;
    }




    @Override
    public int compareTo(LoadTask<CountDrawable> another) {
        Log.i("LoadTask", policy + " " + another.policy + " ID 2");
       return GlobalLoadImageConfig.getInstance().getImageLoadPolicy().compare(policy,another.policy);

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LoadTask){
            LoadTask<CountDrawable> anthor = (LoadTask<CountDrawable>) o;
            return anthor.policy == policy;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return policy * 31 + 17;
    }
}

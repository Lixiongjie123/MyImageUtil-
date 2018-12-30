package com.example.myimageutil.qwe.down.execute;

import java.util.concurrent.Callable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownLoadExacter extends ThreadPoolExecutor {

    private static final String TAG = DownLoadExacter.class.getSimpleName();

    public DownLoadExacter(int maximumPoolSize){
        this(TAG,maximumPoolSize);

    }
    public DownLoadExacter(String name , int maximumPoolSize){
        this(maximumPoolSize,maximumPoolSize,0, TimeUnit.SECONDS,new MyCostumeThreadFactory(name));
    }

    public DownLoadExacter(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new PriorityBlockingQueue<Runnable>(), threadFactory);
    }


    /**
     * 重写该方法实现一个自定义的Task，因为一般的默认的Task 会忽略掉接口 Comparable
     * 适配器模式
     * @param callable
     * @param <T>
     * @return
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new LoadTask<>(callable);
    }

    //自定义线程工厂,设置优先级和自定义name
    private static class MyCostumeThreadFactory implements ThreadFactory {

        private final String name;
        private int threadNum = 0;

        public MyCostumeThreadFactory(){
            this(TAG);
        }

        public MyCostumeThreadFactory(String name){
            this.name = name;
        }


        //这里实际传入的是FutureTask
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, name + "-" + threadNum){
                @Override
                public void run() {
                    //设置线程优先级为后台线程
                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                    super.run();
                }
            };
            threadNum++;
            return thread;
        }
    }
}

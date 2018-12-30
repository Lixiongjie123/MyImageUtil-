package com.example.myimageutil.qwe.down.cache;

import android.util.LruCache;

public class BitmapPoolLruCache  extends LruCache<String,CountDrawable>{
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public BitmapPoolLruCache(int maxSize) {
        super(maxSize);
    }

    /**
     * 在我们执行 remove 的方法的时候就会回调该方法，重写方法目的是把即将移除的 Bitmap 放入 BitmapPool中，细节实现
     * 是减少计数，判断是否为 0 决定是否放入池中。
     * @param evicted 为 true 才代表这个缓存会被回收，释放内存。
     * @param key
     * @param oldValue
     * @param newValue
     */
    @Override
    protected void entryRemoved(boolean evicted, String key, CountDrawable oldValue, CountDrawable newValue) {
        if (evicted){
            oldValue.recycle();
        }
    }
}

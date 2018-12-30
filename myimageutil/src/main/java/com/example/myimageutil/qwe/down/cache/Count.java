package com.example.myimageutil.qwe.down.cache;

public interface Count {
    // 用计数器的方式计算options 是否会被回收
    void retain();
    void  recycle();
}

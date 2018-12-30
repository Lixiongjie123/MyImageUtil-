package com.example.myimageutil.qwe.down.policy;

import com.example.myimageutil.qwe.down.request.BitmapRequest;

/**
 * 先进先出策略
 */

public class FIFOPolicy implements ImageLoadPolicy{
    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        return request1.requestID - request2.requestID;
    }

    @Override
    public int compare(int policy1, int policy2) {
        return policy1 - policy2;
    }
}

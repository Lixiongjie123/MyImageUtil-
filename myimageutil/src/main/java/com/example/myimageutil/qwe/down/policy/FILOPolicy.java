package com.example.myimageutil.qwe.down.policy;

import com.example.myimageutil.qwe.down.request.BitmapRequest;

public class FILOPolicy implements  ImageLoadPolicy{
    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        return request2.requestID - request1.requestID;
    }

    @Override
    public int compare(int policy1, int policy2) {
        return policy2 - policy1;
    }
}

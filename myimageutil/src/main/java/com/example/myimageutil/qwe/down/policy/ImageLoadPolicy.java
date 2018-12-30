package com.example.myimageutil.qwe.down.policy;

import com.example.myimageutil.qwe.down.request.BitmapRequest;

public interface ImageLoadPolicy {
    int compare(BitmapRequest request1, BitmapRequest request2);
    int compare(int policy1, int policy2);
}

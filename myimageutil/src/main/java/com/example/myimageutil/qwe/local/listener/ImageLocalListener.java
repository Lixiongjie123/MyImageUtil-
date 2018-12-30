package com.example.myimageutil.qwe.local.listener;

import android.graphics.Bitmap;
import android.net.Uri;

 public  interface ImageLocalListener {
    void setBitmapOnclickListener(Bitmap bitmap);
    void setUriOnclickListener(Uri uri);
}

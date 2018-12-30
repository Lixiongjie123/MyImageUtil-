package com.example.myimageutil.qwe.local.listener;

import android.graphics.Bitmap;
import android.net.Uri;

public abstract  class BitmapOnclickListener implements ImageLocalListener {
    @Override
    public abstract  void setBitmapOnclickListener(Bitmap bitmap) ;

    @Override
    public void setUriOnclickListener(Uri uri) {

    }
}

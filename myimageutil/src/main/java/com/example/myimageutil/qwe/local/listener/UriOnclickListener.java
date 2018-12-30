package com.example.myimageutil.qwe.local.listener;

import android.graphics.Bitmap;
import android.net.Uri;

public abstract  class UriOnclickListener implements ImageLocalListener   {
    @Override
    public void setBitmapOnclickListener(Bitmap bitmap) {

    }

    @Override
    public abstract  void setUriOnclickListener(Uri uri) ;
}

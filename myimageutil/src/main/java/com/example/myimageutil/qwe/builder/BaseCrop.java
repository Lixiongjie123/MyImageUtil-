package com.example.myimageutil.qwe.builder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;

public interface BaseCrop  {
    Intent getIntent(Context context,Uri uri, int outX , int outY);
    int getRequestCode();
    void handleActivityResult(ImageLocalConfig imageLocalConfig , Intent data );
}
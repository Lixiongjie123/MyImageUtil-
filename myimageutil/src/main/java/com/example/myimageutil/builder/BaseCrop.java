package com.example.myimageutil.wqe.builder;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;

import com.example.myimageutil.wqe.Local.providers.ImageLocalProvider;
import com.example.myimageutil.wqe.builder.records.ImageLocalConfig;

import java.io.File;

public interface BaseCrop  {
    Intent getIntent(Context context,Uri uri, int outX , int outY);
    int getRequestCode();
    void handleActivityResult(ImageLocalConfig imageLocalConfig , Intent data );
}
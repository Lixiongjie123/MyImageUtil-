package com.example.myimageutil.qwe.local.providers;

import android.content.Context;
import android.content.Intent;

import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;

import java.io.IOException;

public  interface ImageLocalProvider {
   Intent getIntent(Context context) throws IOException;
   int getRequestCode();
   void handleActivityResult(ImageLocalConfig r , Intent intent);
}

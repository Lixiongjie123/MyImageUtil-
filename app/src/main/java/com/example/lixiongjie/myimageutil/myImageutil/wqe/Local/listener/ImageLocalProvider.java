package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener;

import android.content.Intent;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

public  interface ImageLocalProvider {
   int getIntent();
   int getRequestCode();
   void handleActivityResult(ImageLocalConfig r , Intent intent);
}

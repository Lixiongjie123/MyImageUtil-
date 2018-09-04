package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.Context;
import android.content.Intent;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

public interface BaseCrop {
    Intent getIntent(Context context);
    int getReqesutCode();
    void handleActivityResult(ImageLocalConfig imageLocalConfig , Intent data );
}
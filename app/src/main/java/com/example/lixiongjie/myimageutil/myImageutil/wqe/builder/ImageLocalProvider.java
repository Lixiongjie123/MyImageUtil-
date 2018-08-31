package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

import static android.provider.MediaStore.*;
import static android.provider.MediaStore.Audio.*;
import static android.provider.MediaStore.Audio.Media.*;

public class ImageLocalProvider implements BaseProvider {

    private ImageLocalConfig imageRecord ;
    public ImageLocalProvider(ImageLocalBuilder builder) throws IllegalAccessException {
        imageRecord  =  new ImageLocalConfig(builder);
    }

    @Override
    public void execute() {
        //TODO  判断该执行哪一个Provider
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //TODO 判断该执行哪一个Provider
    }


}

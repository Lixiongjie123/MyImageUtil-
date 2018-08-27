package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.Intent;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalRecord;

public class ImageLocalProvider implements BaseProvider {

    private ImageLocalRecord imageRecord ;
    public ImageLocalProvider(ImageLocalBuilder builder) {
        imageRecord  =  new ImageLocalRecord(builder);
    }

    @Override
    public void execute() {
        //TODO 判断是不是
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}

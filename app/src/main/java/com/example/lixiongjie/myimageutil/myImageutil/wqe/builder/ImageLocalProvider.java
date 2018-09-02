package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.Intent;


import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

import java.io.IOException;


public class ImageLocalProvider implements BaseProvider {

    private ImageLocalConfig imageRecord ;
    public ImageLocalProvider(ImageLocalBuilder builder) throws IllegalAccessException {
        imageRecord  =  new ImageLocalConfig(builder);
    }

    @Override
    public void execute() throws IOException {
        if (this.imageRecord.context != null){
            this.imageRecord.activity.startActivityForResult(this.imageRecord.imageLocalProvider.
                    getIntent(this.imageRecord.context),this.imageRecord.imageLocalProvider.getRequestCode());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        this.imageRecord.imageLocalProvider.handleActivityResult(imageRecord,intent);
    }
}

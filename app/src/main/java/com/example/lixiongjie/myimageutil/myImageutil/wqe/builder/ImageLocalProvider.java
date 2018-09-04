package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.Intent;


import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class ImageLocalProvider implements BaseProvider {

    private ImageLocalConfig imageRecord ;
    public ImageLocalProvider(ImageLocalBuilder builder) throws IllegalAccessException {
        imageRecord  =  new ImageLocalConfig(builder);
    }

    @Override
    public void execute() throws IOException {
        if (this.imageRecord.context != null){
            if (this.imageRecord.activity !=null){
                this.imageRecord.activity.startActivityForResult(this.imageRecord.imageLocalProvider.
                        getIntent(this.imageRecord.context),this.imageRecord.imageLocalProvider.getRequestCode());
            }else {
                this.imageRecord.fragment.startActivityForResult(this.imageRecord.imageLocalProvider.getIntent(this.imageRecord.context),this.imageRecord.imageLocalProvider.getRequestCode());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode != RESULT_OK ){
            try {
                throw  new  IllegalAccessException("Data returned unsuccessfullyl");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return;
        }
        this.imageRecord.imageLocalProvider.handleActivityResult(imageRecord,intent);
    }
}

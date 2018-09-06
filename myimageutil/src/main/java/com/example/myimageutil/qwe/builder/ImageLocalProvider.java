package com.example.myimageutil.qwe.builder;

import android.content.Intent;


import com.example.myimageutil.qwe.builder.records.ImageLocalBuilder;
import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;

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
                if (this.imageRecord.isUriCrop) {
                    this.imageRecord.activity.startActivityForResult
                            (this.imageRecord.baseCrop.getIntent(this.imageRecord.context,this.imageRecord.cropUri,this.imageRecord.cutX,this.imageRecord.cutY)
                                    ,this.imageRecord.baseCrop.getRequestCode());
                }
                else {
                    this.imageRecord.activity.startActivityForResult(this.imageRecord.imageLocalProvider.
                            getIntent(this.imageRecord.context), this.imageRecord.imageLocalProvider.getRequestCode());
                }
            }else {
                if (this.imageRecord.isUriCrop) {
                    this.imageRecord.fragment.startActivityForResult
                            (this.imageRecord.baseCrop.getIntent(this.imageRecord.context,this.imageRecord.cropUri,this.imageRecord.cutX,this.imageRecord.cutY)
                                    ,this.imageRecord.baseCrop.getRequestCode());
                }

                else
                this.imageRecord.fragment.startActivityForResult
                        (this.imageRecord.imageLocalProvider.getIntent(this.imageRecord.context),this.imageRecord.imageLocalProvider.getRequestCode());
            }
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != RESULT_OK) {
            try {
                throw new IllegalAccessException("Data returned unsuccessfullyl");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return;
        }
        if (requestCode != 30) {
            this.imageRecord.imageLocalProvider.handleActivityResult(imageRecord, intent);
        }else {
            this.imageRecord.baseCrop.handleActivityResult(imageRecord,intent);
        }
    }
}

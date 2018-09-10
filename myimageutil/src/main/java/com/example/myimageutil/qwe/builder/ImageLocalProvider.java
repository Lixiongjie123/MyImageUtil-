package com.example.myimageutil.qwe.builder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;


import com.example.myimageutil.qwe.builder.records.ImageLocalBuilder;
import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.REQUEST_CROP_PHOTO;
import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.REQUEST_PERIMISSION;


public class ImageLocalProvider implements BaseProvider {

    private ImageLocalConfig imageRecord ;
    public ImageLocalProvider(ImageLocalBuilder builder) throws IllegalAccessException {
        imageRecord  =  new ImageLocalConfig(builder);
    }

    @Override
    public void execute() throws IOException {

     if (this.imageRecord.context != null && this.imageRecord.isGetPermissions){
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
                throw new IllegalAccessException("Data returned unsuccessfully");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return;
        }
        if (requestCode != REQUEST_CROP_PHOTO) {
            this.imageRecord.imageLocalProvider.handleActivityResult(imageRecord, intent);
        }else {
            this.imageRecord.baseCrop.handleActivityResult(imageRecord,intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == REQUEST_PERIMISSION)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                this.imageRecord.isGetPermissions = true;
            } else
            {
                // Permission Denied
                Toast.makeText(this.imageRecord.context, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

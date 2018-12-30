package com.example.myimageutil.qwe.builder.core;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.myimageutil.qwe.builder.BaseProvider;
import com.example.myimageutil.qwe.builder.factory.ImageLoadFactory;
import com.example.myimageutil.qwe.builder.factory.ImageLocalFactory;
import com.example.myimageutil.qwe.builder.records.ImageLoadBuilder;
import com.example.myimageutil.qwe.builder.records.ImageLocalBuilder;
import com.example.myimageutil.qwe.builder.factory.ImageFactory;

import java.io.IOException;

public class MyImageUtil {
    private static ImageFactory factory ;
    private BaseProvider baseProvider;
    private MyImageUtil() {
     baseProvider = factory.init();
    }

    public static MyImageUtil create(ImageLocalBuilder imageLocalBuilder){
            factory = new ImageLocalFactory(imageLocalBuilder);
            return new MyImageUtil();
    }

    public static  MyImageUtil create(ImageLoadBuilder builder){
        factory = new ImageLoadFactory(builder);
        return  new MyImageUtil();
    }

    public void  execute(){
        try {
            this.baseProvider.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  onActivityForResult(int requestCode, int resultCode, @Nullable Intent data){
     this.baseProvider.onActivityResult(requestCode,resultCode,data);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        this.baseProvider.onRequestPermissionsResult( requestCode,  permissions, grantResults);
    }
}

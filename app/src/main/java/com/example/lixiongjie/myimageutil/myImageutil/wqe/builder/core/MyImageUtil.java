package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.core;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.BaseProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.factory.ImageFactory;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.factory.ImageLocalFactory;

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
}

package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.core;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.factory.ImageFactory;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.factory.ImageLocalFactory;

public class MyImageUtil {
    private static ImageFactory factory ;

    private MyImageUtil() {

    }

    public static MyImageUtil create(ImageLocalBuilder imageLocalBuilder){
        factory = new ImageLocalFactory(imageLocalBuilder);
        return new MyImageUtil();
    }
}

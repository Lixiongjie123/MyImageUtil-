package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.factory;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.ImageLocalProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;

public class ImageLocalFactory  implements  ImageFactory{
    private ImageLocalBuilder imageLocalBuilder;
    public ImageLocalFactory(ImageLocalBuilder imageLocalBuilder) {
        this.imageLocalBuilder = imageLocalBuilder;
    }

    @Override
    public ImageLocalProvider init() {
        try {
            return new ImageLocalProvider(imageLocalBuilder);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

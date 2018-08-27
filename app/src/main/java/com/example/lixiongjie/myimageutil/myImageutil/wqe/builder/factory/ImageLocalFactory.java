package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.factory;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.BaseProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalBuilder;

public class ImageLocalFactory  implements  ImageFactory{
    private ImageLocalBuilder imageLocalBuilder;
    public ImageLocalFactory(ImageLocalBuilder imageLocalBuilder) {
        this.imageLocalBuilder = imageLocalBuilder;
    }

    @Override
    public BaseProvider init() {
        //TODO 返回提供着实现类实例
        return null;
    }
}

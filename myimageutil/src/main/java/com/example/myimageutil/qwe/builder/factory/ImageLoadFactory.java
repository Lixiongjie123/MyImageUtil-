package com.example.myimageutil.qwe.builder.factory;

import com.example.myimageutil.qwe.builder.BaseProvider;
import com.example.myimageutil.qwe.builder.ImageLoadProvider;
import com.example.myimageutil.qwe.builder.ImageLocalProvider;
import com.example.myimageutil.qwe.builder.records.ImageLoadBuilder;

public class ImageLoadFactory implements  ImageFactory{

    private ImageLoadBuilder builder ;

    public ImageLoadFactory(ImageLoadBuilder builder) {
        this.builder = builder;
    }


    @Override
    public BaseProvider init() {
        return  new ImageLoadProvider(builder);
    }
}

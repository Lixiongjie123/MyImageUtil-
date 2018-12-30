package com.example.myimageutil.qwe.builder;

import android.content.Intent;

import com.example.myimageutil.qwe.builder.records.ImageLoadBuilder;
import com.example.myimageutil.qwe.builder.records.ImageLoadConfig;
import com.example.myimageutil.qwe.down.cache.LoadManager;
import com.example.myimageutil.qwe.down.execute.DownLoadExacter;
import com.example.myimageutil.qwe.down.loader.ImageLoader;
import com.example.myimageutil.qwe.down.loader.NetLoader;
import com.example.myimageutil.qwe.down.request.BitmapRequest;

import java.util.concurrent.ThreadPoolExecutor;

public class ImageLoadProvider implements BaseProvider {

    private static final ThreadPoolExecutor exe = new DownLoadExacter("LoadProvider",Runtime.getRuntime().availableProcessors() + 1);
    private ImageLoadConfig config;
    private BitmapRequest request ;
    public ImageLoadProvider(ImageLoadBuilder builder) {
        config = new ImageLoadConfig(builder);
        request = new BitmapRequest(config);

    }

    @Override
    public void execute()  {
        //TODO 文件的访问修改到在这里执行
        ImageLoader imageLoader = new NetLoader(exe);
        LoadManager.getSingleton().loadImageView(imageLoader,request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //TODO 权限添加
    }
}

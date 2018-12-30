package com.example.myimageutil.qwe.down.loader;

import android.util.Log;

import com.example.myimageutil.qwe.down.cache.CountDrawable;
import com.example.myimageutil.qwe.down.execute.ThreadPoolQueuePolicy;
import com.example.myimageutil.qwe.down.request.BitmapRequest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import static com.example.myimageutil.qwe.util.BitmapDecode.byteTOInputStream;
import static com.example.myimageutil.qwe.util.BitmapDecode.decodeRequestBitmap;
import static com.example.myimageutil.qwe.util.BitmapDecode.inputStreamTOByte;


/**
 * Bitmap 的请求类任务实体(包装请求成可执行的实体)
 */

public class BitmapCallBack implements Callable<CountDrawable> ,ThreadPoolQueuePolicy{

    private  final BitmapRequest request;

    public BitmapCallBack(BitmapRequest request) {
        this.request = request;
    }

    @Override
    public int getPolicy() {
        return request.requestID;
    }

    @Override
    public CountDrawable call() throws Exception {
        URL url = null;
        CountDrawable drawable = null;
        InputStream inputStream = null;
        InputStream transformStream = null;

        HttpURLConnection connection = null;

        try {
            url = new URL(request.uri);
            Log.i("BitmapCallback", url + " url");
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);

            inputStream = connection.getInputStream();
            byte[] bytes = inputStreamTOByte(inputStream);

            transformStream = byteTOInputStream(bytes);
            inputStream = byteTOInputStream(bytes);

            //TODO 宽高自适应判断

            drawable = decodeRequestBitmap(inputStream, transformStream, request.requestWidth, request.requestHeight);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
            if (transformStream != null){
                transformStream.close();
            }
            if (inputStream != null){
                inputStream.close();
            }
            if (connection != null){
                connection.disconnect();
            }
        }
        return drawable;
    }
}

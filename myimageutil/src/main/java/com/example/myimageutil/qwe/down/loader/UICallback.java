package com.example.myimageutil.qwe.down.loader;

import android.app.DownloadManager;
import android.os.Looper;
import android.os.Message;

import com.example.myimageutil.qwe.down.cache.CountDrawable;
import com.example.myimageutil.qwe.down.cache.ImageCache;
import com.example.myimageutil.qwe.down.request.BitmapRequest;


/**
 * 实现UI的更新，主线程回调
 */

public class UICallback  {


    private static final int LOAD = 1;
    private static final int LOADING = 2;
    private static final int ERROR = 3;
    private static final int LOAD_CACHE = 4;


    private  final BitmapRequest request;
    private  final ImageCache cache;
    private  final H mainHandler;

    /**
     * 实现UI更新的几个状态
     *----------------------------------------
     */


    /**
     * 加载完后的状态
     */
    public void  loadedImageView(CountDrawable countDrawable){
        //TODO 实现缓存
        Message message = Message.obtain();
        message.what = LOAD;
        message.obj = countDrawable;
        mainHandler.sendMessage(message);
    }

    /**
     *
     * @param request
     */




    public UICallback(BitmapRequest request) {
        this.request = request;
        this.cache = request.imageCache;
        mainHandler = new H(request);
    }

    private final class H  extends android.os.Handler {
        private  BitmapRequest request ;

        public H(BitmapRequest request) {
            //绑定主线程
            super(Looper.getMainLooper());
            this.request = request;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}

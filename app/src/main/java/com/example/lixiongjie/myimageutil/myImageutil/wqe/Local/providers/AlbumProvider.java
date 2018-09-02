package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.util.FileUtils;

import static android.support.constraint.Constraints.TAG;

public class AlbumProvider implements ImageLocalProvider {
    private Context context;


    @Override
    public Intent getIntent(Context context) {
        this.context = context;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public int getRequestCode() {
        return 10;

    }

    @Override
    public void handleActivityResult(ImageLocalConfig r, Intent intent) {
        if (r.isListener){
            Uri data = intent.getData();
            String pathByUri = FileUtils.getFilePathByUri(context, data);
            Bitmap backBitmap = BitmapFactory.decodeFile(pathByUri);
            if (backBitmap == null){
                Log.d("123", "handleActivityResult: 111");
            }
            r.imageLocalListener.setBitmapOnclickListener(backBitmap);
        }
    }
}

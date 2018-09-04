package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.util.FileUtils;

import java.io.FileNotFoundException;


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
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(data));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            String pathByUri = FileUtils.getFilePathByUri(context, data);
//            Bitmap backBitmap = BitmapFactory.decodeFile(pathByUri);
            r.imageLocalListener.setBitmapOnclickListener(bitmap);
        }
    }
}

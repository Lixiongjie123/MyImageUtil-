package com.example.myimageutil.qwe.local.providers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;

import java.io.FileNotFoundException;

import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.REQUEST_OPEN_GALLERY;


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
        return REQUEST_OPEN_GALLERY;

    }

    @Override
    public void handleActivityResult(ImageLocalConfig r, Intent intent) {
        if (!r.isCrop) {
            if (r.isListener) {
                Uri data = intent.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(data));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

//            Bitmap backBitmap = BitmapFactory.decodeFile(pathByUri);
                r.imageLocalListener.setBitmapOnclickListener(bitmap);
                r.imageLocalListener.setUriOnclickListener(data);
            }
        }else
            if (r.isActivity){
            r.activity.startActivityForResult(r.baseCrop.getIntent(r.context, intent.getData(),r.cutX,r.cutY),r.baseCrop.getRequestCode());
            }else {
                r.fragment.startActivityForResult(r.baseCrop.getIntent(r.context,intent.getData(),r.cutX,r.cutY),r.baseCrop.getRequestCode());
            }
    }
}

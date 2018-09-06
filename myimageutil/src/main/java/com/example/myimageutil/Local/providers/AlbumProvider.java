package com.example.myimageutil.wqe.Local.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.myimageutil.wqe.builder.records.ImageLocalConfig;
import com.example.myimageutil.wqe.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static android.support.constraint.Constraints.TAG;


public class AlbumProvider implements ImageLocalProvider {
    private Context context;
    private  String pathByUri;

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
        if (!r.isCrop) {
            if (r.isListener) {
                Uri data = intent.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(data));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "handleActivityResult: "+data +"!"+bitmap+"!"+data);
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

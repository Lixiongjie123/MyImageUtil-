package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.BaseCrop;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CropProvider implements BaseCrop {
    private Context context;
    File cropImageFile;
    Uri uri ;
    private  int outX;
    private int outY;

    public CropProvider(int outX, int outY) {
        this.outX = outX;
        this.outY = outY;
    }

    @Override
    public Intent getIntent(Context context ) {
        this.context = context;
        Intent intent = new Intent("com.android.camera.action.CROP");
        try {
            cropImageFile = createCropImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        uri = Uri.fromFile(cropImageFile);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        return intent;
    }

    @Override
    public int getReqesutCode() {
        return 30;
    }

    @Override
    public void handleActivityResult(ImageLocalConfig imageLocalConfig, Intent data) {
    }

    private File createCropImageFile() throws IOException {
        String imgNameCrop = "HomePic_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pictureDirCrop = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/CropPicture");
        if (!pictureDirCrop.exists()) {
            pictureDirCrop.mkdirs();
        }
        File image = File.createTempFile(
                imgNameCrop,         /* prefix */
                ".jpg",             /* suffix */
                pictureDirCrop      /* directory */
        );
        return image;
    }
}

package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.constraint.Constraints.TAG;

public class CameraProvider implements ImageLocalProvider {

    private Context context;
    File image;
    Uri uri;
    @Override
    public Intent getIntent(Context context) throws IOException {
        this.context = context;
        //指定相机意图
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置相片保存的地址
        image = createOriImageFile();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(image);
        } else {
            uri = FileProvider.getUriForFile(context,context.getPackageName()+".provider",image);
        }

   // FileUtils.FileUri2ContentUri(context,image);

        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    @Override
    public int getRequestCode() {
        return 20;
    }


    @Override
    public void handleActivityResult(ImageLocalConfig r, Intent intent) {
        //TODO 照相解析返回资料
        if (r.isListener){
            Bitmap bitmap = null;


//            try {
//                Log.d(TAG, "handleActivityResult1: "+image.getPath()+"!"+image.getPath());
//                bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

            bitmap = BitmapFactory.decodeFile(image.getPath());
            r.imageLocalListener.setBitmapOnclickListener(bitmap);
        }
    }

    /**
     * 创建照相图片的文件
     * @return 图片文件
     */
    private  File  createOriImageFile(){
        String imgNameOri = "HomePic_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pictureDirOri = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();
        }
        File tempFile = null;
        try {
            tempFile = File.createTempFile(
                    imgNameOri,         /* prefix */
                    ".jpg",             /* suffix */
                    pictureDirOri       /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  tempFile;
    }

}

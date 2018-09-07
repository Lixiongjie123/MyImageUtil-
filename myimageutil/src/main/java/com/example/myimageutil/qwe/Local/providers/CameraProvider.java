package com.example.myimageutil.qwe.Local.providers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.FILE_PROVIDER_AUTHORITY;
import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.REQUEST_OPEN_CAMERA;

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
            uri = FileProvider.getUriForFile(context,FILE_PROVIDER_AUTHORITY,image);
        }

   // FileUtils.FileUri2ContentUri(context,image);

        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    @Override
    public int getRequestCode() {
        return REQUEST_OPEN_CAMERA;
    }


    @Override
    public void handleActivityResult(ImageLocalConfig r, Intent intent) {
        //TODO 照相解析返回资料




        if (!r.isCrop) {
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
                r.imageLocalListener.setUriOnclickListener(uri);

            }
        }else
        if (r.isActivity){
            r.activity.startActivityForResult(r.baseCrop.getIntent(r.context,uri,r.cutX,r.cutY),r.baseCrop.getRequestCode());
        }else {
            r.fragment.startActivityForResult(r.baseCrop.getIntent(r.context,uri,r.cutX,r.cutY),r.baseCrop.getRequestCode());
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

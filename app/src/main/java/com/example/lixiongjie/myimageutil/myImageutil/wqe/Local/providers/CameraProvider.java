package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraProvider implements ImageLocalProvider {

    private Context context;
    @Override
    public Intent getIntent(Context context) throws IOException {
        this.context = context;
        //指定相机意图
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置相片保存的地址
        //TODO 创建文件地址
        String imgNameOri = "HomePic_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pictureDirOri = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();
        }
        File image = File.createTempFile(
                imgNameOri,         /* prefix */
                ".jpg",             /* suffix */
                pictureDirOri       /* directory */
        );
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image);
        return intent;
    }

    @Override
    public int getRequestCode() {
        return 20;
    }


    @Override
    public void handleActivityResult(ImageLocalConfig r, Intent intent) {
        //TODO 照相解析返回资料

    }


}

package com.example.myimageutil.qwe.local.providers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.myimageutil.qwe.builder.BaseCrop;
import com.example.myimageutil.qwe.builder.records.ImageLocalConfig;
import com.example.myimageutil.qwe.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.FILE_PROVIDER_AUTHORITY;
import static com.example.myimageutil.qwe.builder.records.ImageLocalConfig.REQUEST_CROP_PHOTO;

public class CropProvider implements BaseCrop {
    private Context context;
    private  File cropfile ;
    private  Uri newUri ;



    public CropProvider() {

    }



    @Override
    public Intent getIntent(Context context, Uri uri, int outX, int outY) {
        this.context = context;

        return cropImage(uri,outX,outY);
    }

    @Override
    public int getRequestCode() {
        return REQUEST_CROP_PHOTO;
    }



    @Override
    public void handleActivityResult(ImageLocalConfig imageLocalConfig, Intent data) {
        if (imageLocalConfig.isListener){
            try {
                imageLocalConfig.imageLocalListener.setBitmapOnclickListener(BitmapFactory.decodeStream(context.getContentResolver().openInputStream(newUri)));
                imageLocalConfig.imageLocalListener.setUriOnclickListener(newUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
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


    public Intent cropPhoto(File file , int outX , int outY) {
        cropfile = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!cropfile.getParentFile().exists()) cropfile.getParentFile().mkdirs();
        Uri outputUri = Uri.fromFile(cropfile);
        Uri imageUri = FileProvider.getUriForFile( context,context.getPackageName()+".provider", new File("/storage/emulated/0/Pictures/OriPicture/HomePic_20180903_1427521360957876.jpg"));//通过FileProvider创建一个content类型的Uri
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outX);
        intent.putExtra("outputY", outY);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return intent;
    }




    private Intent cropImage(Uri fromUri, int outX , int outY) {
        if (fromUri == null) {
            return null;
        }
        final Intent cropIntent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上部分设备的没权限修改其它APP下的文件
            //如果不是程序自己的fileprovider，则把来源content uri的文件复制到自己fileprovider对应的目录下。
            // 因为部分应用不允许Intent.FLAG_GRANT_WRITE_URI_PERMISSION权限，强行申请该权限，会报安全异常。
            // 如果不拷贝文件，就需要自己实现一个文件裁剪器。自己的裁剪器裁剪的文件放在自己目录对应的包下，不会存在编辑时权限问题。
            if (!fromUri.getAuthority().equals(FILE_PROVIDER_AUTHORITY)) {
                try {
                    final File copyTargetFile = createCropImageFile();//
                    Uri targetUri = FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, copyTargetFile);
                    FileUtils.copyFileFromProviderToSelfProvider(context.getApplicationContext(), fromUri, targetUri);
                    fromUri = targetUri;//替换fromUri，之后的裁剪工作，裁剪的是我们程序FileProvider中的文件。
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//如果是低版本则跳过上面这一步

        cropIntent.setDataAndType(fromUri, "image/*");//capture from camera

        cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", outX);
        cropIntent.putExtra("outputY", outY);
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("return-data", false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            File cropImage = null;
            try {
                cropImage = createCropImageFile();//裁剪后的文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (cropImage != null) {
                newUri = FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, cropImage);
                //给所有具备裁剪功能的APP授权临时权限。
                List<ResolveInfo> resInfoList =context.getPackageManager().queryIntentActivities(cropIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    //给每个APP授权
                    context.grantUriPermission(packageName, fromUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    context.grantUriPermission(packageName, newUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
            }
        } else {
            try {
                createCropImageFile();//5.0以下直接创建文件，并赋值mUriForFile为新创建文件路径
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, newUri);//裁剪后的图片被输出的路径的uri
        cropIntent.putExtra("noFaceDetection", true);
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return cropIntent;
    }




}

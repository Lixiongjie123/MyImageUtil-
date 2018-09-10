package com.example.lixiongjie.myimageutil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myimageutil.qwe.Local.listener.ImageLocalListener;
import com.example.myimageutil.qwe.builder.core.MyImageUtil;
import com.example.myimageutil.qwe.builder.records.ImageLocalBuilder;


public class MainActivity extends AppCompatActivity {
    private Button mTextButton;
    MyImageUtil myImageUtil ;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextButton = findViewById(R.id.textbutton);
        imageView = findViewById(R.id.image);
        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });



    }

    private void choosePhoto() {
        myImageUtil = MyImageUtil.create(new ImageLocalBuilder()
                        .with(MainActivity.this)
                        .camera()
                        .crop(200,100)
                        .setBitmapListener(new ImageLocalListener() {
            @Override
            public void setBitmapOnclickListener(Bitmap bitmap) {

            }

            @Override
            public void setUriOnclickListener(Uri uri) {
            }
        }));
        myImageUtil.execute();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainActivity ", "onActivityResult: "+requestCode+"!"+resultCode+"!"+data);

        myImageUtil.onActivityForResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
      myImageUtil.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

   
}


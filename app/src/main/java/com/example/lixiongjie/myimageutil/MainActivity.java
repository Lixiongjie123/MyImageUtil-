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
                choosePhone();
            }
        });



    }

    public void choosePhone(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    20);

        }else {
            choosePhoto();
        }
    }

    private void choosePhoto() {
        myImageUtil = MyImageUtil.create(new ImageLocalBuilder().providerAuthorities("com.example.lixiongjie.myimageutil.provider").with(MainActivity.this).album().setBitmapListener(new ImageLocalListener() {
            @Override
            public void setBitmapOnclickListener(Bitmap bitmap) {

            }

            @Override
            public void setUriOnclickListener(Uri uri) {
                Log.d("", "setUriOnclickListener: "+uri);
                myImageUtil = MyImageUtil
                        .create(new ImageLocalBuilder().with(MainActivity.this).crop(uri,200,200)
                        .setBitmapListener(new ImageLocalListener() {
                    @Override
                    public void setBitmapOnclickListener(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                    @Override
                    public void setUriOnclickListener(Uri uri) {
                    }
                }));
                myImageUtil.execute();
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

        if (requestCode == 20)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                choosePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

   
}


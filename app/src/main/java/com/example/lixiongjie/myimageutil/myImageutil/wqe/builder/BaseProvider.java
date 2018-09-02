package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.Intent;

import java.io.IOException;

//类似适配器模式，把on
public interface  BaseProvider {
    void  execute() throws IOException;

    void onActivityResult(int requestCode, int resultCode, Intent intent);
}

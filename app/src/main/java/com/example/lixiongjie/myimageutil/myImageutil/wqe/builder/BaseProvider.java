package com.example.lixiongjie.myimageutil.myImageutil.wqe.builder;

import android.content.Intent;
//类似适配器模式，把on
public interface  BaseProvider {
    void  execute();

    void onActivityResult(int requestCode, int resultCode, Intent intent);
}

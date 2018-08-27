package com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.providers;

import android.content.Intent;

import com.example.lixiongjie.myimageutil.myImageutil.wqe.Local.listener.ImageLocalProvider;
import com.example.lixiongjie.myimageutil.myImageutil.wqe.builder.records.ImageLocalRecord;

public class AblumProvider implements ImageLocalProvider {
    @Override
    public int getIntent() {
        return 0;
    }

    @Override
    public int getRequestCode() {
        return 0;
    }

    @Override
    public void handleActivityResult(ImageLocalRecord r, Intent intent) {

    }
}

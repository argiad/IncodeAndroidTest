package com.incode.androidtest.activity;

import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by argi on 11/2/16.
 */

public class ViewController extends BaseActivity {

    void startMain(){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    void startImageCapture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }
}

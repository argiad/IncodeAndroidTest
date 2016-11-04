package com.incode.androidtest.interfaces;

/**
 * Created by argi on 11/2/16.
 */

public interface FragmentInterface {
    void onPictureClick(String pictureID);
    void onPictureShare (String pictureID);
    void onDataReload();
    void onTakePicture();
}

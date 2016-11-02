package com.incode.androidtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.incode.androidtest.DataManager;

/**
 * Created by argi on 11/2/16.
 */

abstract class BaseActivity extends AppCompatActivity {

    /**
     *  Business Logic DataManager shortcut
     */
    DataManager dm = DataManager.INSTANSE;

    /**
     *  Log Tag
     */
    public static String TAG;


    void log(String textToLog){
        Log.d(TAG, textToLog);
    }
}

package com.incode.androidtest.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.incode.androidtest.R;
import com.incode.androidtest.fragment.DetailsFragment;
import com.incode.androidtest.fragment.MainFragment;
import com.incode.androidtest.interfaces.DataReady;
import com.incode.androidtest.interfaces.FragmentInterface;

/**
 * Created by argi on 11/2/16.
 */

public class MainActivity extends ViewController implements FragmentInterface, DataReady {

    MainFragment mainFragment;
    DetailsFragment detailsFragment;
    DataReady drListener;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);

        TAG = getClass().getSimpleName();
        drListener = this;


        if (dm.data == null )
            dm.init(getApplicationContext(), drListener);


        if (savedInstanceState == null) {
            mainFragment = MainFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.port, mainFragment)
                    .commit();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            dm.takenPicture(imageBitmap);
        }
    }

    @Override
    public void onPictureClick(String id) {
        log(id);
        dm.picture = dm.getPictureByID(id);
        detailsFragment = DetailsFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.port, detailsFragment)
                .commit();
        mainFragment = null;

    }

    @Override
    public void onBackPressed(){
        if (mainFragment == null) {
            mainFragment = MainFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.port, mainFragment)
                    .commit();
            detailsFragment = null;
        } else {
            finish();
        }
    }

    @Override
    public void onPictureShare(String id) {
        log(id);
        dm.shareImage(this);
    }

    @Override
    public void onDataReload() {
        dm.init(getApplicationContext(),drListener);
    }

    @Override
    public void onTakePicture() {
        startImageCapture();
    }

    @Override
    public void onDataReady() {
        Log.d(TAG,"on Data Ready");
        mainFragment.reloadData();
    }

    @Override
    public void onRequestFailure() {
        log("on Request Failure");
    }
}

package com.incode.androidtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.incode.androidtest.interfaces.DataReady;
import com.incode.androidtest.interfaces.RetrofitInterface;
import com.incode.androidtest.models.Picture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by argi on 11/2/16.
 */

public enum DataManager {
    INSTANCE;

    private String baseURL = "http://beta.json-generator.com/";
    private Context context;

    public List<Picture> data;
    public List<Picture> cachedData;


    public Retrofit retrofit;

    // not good but simple
    public Picture picture;

    public Bitmap image;

    void init(){
        init(context);
    }

    public void init(Context ctx) {
        init(ctx, null);
    }

    public void init(Context ctx, final DataReady dataReady) {

        context = ctx;

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<List<Picture>> call = request.getJSON();
        call.enqueue(new Callback<List<Picture>>() {
            @Override
            public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
                data = response.body();
                cachedData = response.body();
                if (dataReady != null)
                    dataReady.onDataReady();
            }

            @Override
            public void onFailure(Call<List<Picture>> call, Throwable t) {
                t.printStackTrace();
                if (dataReady != null)
                    dataReady.onRequestFailure();
            }
        });

    }

    public void takedPicture(Bitmap bitmap){

        data = cachedData;
        Picture picture = new Picture();

        try {

            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath + "/temp.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

            picture.setPicture(cachePath + "/temp.png");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        picture.setComment("new picture");
        picture.setId("0000000000000");
        picture.setPublishedAt("00:00:00 2020.03.01");
        picture.setTitle("Tacked Picture");

        data.add(picture);


    }

    public Picture getPictureByID(String ID) {
        for (Picture picture : data) {
            if (picture.getId().equalsIgnoreCase(ID))
                return picture;
        }
        return null;
    }

    public void shareImage(Activity activity){
        try {

            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png");
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File imagePath = new File(context.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(context, "com.incode.androidtest.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            activity.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }

    }


}

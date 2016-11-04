package com.incode.androidtest.interfaces;

import com.incode.androidtest.models.Picture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by argi on 11/3/16.
 */

public interface RetrofitInterface {

    @GET("api/json/get/EkphH5xyM")
    Call<List<Picture>> getJSON();

}

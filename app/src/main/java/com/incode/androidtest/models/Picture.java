package com.incode.androidtest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by argi on 11/3/16.
 */

public class Picture {

    @SerializedName("picture")
    private String picture;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("comment")
    private String comment;

    @SerializedName("title")
    private String title;

    @SerializedName("_id")
    private String id;


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

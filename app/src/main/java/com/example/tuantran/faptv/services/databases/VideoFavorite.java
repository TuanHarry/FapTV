package com.example.tuantran.faptv.services.databases;

import io.realm.RealmObject;

public class VideoFavorite extends RealmObject {

    String videoID;
    String videoTitle;
    String urlImag;
    boolean isFavorite;



    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getUrlImag() {
        return urlImag;
    }

    public void setUrlImag(String urlImag) {
        this.urlImag = urlImag;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

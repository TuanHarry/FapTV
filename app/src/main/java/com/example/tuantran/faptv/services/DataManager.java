package com.example.tuantran.faptv.services;

import com.example.tuantran.faptv.services.databases.VideoFavorite;

import java.util.List;

public interface DataManager {
    void addFavorite(VideoFavorite favorite);
    void removeFavorite(VideoFavorite favorite);
    boolean CheckFavoritebyID(String videoID);
    List<VideoFavorite> getListVideoFavorite();
}

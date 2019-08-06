package com.example.tuantran.faptv.services.databases;

import java.util.List;

public interface DatabaseHelper {
    void addFavorite(VideoFavorite favorite);
    List<VideoFavorite> getListFavorite();
    void removeFavorite(VideoFavorite favorite);
    boolean checkFavoriteByVideoID(String videoID);
}

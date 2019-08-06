package com.example.tuantran.faptv.fragments.favorites;

import com.example.tuantran.faptv.services.AppDataManager;
import com.example.tuantran.faptv.services.DataManager;
import com.example.tuantran.faptv.services.databases.VideoFavorite;

import java.util.List;

public class VideoFavoritePresenter {

    // Call favorite from database

    DataManager mDataManager = AppDataManager.getInstance();

    public VideoFavoritePresenter() {
    }

    protected List<VideoFavorite> getListFavorite(){
        return mDataManager.getListVideoFavorite();
    }
}

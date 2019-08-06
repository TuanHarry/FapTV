package com.example.tuantran.faptv.services;

import com.example.tuantran.faptv.Application;
import com.example.tuantran.faptv.services.databases.AppDatabaseHelper;
import com.example.tuantran.faptv.services.databases.VideoFavorite;

import java.util.List;

public class AppDataManager  implements DataManager{
    // Single ton patent
    private AppDatabaseHelper mDatabaseHelper;
    private static volatile AppDataManager Instance = null;

    private AppDataManager() {
        mDatabaseHelper = new AppDatabaseHelper(Application.mContext);
    }

    public static AppDataManager getInstance(){
        AppDataManager localInstance = Instance;
        if (localInstance == null) {
            synchronized (AppDataManager.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new AppDataManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void addFavorite(VideoFavorite favorite) {
        mDatabaseHelper.addFavorite(favorite);
    }

    @Override
    public void removeFavorite(VideoFavorite favorite) {
        mDatabaseHelper.removeFavorite(favorite);
    }

    @Override
    public boolean CheckFavoritebyID(String videoID) {
        return mDatabaseHelper.checkFavoriteByVideoID(videoID);
    }

    @Override
    public List<VideoFavorite> getListVideoFavorite() {
        return mDatabaseHelper.getListFavorite();
    }
}

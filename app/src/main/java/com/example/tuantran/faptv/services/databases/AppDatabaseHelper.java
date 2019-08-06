package com.example.tuantran.faptv.services.databases;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AppDatabaseHelper implements DatabaseHelper {

    private Realm mRealm;

    public AppDatabaseHelper(Context context){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .schemaVersion(1)
                .build();
        mRealm = Realm.getInstance(config);
    }

    @Override
    public void addFavorite(VideoFavorite favorite) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(favorite);
            }
        });
    }

    @Override
    public List<VideoFavorite> getListFavorite() {
        RealmResults<VideoFavorite> results = mRealm.where(VideoFavorite.class).findAll() ;
        return results;
    }


    @Override
    public void removeFavorite(VideoFavorite favorite) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<VideoFavorite> results = realm.where(VideoFavorite.class).equalTo("videoID",favorite.getVideoID()).findAll();
                results.deleteAllFromRealm();
            }
        });
    }

    @Override
    public boolean checkFavoriteByVideoID(String videoID) {
        RealmResults<VideoFavorite> results = mRealm.where(VideoFavorite.class).equalTo("videoID",videoID).findAll();
        if(results.isEmpty())
            return false;
        return true;
    }
}

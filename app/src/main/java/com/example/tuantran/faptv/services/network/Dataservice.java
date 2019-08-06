package com.example.tuantran.faptv.services.network;

import com.example.tuantran.faptv.model.YoutubeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Dataservice {

    @GET("search")
    Call<YoutubeModel> getData(
            @Query("key") String key,
            @Query("channelId") String channelID,
            @Query("part") String path,
            @Query("order") String date,
            @Query("maxResults") int maxResults);
}

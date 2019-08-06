package com.example.tuantran.faptv.fragments.playlist;

import android.util.Log;

import com.example.tuantran.faptv.model.Item;
import com.example.tuantran.faptv.model.Video;
import com.example.tuantran.faptv.model.YoutubeModel;
import com.example.tuantran.faptv.services.network.APIService;
import com.example.tuantran.faptv.services.network.Dataservice;
import com.example.tuantran.faptv.utils.Contanst;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistPresenter {
    private static String TAG = PlaylistPresenter.class.getName();
    private List<Video> data = new ArrayList<>();
    Single<List<Video>> observe;
    public PlaylistPresenter() {
    }
    // Request API channel youtube and return data

    private List<Video> convertData(List<Item> listItem){
        List<Video> listVideo = new ArrayList<>();

        for (int i = 0; i < listItem.size(); i++){
            Video video = new Video();
            video.setVideoID(listItem.get(i).getId().getVideoId());
            video.setPublishedAt(listItem.get(i).getSnippet().getPublishedAt());
            video.setTitle(listItem.get(i).getSnippet().getTitle());
            video.setThumbnailUrl(listItem.get(i).getSnippet().getThumbnails().getMedium().getUrl());
            video.setDescription(listItem.get(i).getSnippet().getDescription());
            video.setChannelTitle(listItem.get(i).getSnippet().getChannelTitle());

            listVideo.add(video);

        }
        return listVideo;
    }

    public synchronized void getDataAPI(final VideosListener listener) {
        String part = "snippet,id";
        String order = "date";
        int maxResult = 20;
        final Dataservice dataservice = APIService.getService();
        Call<YoutubeModel> res = dataservice.getData(Contanst.YTB_API_KEY, Contanst.CHANNEL_ID, part, order, maxResult);
        res.enqueue(new Callback<YoutubeModel>() {
            @Override
            public void onResponse(Call<YoutubeModel> call, Response<YoutubeModel> response) {
                //TODO add button progressbar
                if (response.body() != null) {
                    List<Item> res = response.body().getItems();
                    data = convertData(res);
                    listener.onSuccess(data);
                } else {
                    Log.d(TAG, "onResponse: " + "data null");
                }
            }

            @Override
            public void onFailure(Call<YoutubeModel> call, Throwable t) {
                Log.d("LOL_ME_MAY", "onFailure: ");
            }
        });

    }
    public interface VideosListener{
        void onSuccess(List<Video> data);
    }
}

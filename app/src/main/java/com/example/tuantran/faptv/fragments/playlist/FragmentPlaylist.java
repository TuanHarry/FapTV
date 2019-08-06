package com.example.tuantran.faptv.fragments.playlist;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.faptv.R;
import com.example.tuantran.faptv.adapters.PlaylistAdapter;
import com.example.tuantran.faptv.fragments.favorites.IViewFavoriteVideo;
import com.example.tuantran.faptv.model.Video;
import com.example.tuantran.faptv.services.AppDataManager;
import com.example.tuantran.faptv.services.DataManager;
import com.example.tuantran.faptv.services.databases.VideoFavorite;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlaylist extends Fragment implements IViewFavoriteVideo{
    static String TAG = FragmentPlaylist.class.getName();

    private RecyclerView recyclerView;
    private TextView videoTitle;
    private TextView publishedAt;
    private ImageView iconFavorite;
    private PlaylistAdapter adapter;

    // YoutubePlayerView
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;

    //Data presenter
    private PlaylistPresenter presenter;
    private DataManager mDataManager = AppDataManager.getInstance();
    IViewFavoriteVideo iView;

    public void setView(IViewFavoriteVideo v) {
        this.iView = v;
    }


    // Variable videoIDUpdate
    private String videoIDUpdate;

    private String getVideoIDUpdate() {
        return videoIDUpdate;
    }

    private void setVideoIDUpdate(String videoIDUpdate) {
        this.videoIDUpdate = videoIDUpdate;
    }

    // Constructor Fragment
    public FragmentPlaylist() {
        presenter = new PlaylistPresenter();
    }

    // Instances Fragment
    public static FragmentPlaylist newInstances(){
        return new FragmentPlaylist();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_playlist, container, false);
        initView(view);
        getData();
        setView(this);
        return view;

    }

    private void getData() {
        presenter.getDataAPI(new PlaylistPresenter.VideosListener() {
            @Override
            public void onSuccess(List<Video> data) {
                initControl(data);
            }
        });

    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.rvc_list_video);
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        videoTitle = view.findViewById(R.id.videoTitle);
        publishedAt = view.findViewById(R.id.publishedAt);
        iconFavorite = view.findViewById(R.id.favorite);
    }

    private void initControl(final List<Video> listData) {

        // Life cycle youtubePlayerView
        initYoutubePlayerView(listData);
        getLifecycle().addObserver(youTubePlayerView);

        // set data to RecyclerView
        adapter = new PlaylistAdapter(listData);
        addDataToRecyclerView(adapter);

        // Set onClick when click item in recyclerView
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = listData.get(position).getVideoID();
                updateVideoID(youTubePlayer,id);
                getAttributeVideo(listData,id);

                boolean isCheck = mDataManager.CheckFavoritebyID(id);
                if (isCheck)
                    iconFavorite.setImageResource(R.drawable.ic_baseline_favorite_24px);
                else
                    iconFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24px);

            }
        });

        iconFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variable check video id have favorite
                boolean isCheck = mDataManager.CheckFavoritebyID(getVideoIDUpdate());
                if (isCheck == false){
                    iconFavorite.setImageResource(R.drawable.ic_baseline_favorite_24px);
                    //TODO save database
                    Video videoInFo = getInfo(listData,getVideoIDUpdate());
                    VideoFavorite video = new VideoFavorite();
                    video.setVideoID(videoInFo.getVideoID());
                    video.setVideoTitle(videoInFo.getTitle());
                    video.setUrlImag(videoInFo.getThumbnailUrl());
                    video.setFavorite(true);
                    iView.UpdateVideo(video);
                    mDataManager.addFavorite(video);
                }else {
                    iconFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24px);
                    Toast.makeText(getActivity(), "Unfavorite", Toast.LENGTH_SHORT).show();
                    //TODO delele video from database
                    Video videoInFo = getInfo(listData,getVideoIDUpdate());
                    VideoFavorite video = new VideoFavorite();
                    video.setVideoID(videoInFo.getVideoID());
                    video.setVideoTitle(videoInFo.getTitle());
                    video.setUrlImag(videoInFo.getThumbnailUrl());
                    video.setFavorite(false);
                    iView.UpdateVideo(video);
                    mDataManager.removeFavorite(video);
                }
            }
        });


    }

    // init YouTubePlayerView
    private synchronized void initYoutubePlayerView(final List<Video> data){
        // Set videoID is video first in list video
        String videoID = data.get(0).getVideoID();


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NotNull final YouTubePlayer initializedYouTubePlayer) {
                setVideoIDUpdate(videoID);
                super.onReady(initializedYouTubePlayer);
                youTubePlayer = initializedYouTubePlayer;
                youTubePlayer.loadVideo(getVideoIDUpdate(),0f);
                getAttributeVideo(data,videoID);
            }
        });

        boolean isCheck = mDataManager.CheckFavoritebyID(videoID);
        if (isCheck)
            iconFavorite.setImageResource(R.drawable.ic_baseline_favorite_24px);
        else
            iconFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24px);
    }

    // Update VideoID when click item in recyclerView
    private void updateVideoID(final YouTubePlayer youTubePlayer,String videoIDUpdate){
        setVideoIDUpdate(videoIDUpdate);
        youTubePlayer.loadVideo(getVideoIDUpdate(),0f);
    }

    // init VideoID when init YoutubePlayerView
    private String getRandomElement(List<Video> data) {
        Random rand = new Random();
        int index = rand.nextInt(data.size());
        System.out.println("\nIndex :" + index );
        return data.get(index).getVideoID();
    }

    // get attribute video by id video
    private void getAttributeVideo(List<Video> data,String videoID){
        for(int i =0 ; i < data.size(); i++){
            if (videoID.equals(data.get(i).getVideoID())){
                // substring title video
                String title = data.get(i).getTitle();
                videoTitle.setText(title.substring(16,title.length()));
                // substring date
                String date = data.get(i).getPublishedAt();
                publishedAt.setText(date.substring(0,10));
                Log.d(TAG, "getAttributeVideo: ");
            }
        }
    }

    // get position video is playing by id video

    private int getPositionVideo(List<Video> data, String videoID){
        for (int i = 0; i < data.size(); i++){
            if (videoID.equals(data.get(i).getVideoID())){
                return i;
            }
        }
        return 0;
    }

    // get information video by video id
    private Video getInfo(List<Video> data, String videoID){
     //   Video v = new Video();
        for (int i = 0; i < data.size(); i++){
            if (videoID.equals(data.get(i).getVideoID())){
                return data.get(i);
            }
        }
        return null;
    }


    // Setup data to RecyclerView
    private void addDataToRecyclerView(PlaylistAdapter playlistAdapter){
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(playlistAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

    @Override
    public void UpdateVideo(VideoFavorite video) {

    }

    private VideoFavorite convertVideoToVideoFavorite(Video video){
        VideoFavorite favorite = new VideoFavorite();
        favorite.setVideoID(video.getVideoID());
        favorite.setVideoTitle(video.getTitle());
        favorite.setUrlImag(video.getThumbnailUrl());
        return favorite;
    }

    private void addVideo(List<Video> listData, String videoID){
        Video videoInFo = getInfo(listData,videoID);
        VideoFavorite video = convertVideoToVideoFavorite(videoInFo);
        video.setFavorite(true);
        iView.UpdateVideo(video);

    }
}

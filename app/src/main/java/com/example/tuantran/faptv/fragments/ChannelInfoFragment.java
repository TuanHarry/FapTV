package com.example.tuantran.faptv.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuantran.faptv.R;
import com.example.tuantran.faptv.adapters.PlaylistAdapter;
import com.example.tuantran.faptv.model.Item;
import com.example.tuantran.faptv.model.Video;
import com.example.tuantran.faptv.utils.Contanst;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelInfoFragment extends Fragment {

    RecyclerView recyclerView;


    PlaylistAdapter adapter;
    YouTubePlayerView youTubePlayerView;

    public ChannelInfoFragment() {
        // Required empty public constructor
    }


    public static ChannelInfoFragment newInstances(){
        ChannelInfoFragment channelInfoFragment = new ChannelInfoFragment();
        return channelInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channel_info, container, false);


        // setup video intro
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        return view;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        if (menuVisible){
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    String videoID = Contanst.VIDEO_INTRO;
                    youTubePlayer.loadVideo(videoID,0);
                }
            });

        }
    }

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

    // Setup data to RecyclerView
    private void addDataToRecyclerView(PlaylistAdapter playlistAdapter){
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(playlistAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

}

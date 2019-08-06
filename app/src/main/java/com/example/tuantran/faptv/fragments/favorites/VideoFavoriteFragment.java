
package com.example.tuantran.faptv.fragments.favorites;


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
import com.example.tuantran.faptv.adapters.VideoFavoriteAdapter;
import com.example.tuantran.faptv.fragments.playlist.FragmentPlaylist;
import com.example.tuantran.faptv.services.databases.VideoFavorite;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFavoriteFragment extends Fragment{

    private RecyclerView recyclerView;
    private VideoFavoriteAdapter adapter;
    private VideoFavoritePresenter presenter;
    private List<VideoFavorite> data = new ArrayList<>();

    public VideoFavoriteFragment() {
        // Required empty public constructor
    }

    public static VideoFavoriteFragment newInstances(){
        VideoFavoriteFragment videoFavoriteFragment = new VideoFavoriteFragment();
        return videoFavoriteFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_favorite, container, false);
        initView(view);
        initControl();
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rvc_list_favorite);
        presenter = new VideoFavoritePresenter();
    }

    private void initControl() {
        data = presenter.getListFavorite();
        adapter = new VideoFavoriteAdapter(data);
        addDataToRecyclerView(adapter);
    }

    // Setup data to RecyclerView
    private void addDataToRecyclerView(VideoFavoriteAdapter adapter){
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

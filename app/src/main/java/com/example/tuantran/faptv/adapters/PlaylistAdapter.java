package com.example.tuantran.faptv.adapters;

import androidx.annotation.Nullable;

import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.faptv.R;
import com.example.tuantran.faptv.model.Video;
import com.example.tuantran.faptv.utils.glide.ImagerHelper;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends BaseQuickAdapter<Video, BaseViewHolder> {
    private List<Video> list = new ArrayList<>();

    public PlaylistAdapter(@Nullable List<Video> data) {
        super(R.layout.item_video,data);
//        addItemType(1,R.layout.item_youtube_playerview);
//        addItemType(2,R.layout.item_video);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, Video item) {
        setupViewForItem(helper,item);
    }


    private void setupViewForItem(BaseViewHolder helper, Video item) {
        ImageView thumbnailUrl = helper.getView(R.id.thumbnailUrl);
        ImagerHelper.load(mContext,thumbnailUrl,item.getThumbnailUrl());
        helper.setText(R.id.video_title, item.getTitle());
        helper.setText(R.id.video_description, item.getDescription());
    }
}

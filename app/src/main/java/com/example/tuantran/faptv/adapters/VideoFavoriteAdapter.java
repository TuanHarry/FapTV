package com.example.tuantran.faptv.adapters;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.faptv.R;
import com.example.tuantran.faptv.services.databases.VideoFavorite;
import com.example.tuantran.faptv.utils.glide.ImagerHelper;

import java.util.ArrayList;
import java.util.List;

public class VideoFavoriteAdapter extends BaseQuickAdapter<VideoFavorite, BaseViewHolder> {
    List<VideoFavorite> list = new ArrayList<>();
    public VideoFavoriteAdapter(@Nullable List<VideoFavorite> data) {
        super(R.layout.item_video_favorite,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoFavorite item) {
        ImageView img = helper.getView(R.id.thumbnailUrl);
        ImagerHelper.load(mContext,img,item.getUrlImag());
        helper.setText(R.id.video_title,item.getVideoTitle());
        helper.addOnClickListener(R.id.action_favorite);
        helper.addOnClickListener(R.id.action_share);
    }
}

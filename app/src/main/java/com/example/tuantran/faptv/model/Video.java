package com.example.tuantran.faptv.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Video implements MultiItemEntity {
    String typeItemForAdapter;
    String videoID;
    String publishedAt;
    String title;
    String description;
    String thumbnailUrl;
    String channelTitle;



    public Video() {
    }

    public Video(String videoID, String publishedAt, String title, String description, String thumbnailUrl, String channelTitle) {
        this.videoID = videoID;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.channelTitle = channelTitle;
    }

    // This is Constructor using for Adapter
    public Video(String typeItemForAdapter, String videoID, String publishedAt, String title, String description, String thumbnailUrl, String channelTitle) {
        this.typeItemForAdapter = typeItemForAdapter;
        this.videoID = videoID;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.channelTitle = channelTitle;
    }

    public String getTypeItemForAdapter() {
        return typeItemForAdapter;
    }

    public void setTypeItemForAdapter(String typeItemForAdapter) {
        this.typeItemForAdapter = typeItemForAdapter;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    @Override
    public int getItemType() {
        return Integer.parseInt(getTypeItemForAdapter());
    }
}

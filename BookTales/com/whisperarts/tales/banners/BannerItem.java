package com.whisperarts.tales.banners;

public class BannerItem {
    public int imageId;
    public String packageName;

    public BannerItem(String packageName, int imageId) {
        this.packageName = packageName;
        this.imageId = imageId;
    }
}

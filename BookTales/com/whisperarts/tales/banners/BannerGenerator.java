package com.whisperarts.tales.banners;

import com.whisperarts.tales.entities.AdditionalTalesInfo;
import com.whisperarts.tales.entities.FullTaleInfo;
import com.whisperarts.tales.entities.FullTaleInfo.PACK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BannerGenerator {
    private static final int MAX_NUMBER_OF_BANNERS = 5;
    private final List<FullTaleInfo> allTales;

    public BannerGenerator(Map<String, FullTaleInfo> allTales) {
        this.allTales = new ArrayList(allTales.values());
    }

    public List<BannerItem> doGenerate() {
        List<BannerItem> banners = new ArrayList();
        for (FullTaleInfo taleInfo : this.allTales) {
            if (!taleInfo.isPaid()) {
                String packageName = taleInfo.getFullPackage();
                AdditionalTalesInfo additionalTaleInfo = AdditionalTalesInfo.byPackageName(packageName);
                if (additionalTaleInfo != null) {
                    banners.add(new BannerItem(packageName, additionalTaleInfo.bannerDrawableId));
                }
            }
        }
        List<BannerItem> packBanners = new ArrayList();
        for (PACK pack : PACK.packs.values()) {
            if (!pack.isInstalledOrNotNeeded) {
                packBanners.add(new BannerItem(pack.packageName, pack.bannerId));
            }
        }
        if (!packBanners.isEmpty()) {
            Collections.shuffle(packBanners);
            banners.add(0, (BannerItem) packBanners.get(0));
        }
        if (banners.size() > MAX_NUMBER_OF_BANNERS) {
            banners = banners.subList(0, MAX_NUMBER_OF_BANNERS);
        }
        Collections.shuffle(banners, new Random(System.currentTimeMillis()));
        return banners;
    }
}

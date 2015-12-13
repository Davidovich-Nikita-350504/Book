package com.whisperarts.tales.entities;

import com.whisperarts.tales.C0026R;

public enum AdditionalTalesInfo {
    repka("com.whisperarts.tales.repka", "1.3.5", C0026R.drawable.banner_repka),
    threeporosenka("com.whisperarts.tales.threeporosenka", "1.3.5", C0026R.drawable.banner_threeporosenka),
    kolobok("com.whisperarts.tales.kolobok", "1.3.6", C0026R.drawable.banner_kolobok),
    kurochka("com.whisperarts.tales.kurochka", "1.3.5", C0026R.drawable.banner_kurochka),
    lisichka("com.whisperarts.tales.lisichka", "1.3.5", C0026R.drawable.banner_lisichka),
    teremok("com.whisperarts.tales.teremok", "1.3.5", C0026R.drawable.banner_teremok),
    sevenkozl("com.whisperarts.tales.sevenkozl", "1.3.5", C0026R.drawable.banner_sevenkozl),
    masha("com.whisperarts.tales.masha", "1.3.5", C0026R.drawable.banner_masha),
    twogreedybears("com.whisperarts.tales.twogreedybears", "1.3.5", C0026R.drawable.banner_twogreedybears),
    threebears("com.whisperarts.tales.threebears", "1.3.5", C0026R.drawable.banner_threebears),
    taleaboutfish("com.whisperarts.tales.taleaboutfish", "1.3.5", C0026R.drawable.banner_taleaboutfish),
    gysilebedi("com.whisperarts.tales.gysilebedi", "1.3.5", C0026R.drawable.banner_gysilebedi),
    krylaty("com.whisperarts.tales.krylaty", "1.3.5", C0026R.drawable.banner_krylaty),
    rooster("com.whisperarts.tales.rooster", "1.3.5", C0026R.drawable.banner_rooster),
    manandbear("com.whisperarts.tales.manandbear", "1.3.5", C0026R.drawable.banner_manandbear),
    princess("com.whisperarts.tales.princess", "1.3.5", C0026R.drawable.banner_princessa),
    lisaidrozd("com.whisperarts.tales.lisaidrozd", "1.3.5", C0026R.drawable.banner_lisa_drozd),
    morozko("com.whisperarts.tales.morozko", "1.3.5", C0026R.drawable.banner_morozko),
    kasha("com.whisperarts.tales.kasha", "1.3.5", C0026R.drawable.banner_kasha),
    rabbitshut("com.whisperarts.tales.rabbitshut", "1.3.5", C0026R.drawable.banner_rabbitshut),
    cockerelandmillstones("com.whisperarts.tales.cockerelandmillstones", "1.3.5", C0026R.drawable.banner_cockerelandmillstones),
    cotofey("com.whisperarts.tales.cotofey", "1.3.5", C0026R.drawable.banner_cotofey),
    tsarevnalyagushka("com.whisperarts.tales.tsarevnalyagushka", "1.3.5", C0026R.drawable.banner_tsarevnalyagushka),
    tomthumb("com.whisperarts.tales.tomthumb", "1.3.5", C0026R.drawable.banner_tomthumb),
    animalswinter("com.whisperarts.tales.animalswinter", "1.3.5", C0026R.drawable.banner_animalswinter),
    flyingship("com.whisperarts.tales.flyingship", "1.3.5", C0026R.drawable.banner_flyingship),
    geese("com.whisperarts.tales.geese", "1.3.5", C0026R.drawable.banner_geese),
    pussinboots("com.whisperarts.tales.pussinboots", "1.3.5", C0026R.drawable.banner_pussinboots),
    elena("com.whisperarts.tales.elena", "1.3.5", C0026R.drawable.banner_elena),
    heronandcrane("com.whisperarts.tales.heronandcrane", "1.3.5", C0026R.drawable.banner_heronandcrane),
    redhood("com.whisperarts.tales.redhood", "1.3.5", C0026R.drawable.banner_redhood),
    nikita("com.whisperarts.tales.nikita", "1.3.5", C0026R.drawable.banner_nikita),
    zhiharka("com.whisperarts.tales.zhiharka", "1.3.5", C0026R.drawable.banner_zhiharka),
    cinderella("com.whisperarts.tales.cinderella", "1.3.5", C0026R.drawable.banner_cinderella),
    spica("com.whisperarts.tales.spica", "1.3.5", C0026R.drawable.banner_spica),
    howagoatbuiltahut("com.whisperarts.tales.howagoatbuiltahut", "1.3.5", C0026R.drawable.banner_howagoatbuiltahut),
    therewasadog("com.whisperarts.tales.therewasadog", "1.3.5", C0026R.drawable.banner_therewasadog),
    twofrosts("com.whisperarts.tales.twofrosts", "1.3.5", C0026R.drawable.banner_twofrosts),
    crownking("com.whisperarts.tales.crownking", "1.0.0", C0026R.drawable.banner_crownking),
    priestandbald("com.whisperarts.tales.priestandbald", "1.0.0", C0026R.drawable.banner_priestandbald),
    sisteralenushka("com.whisperarts.tales.sisteralenushka", "1.0.0", C0026R.drawable.banner_sisteralenushka),
    dolphins("com.whisperarts.tales.dolphins", "1.0.0", C0026R.drawable.banner_dolphins),
    pan("com.whisperarts.tales.pan", "1.0.0", C0026R.drawable.banner_pan),
    sivko("com.whisperarts.tales.sivko", "1.0.0", C0026R.drawable.banner_sivko),
    bychok("com.whisperarts.tales.bychok", "1.0.0", C0026R.drawable.banner_bychok),
    babayaga("com.whisperarts.tales.babayaga", "1.0.0", C0026R.drawable.banner_babayaga),
    ladushki("com.whisperarts.tales.ladushki", "1.0.0", C0026R.drawable.banner_ladushki),
    lisichkasoskalochkoi("com.whisperarts.tales.lisichkasoskalochkoi", "1.0.0", C0026R.drawable.banner_lisichkasoskalochkoi);
    
    public final int bannerDrawableId;
    public final String packageName;
    public final String versionName;

    private AdditionalTalesInfo(String packageName, String versionName, int bannerDrawableId) {
        this.packageName = packageName;
        this.versionName = versionName;
        this.bannerDrawableId = bannerDrawableId;
    }

    public static AdditionalTalesInfo byPackageName(String packageName) {
        for (AdditionalTalesInfo info : values()) {
            if (info.packageName.equals(packageName)) {
                return info;
            }
        }
        return null;
    }
}

package com.whisperarts.tales.entities;

import android.graphics.Bitmap;
import com.whisperarts.tales.C0026R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FullTaleInfo implements Comparable<FullTaleInfo> {
    private String description;
    private String fullPackage;
    private Bitmap image;
    private boolean isPaid;
    private String name;
    private boolean needUpgrade;
    private String price;

    public static class PACK {
        public static final Map<String, PACK> packs;
        public int bannerId;
        public boolean isInstalledOrNotNeeded;
        public int maxNumberOfInstalledTalesToIgnorePack;
        public String packageName;
        private final List<String> tales;

        static {
            packs = new HashMap();
            packs.put("com.whisperarts.tales.pack", new PACK("com.whisperarts.tales.pack", 4, C0026R.drawable.banner_sbornik_1, "com.whisperarts.tales.sevenkozl", "com.whisperarts.tales.kolobok", "com.whisperarts.tales.teremok", "com.whisperarts.tales.kurochka", "com.whisperarts.tales.masha", "com.whisperarts.tales.twogreedybears", "com.whisperarts.tales.threebears", "com.whisperarts.tales.taleaboutfish", "com.whisperarts.tales.krylaty", "com.whisperarts.tales.rooster", "com.whisperarts.tales.manandbear"));
            packs.put("com.whisperarts.tales.pack2", new PACK("com.whisperarts.tales.pack2", 4, C0026R.drawable.banner_sbornik_2, "com.whisperarts.tales.lisaidrozd", "com.whisperarts.tales.princess", "com.whisperarts.tales.morozko", "com.whisperarts.tales.kasha", "com.whisperarts.tales.rabbitshut", "com.whisperarts.tales.cockerelandmillstones", "com.whisperarts.tales.cotofey", "com.whisperarts.tales.tsarevnalyagushka", "com.whisperarts.tales.tomthumb", "com.whisperarts.tales.animalswinter", "com.whisperarts.tales.flyingship"));
            packs.put("com.whisperarts.tales.pack3", new PACK("com.whisperarts.tales.pack3", 4, C0026R.drawable.banner_sbornik_3, "com.whisperarts.tales.geese", "com.whisperarts.tales.pussinboots", "com.whisperarts.tales.elena", "com.whisperarts.tales.heronandcrane", "com.whisperarts.tales.redhood", "com.whisperarts.tales.nikita", "com.whisperarts.tales.zhiharka", "com.whisperarts.tales.cinderella", "com.whisperarts.tales.spica", "com.whisperarts.tales.howagoatbuiltahut", "com.whisperarts.tales.therewasadog"));
            packs.put("com.whisperarts.tales.packmega", new PACK("com.whisperarts.tales.packmega", 13, C0026R.drawable.banner_sbornik_mega, "com.whisperarts.tales.lisichka", "com.whisperarts.tales.repka", "com.whisperarts.tales.threeporosenka", "com.whisperarts.tales.gysilebedi", "com.whisperarts.tales.sevenkozl", "com.whisperarts.tales.kolobok", "com.whisperarts.tales.teremok", "com.whisperarts.tales.kurochka", "com.whisperarts.tales.masha", "com.whisperarts.tales.twogreedybears", "com.whisperarts.tales.threebears", "com.whisperarts.tales.taleaboutfish", "com.whisperarts.tales.krylaty", "com.whisperarts.tales.rooster", "com.whisperarts.tales.manandbear", "com.whisperarts.tales.lisaidrozd", "com.whisperarts.tales.princess", "com.whisperarts.tales.morozko", "com.whisperarts.tales.kasha", "com.whisperarts.tales.rabbitshut", "com.whisperarts.tales.cockerelandmillstones", "com.whisperarts.tales.cotofey", "com.whisperarts.tales.tsarevnalyagushka", "com.whisperarts.tales.tomthumb", "com.whisperarts.tales.animalswinter", "com.whisperarts.tales.flyingship", "com.whisperarts.tales.geese", "com.whisperarts.tales.pussinboots", "com.whisperarts.tales.elena", "com.whisperarts.tales.heronandcrane", "com.whisperarts.tales.redhood", "com.whisperarts.tales.nikita", "com.whisperarts.tales.zhiharka", "com.whisperarts.tales.cinderella", "com.whisperarts.tales.spica", "com.whisperarts.tales.howagoatbuiltahut", "com.whisperarts.tales.therewasadog", "com.whisperarts.tales.twofrosts", "com.whisperarts.tales.crownking", "com.whisperarts.tales.priestandbald", "com.whisperarts.tales.sisteralenushka", "com.whisperarts.tales.pan", "com.whisperarts.tales.sivko", "com.whisperarts.tales.bychok", "com.whisperarts.tales.babayaga", "com.whisperarts.tales.ladushki", "com.whisperarts.tales.lisichkasoskalochkoi"));
        }

        private PACK(String packageName, int maxNumberOfInstalledTalesToIgnorePack, int bannerId, String... talesPackages) {
            this.tales = new ArrayList();
            this.isInstalledOrNotNeeded = false;
            this.packageName = packageName;
            this.maxNumberOfInstalledTalesToIgnorePack = maxNumberOfInstalledTalesToIgnorePack;
            this.bannerId = bannerId;
            this.tales.addAll(Arrays.asList(talesPackages));
            addToPack(packageName, this);
        }

        public static void addToPack(String packageName, PACK pack) {
            packs.put(packageName, pack);
        }

        public List<String> getTales() {
            return new ArrayList(this.tales);
        }

        public static void uninstall() {
            for (PACK pack : packs.values()) {
                pack.isInstalledOrNotNeeded = false;
            }
        }
    }

    public FullTaleInfo() {
        this.isPaid = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getFullPackage() {
        return this.fullPackage;
    }

    public void setFullPackage(String fullPackage) {
        this.fullPackage = fullPackage;
    }

    public int compareTo(FullTaleInfo another) {
        if (this.isPaid == another.isPaid) {
            if (this.price.equals(another.price)) {
                if (PACK.packs.containsKey(this.fullPackage) && PACK.packs.containsKey(another.fullPackage)) {
                    return this.name.compareTo(another.name);
                }
                return 0;
            } else if (PACK.packs.containsKey(this.fullPackage) && PACK.packs.containsKey(another.fullPackage)) {
                return this.price.compareTo(another.price);
            } else {
                if (PACK.packs.containsKey(this.fullPackage)) {
                    return -1;
                }
                if (PACK.packs.containsKey(another.fullPackage)) {
                    return 1;
                }
                if (this.price.equals("0")) {
                    return -1;
                }
                if (another.price.equals("0")) {
                    return 1;
                }
                return new Random().nextInt(3) - 1;
            }
        } else if (this.isPaid) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean isNeedUpgrade() {
        return this.needUpgrade;
    }

    public void setNeedUpgrade(boolean needUpgrade) {
        this.needUpgrade = needUpgrade;
    }
}

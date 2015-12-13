package com.whisperarts.tales.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import com.whisperarts.tales.entities.FullTaleInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.acra.ACRAConstants;

public class TalesInfoDAO {
    private static final String TALE_FOLDER_PREFIX = "tale_";
    private static AssetManager assetManager;
    private static Context context;
    private static Map<String, FullTaleInfo> talesInfo;

    public static void setContext(Context ctx) {
        context = ctx;
        assetManager = context.getAssets();
    }

    private static void readInfo() throws IOException {
        talesInfo = new HashMap();
        String[] files = assetManager.list(ACRAConstants.DEFAULT_STRING_VALUE);
        for (String name : files) {
            if (name.startsWith(TALE_FOLDER_PREFIX)) {
                FullTaleInfo taleInfo = new FullTaleInfo();
                InputStream is = assetManager.open(new StringBuilder(String.valueOf(name)).append("/image.png").toString());
                taleInfo.setImage(BitmapFactory.decodeStream(is));
                is.close();
                is = assetManager.open(new StringBuilder(String.valueOf(name)).append("/info.txt").toString());
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                taleInfo.setName(in.readLine());
                taleInfo.setDescription(in.readLine());
                taleInfo.setPrice(in.readLine());
                taleInfo.setFullPackage(in.readLine());
                in.close();
                is.close();
                talesInfo.put(taleInfo.getFullPackage(), taleInfo);
            }
        }
    }

    public static synchronized Map<String, FullTaleInfo> getTalesCatalog() throws IOException {
        Map hashMap;
        synchronized (TalesInfoDAO.class) {
            if (talesInfo == null) {
                readInfo();
            }
            hashMap = new HashMap(talesInfo);
        }
        return hashMap;
    }
}

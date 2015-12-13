package com.whisperarts.tales.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.whisperarts.tales.C0026R;
import com.whisperarts.tales.banners.BannerItem;
import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends BaseAdapter {
    int defaultItemBackground;
    private final Context galleryContext;
    private final List<Bitmap> imageBitmaps;
    Bitmap placeholder;

    public BannerAdapter(Context c, List<BannerItem> banners) {
        this.galleryContext = c;
        this.imageBitmaps = new ArrayList();
        for (int i = 0; i < banners.size(); i++) {
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeResource(c.getResources(), ((BannerItem) banners.get(i)).imageId);
            } catch (OutOfMemoryError e) {
                System.gc();
                try {
                    bitmap = BitmapFactory.decodeResource(c.getResources(), ((BannerItem) banners.get(i)).imageId);
                } catch (OutOfMemoryError e2) {
                    bitmap = null;
                }
            }
            if (bitmap != null) {
                this.imageBitmaps.add(bitmap);
            }
        }
        TypedArray styleAttrs = this.galleryContext.obtainStyledAttributes(C0026R.styleable.pic_gallery);
        this.defaultItemBackground = styleAttrs.getResourceId(0, 0);
        styleAttrs.recycle();
    }

    public int getCount() {
        return this.imageBitmaps.size();
    }

    public Object getItem(int position) {
        return Integer.valueOf(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(this.galleryContext);
        imageView.setImageBitmap((Bitmap) this.imageBitmaps.get(position));
        imageView.setLayoutParams(new LayoutParams(300, 200));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        imageView.setBackgroundResource(this.defaultItemBackground);
        return imageView;
    }

    public void setElements(List<BannerItem> list) {
    }
}

package com.whisperarts.library.common.animation.rotate;

import android.graphics.Bitmap;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/* compiled from: SwapView */
final class SwapViews implements Runnable {
    private final Bitmap backBitmap;
    private final ImageView image;
    private final Bitmap imageBitmap;
    private final boolean mIsFirstView;

    public SwapViews(boolean isFirstView, ImageView image, Bitmap imageBitmap, Bitmap backBitmap) {
        this.mIsFirstView = isFirstView;
        this.image = image;
        this.imageBitmap = imageBitmap;
        this.backBitmap = backBitmap;
    }

    public void run() {
        RotateAnimation rotation;
        float centerX = ((float) this.image.getWidth()) / 2.0f;
        float centerY = ((float) this.image.getHeight()) / 2.0f;
        if (this.mIsFirstView) {
            this.image.setImageBitmap(this.backBitmap);
            rotation = new RotateAnimation(-90.0f, 0.0f, centerX, centerY);
        } else {
            this.image.setImageBitmap(this.imageBitmap);
            rotation = new RotateAnimation(90.0f, 0.0f, centerX, centerY);
        }
        rotation.setDuration(350);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new DecelerateInterpolator());
        this.image.startAnimation(rotation);
    }
}

package com.whisperarts.library.common.animation.rotate;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public final class RotateAnimationListener implements AnimationListener {
    private Bitmap backBitmap;
    public ImageView image;
    private Bitmap imageBitmap;
    public boolean mCurrentView;

    public RotateAnimationListener(boolean currentView, ImageView image, Bitmap imageBitmap, Bitmap backBitmap) {
        this.mCurrentView = currentView;
        this.image = image;
        this.imageBitmap = imageBitmap;
        this.backBitmap = backBitmap;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.image.post(new SwapViews(this.mCurrentView, this.image, this.imageBitmap, this.backBitmap));
    }

    public void onAnimationRepeat(Animation animation) {
    }
}

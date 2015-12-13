package com.whisperarts.library.common.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public abstract class AbstractAnimation {
    private final Animation animation;
    private final View view;

    /* renamed from: com.whisperarts.library.common.animation.AbstractAnimation.1 */
    class C00401 extends AnimationAdapter {
        private final /* synthetic */ Runnable val$runnable;

        C00401(Runnable runnable) {
            this.val$runnable = runnable;
        }

        public void onAnimationEnd(Animation animation) {
            if (this.val$runnable != null) {
                this.val$runnable.run();
            }
        }
    }

    protected abstract int getAnimationResource();

    public AbstractAnimation(Context context, View view) {
        this.view = view;
        this.animation = AnimationUtils.loadAnimation(context, getAnimationResource());
    }

    public AbstractAnimation setFillAfter(boolean fillAfter) {
        this.animation.setFillAfter(fillAfter);
        return this;
    }

    public AbstractAnimation setRepeatCount(int repeatCount) {
        this.animation.setRepeatCount(repeatCount);
        return this;
    }

    public void play() {
        play(null);
    }

    public void play(Runnable runnable) {
        this.animation.setAnimationListener(new C00401(runnable));
        this.view.startAnimation(this.animation);
    }
}

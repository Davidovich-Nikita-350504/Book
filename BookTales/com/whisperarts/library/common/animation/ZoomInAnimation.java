package com.whisperarts.library.common.animation;

import android.content.Context;
import android.view.View;
import com.whisperarts.library.common.C0005R;

public class ZoomInAnimation extends AbstractAnimation {
    public ZoomInAnimation(Context context, View view) {
        super(context, view);
    }

    protected int getAnimationResource() {
        return C0005R.anim.zoom_in;
    }
}

package com.whisperarts.library.common.animation;

import android.content.Context;
import android.view.View;
import com.whisperarts.library.common.C0005R;

public class ZoomInAndBackOutAnimation extends AbstractAnimation {
    public ZoomInAndBackOutAnimation(Context context, View view) {
        super(context, view);
    }

    protected int getAnimationResource() {
        return C0005R.anim.zoom_in_and_back_out;
    }
}

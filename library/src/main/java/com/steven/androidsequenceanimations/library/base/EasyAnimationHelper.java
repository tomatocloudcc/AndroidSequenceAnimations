package com.steven.androidsequenceanimations.library.base;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by sulei on 2018/6/12.
 */
public class EasyAnimationHelper {

    /**
     * reset view to default status
     * @param view
     */
    public static void reset(View view)
    {
        ViewCompat.setAlpha(view, 1);
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setScaleY(view, 1);
        ViewCompat.setTranslationX(view, 0);
        ViewCompat.setTranslationY(view, 0);
        ViewCompat.setRotation(view, 0);
        ViewCompat.setRotationY(view, 0);
        ViewCompat.setRotationX(view, 0);
    }
}

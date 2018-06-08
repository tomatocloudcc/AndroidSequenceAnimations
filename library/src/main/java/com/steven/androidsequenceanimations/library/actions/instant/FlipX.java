package com.steven.androidsequenceanimations.library.actions.instant;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by sulei on 2018/6/8.
 */

public class FlipX extends InstantAction
{
    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        return new Animator[]{ObjectAnimator.ofFloat(target, "rotationY", 180)};
    }
}

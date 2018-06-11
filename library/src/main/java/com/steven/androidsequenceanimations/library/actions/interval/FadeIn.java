package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by sulei on 2018/6/7.
 */
public class FadeIn extends IntervalAction
{
    private float mToAlpha = 1;
    public FadeIn(long duration)
    {
        super(duration);
        this.mToAlpha = 1.0f;
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, PropertyValuesHolder.ofFloat("alpha" ,this.mToAlpha));
        return new Animator[]{animator};
    }

}

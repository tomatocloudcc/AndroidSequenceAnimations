package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.FloatRange;
import android.view.View;

/**
 * Created by sulei on 2018/6/7.
 */
public class AlphaTo extends IntervalAction
{
    private float mToAlpha = 1;
    public AlphaTo(long duration, @FloatRange(from = 0, to = 1.0) float alpha)
    {
        this.initDuration(duration);
        this.mToAlpha = alpha;
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, PropertyValuesHolder.ofFloat("alpha" ,this.mToAlpha));
        return new Animator[]{animator};
    }

}

package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by sulei on 2018/6/8.
 */
public class DelayTime extends IntervalAction
{
    /**
     * @param delayTime unit:millis
     */
    public DelayTime(long delayTime)
    {
        this.initDuration(delayTime);
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet)
    {
        ValueAnimator animator = ValueAnimator.ofInt(0);
        return new Animator[]{animator};
    }
}

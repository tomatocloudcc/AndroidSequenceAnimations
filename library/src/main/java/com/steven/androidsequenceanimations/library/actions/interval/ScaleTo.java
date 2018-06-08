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
public class ScaleTo extends IntervalAction
{

    private float scaleX = 1.0f;
    private float scaleY = 1.0f;


    public ScaleTo(long duration,  float scaleXY)
    {
        this(duration, scaleXY, scaleXY);
    }

    public ScaleTo(long duration, float scaleX,  float scaleY)
    {
        this.initDuration(duration);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, PropertyValuesHolder.ofFloat("scaleX",this.scaleX), PropertyValuesHolder.ofFloat("scaleY",this.scaleY));
        return  new Animator[]{animator};
    }
}

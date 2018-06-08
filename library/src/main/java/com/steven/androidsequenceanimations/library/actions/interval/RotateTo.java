package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * Created by sulei on 2018/6/7.
 */
public class RotateTo extends IntervalAction
{
    private int rotationX;
    private int rotationY;

    /**
     * @param duration
     * @param rotationXY 360 degrees equals a circle, default is 0
     */
    public RotateTo(long duration, int rotationXY)
    {
        this(duration, rotationXY, rotationXY);
    }

    /**
     *
     * @param duration
     * @param rotationX 360 degrees equals a circle, default is 0
     * @param rotationY 360 degrees equals a circle, default is 0
     */
    public RotateTo(long duration, int rotationX,  int rotationY)
    {
        this.initDuration(duration);
        this.rotationX = rotationX;
        this.rotationY = rotationY;
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, PropertyValuesHolder.ofFloat("rotationX",this.rotationX), PropertyValuesHolder.ofFloat("rotationY",this.rotationY));
        return  new Animator[]{animator};
    }
}

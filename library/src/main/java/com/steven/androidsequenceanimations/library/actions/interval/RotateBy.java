package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * Created by sulei on 2018/6/7.
 */
public class RotateBy extends IntervalAction
{
    protected int rotationX;
    protected int rotationY;
    protected boolean isBy = true;

    /**
     * @param duration
     * @param rotationXY 360 degrees equals a circle, default is 0
     */
    public RotateBy(long duration, int rotationXY)
    {
        this(duration, rotationXY, rotationXY);
    }

    /**
     *
     * @param duration
     * @param rotationX 360 degrees equals a circle, default is 0
     * @param rotationY 360 degrees equals a circle, default is 0
     */
    public RotateBy(long duration, int rotationX, int rotationY)
    {
        super(duration);
        this.rotationX = rotationX;
        this.rotationY = rotationY;
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        float rx = isBy ? target.getRotationX() + this.rotationX : this.rotationX;
        float ry = isBy ? target.getRotationY() + this.rotationY : this.rotationY;

        PropertyValuesHolder.ofFloat("skew11X",rx);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, PropertyValuesHolder.ofFloat("rotationX",rx), PropertyValuesHolder.ofFloat("rotationY",ry));
        return  new Animator[]{animator};
    }
}

package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * Created by sulei on 2018/6/11.
 */
public class MoveBy extends IntervalAction
{
    protected float mX;
    protected float mY;
    protected boolean isByX = true;
    protected boolean isByY = true;

    public MoveBy(long duration, float x, float y)
    {
        super(duration);
        this.mX = x;
        this.mY = y;
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        float endX = isByX ? target.getX() + this.mX : this.mX;
        float endY = isByY ? target.getY() + this.mY : this.mY;

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, PropertyValuesHolder.ofFloat("x", endX), PropertyValuesHolder.ofFloat("y", endY));
        return  new Animator[]{animator};
    }
}

package com.steven.androidsequenceanimations.library.actions.interval.update;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.interval.IntervalAction;

/**
 * Created by sulei on 2018/6/23.
 */
public class ValueFloatTo extends IntervalAction
{

    public interface IValueChange
    {
        void onChanged(View target, float value);
    }

    private float mFromValue;
    private float mToValue;
    private IValueChange mCallback;
    public ValueFloatTo(long duration,float fromValue, float toValue, IValueChange callback) {
        super(duration);
        this.mFromValue = fromValue;
        this.mToValue = toValue;
        this.mCallback = callback;
    }

    @Override
    protected Animator[] prepare(final View target, AnimatorSet animatorSet) {

        ValueAnimator animator = ValueAnimator.ofFloat(mFromValue, mToValue);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(null != mCallback)
                {
                    mCallback.onChanged(target, (Float) animation.getAnimatedValue());
                }
            }
        });
        return  new Animator[]{animator};
    }
}

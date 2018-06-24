package com.steven.androidsequenceanimations.library.actions.interval.update;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.interval.IntervalAction;

/**
 * Created by sulei on 2018/6/23.
 */
public class ValueIntTo extends IntervalAction
{

    public interface IValueChange
    {
        void onChanged(View target, int value);
    }

    private int mFromValue;
    private int mToValue;
    private IValueChange mCallback;
    public ValueIntTo(long duration, int fromValue, int toValue, IValueChange callback) {
        super(duration);
        this.mFromValue = fromValue;
        this.mToValue = toValue;
        this.mCallback = callback;
    }

    @Override
    protected Animator[] prepare(final View target, AnimatorSet animatorSet) {

        ValueAnimator animator = ValueAnimator.ofInt(mFromValue, mToValue);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(null != mCallback)
                {
                    mCallback.onChanged(target, (Integer) animation.getAnimatedValue());
                }
            }
        });
        return  new Animator[]{animator};
    }
}

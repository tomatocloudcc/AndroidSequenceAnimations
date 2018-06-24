package com.steven.androidsequenceanimations.library.actions.interval.update;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.interval.IntervalAction;

/**
 * Created by sulei on 2018/6/23.
 */
public class ColorTo extends IntervalAction
{

    public interface IColorChange
    {
        void onChanged(View target, int color);
    }

    private int mFromColor;
    private int mToColor;
    private IColorChange mCallback;
    public ColorTo(long duration, int fromColor, int toColor, IColorChange callback) {
        super(duration);
        this.mFromColor = fromColor;
        this.mToColor = toColor;
        this.mCallback = callback;
    }

    @Override
    protected Animator[] prepare(final View target, AnimatorSet animatorSet) {

        ValueAnimator animator = ValueAnimator.ofInt(this.mFromColor, this.mToColor);
        animator.setEvaluator(ArgbEvaluator.getInstance());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curColor = (int) animation.getAnimatedValue();
                if(null != mCallback)
                {
                    mCallback.onChanged(target, curColor);
                }
            }
        });
        return new Animator[]{animator};
    }

    static class ArgbEvaluator implements TypeEvaluator {
        private static final ArgbEvaluator sInstance = new ArgbEvaluator();

        static ArgbEvaluator getInstance() {
            return sInstance;
        }

        private ArgbEvaluator()
        {
        }

        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24) & 0xff;
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24) & 0xff;
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (startA + (int) (fraction * (endA - startA))) << 24 |
                    (startR + (int) (fraction * (endR - startR))) << 16 |
                    (startG + (int) (fraction * (endG - startG))) << 8 |
                    (startB + (int) (fraction * (endB - startB)));
        }
    }
}

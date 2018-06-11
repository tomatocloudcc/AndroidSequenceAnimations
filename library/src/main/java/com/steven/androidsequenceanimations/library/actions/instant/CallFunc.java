package com.steven.androidsequenceanimations.library.actions.instant;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by sulei on 2018/6/7.
 */
public class CallFunc extends InstantAction
{

    public interface Caller
    {
        void onCall(View target);
    }

    private Caller mCaller = null;
    public CallFunc(Caller caller)
    {
        super();
        this.mCaller = caller;
    }

    @Override
    protected Animator[] prepare(final View target, AnimatorSet animatorSet) {
        ValueAnimator animator = ValueAnimator.ofInt(0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(null != mCaller)
                {
                    mCaller.onCall(target);
                }
            }
        });
        return new Animator[]{animator};
    }
}

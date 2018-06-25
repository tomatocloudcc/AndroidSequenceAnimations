package com.steven.androidsequenceanimations.library.actions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.interval.DelayTime;
import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/24.
 */
public class Repeat extends ContainerAction
{
    private int mOriRepeatCount;
    private int mRepeatCount;
    private AnimatorSet mCurAnimatorSet;
    public Repeat(final BaseAction action, int repeatCount)
    {
        this.mOriRepeatCount = repeatCount;
        this.mRepeatCount = repeatCount;

        long repeatAllDuration = mRepeatCount*action.getDuration() + action.getDuration();
        if(repeatAllDuration<0) // beyond limit
        {
            repeatAllDuration = Long.MAX_VALUE;
        }
        this.initDuration(mRepeatCount==-1 ? Long.MAX_VALUE : repeatAllDuration);

        long repeatDuration = mRepeatCount*action.getDuration();
        if(repeatDuration<0) // beyond limit
        {
            repeatDuration = Long.MAX_VALUE;
        }
        initWithTwoActions(action, new DelayTime(mRepeatCount==-1 ? Long.MAX_VALUE : repeatDuration));
    }

    @Override
    public void cancel() {
        super.cancel();
        if(null != mCurAnimatorSet)
        {
            mCurAnimatorSet.cancel();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void pause() {
        if(null != mCurAnimatorSet)
        {
            mCurAnimatorSet.pause();
        }
        super.pause();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void resume() {
        if(null != mCurAnimatorSet)
        {
            mCurAnimatorSet.resume();
        }
        super.resume();
    }

    private void doRepeat(final View target, final AnimatorSet animatorSet, final Animator animator)
    {
        if(this.mCanceled || this.mPaused)
        {
            return;
        }

        if(mRepeatCount==0 )
        {
            mRepeatCount = mOriRepeatCount; // reset repeatCount for recycle
            return;
        }

        if(mRepeatCount!=-1)
        {
            --mRepeatCount;
        }
        animatorSet.play(animator);
        animatorSet.start();

        addRepeatListener(target, animator);
    }

    private void addRepeatListener(final View target, final Animator animator)
    {
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator.removeAllListeners();

                mCurAnimatorSet = new AnimatorSet();
                Animator[] animators1 = action1.doPrepare(target, mCurAnimatorSet);

                final Animator animator1;
                if(action1 instanceof Together)
                {
                    animator1 = animators1[0].getDuration() > animators1[1].getDuration() ? animators1[0] : animators1[1];
                }else
                {
                    animator1 = animators1.length >= 2 ? animators1[1] : animators1[0];
                }

                doRepeat(target, mCurAnimatorSet, animator1);
            }
        });
    }

    @Override
    protected Animator[] prepare(final View target, final AnimatorSet animatorSet) {
        Animator[] animators1 = this.action1.doPrepare(target, animatorSet);
        Animator[] animators2 = this.action2.doPrepare(target, animatorSet);

        final Animator animator1;
        if(this.action1 instanceof Together)
        {
            animator1 = animators1[0].getDuration() > animators1[1].getDuration() ? animators1[0] : animators1[1];
        }else
        {
            animator1 = animators1.length >= 2 ? animators1[1] : animators1[0];
        }

        addRepeatListener(target, animator1);
        Animator animator2 = animators2[0];
        animatorSet.play(animator1).before(animator2);
        return new Animator[]{animator1, animator2};
    }
}

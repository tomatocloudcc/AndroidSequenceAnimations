package com.steven.androidsequenceanimations.library.actions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.interval.DelayTime;
import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/24.
 */
public class Repeat extends ContainerAction
{

    private int mRepeatCount;
    private AnimatorSet mCurAnimatorSet;
    public Repeat(final BaseAction action, int repeatCount)
    {
        this.mRepeatCount = repeatCount;
        this.initDuration(mRepeatCount*action.getDuration() + action.getDuration());
        initWithTwoActions(action, new DelayTime(mRepeatCount*action.getDuration()));
    }

    @Override
    public void cancel() {
        super.cancel();
        if(null != mCurAnimatorSet)
        {
            mCurAnimatorSet.cancel();
        }
    }

    private void doRepeat(final View target, final AnimatorSet animatorSet, final Animator animator)
    {
        if(mRepeatCount==0 || this.mCanceled)
        {
            return;
        }

        --mRepeatCount;
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
                System.out.println("anim repeat end2");
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

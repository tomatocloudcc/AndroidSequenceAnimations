package com.steven.androidsequenceanimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.Together;
import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/7.
 */

public class RepeatAnimator extends BaseAction
{
    private BaseAction action;

    private int repeatTimes;
    public RepeatAnimator(BaseAction action, int repeatTimes)
    {
        this.action = action;
        this.repeatTimes = repeatTimes;
    }


    @Override
    protected Animator[] prepare(final View target, final AnimatorSet animatorSet) {

        final Animator[] animators = this.action.doPrepare(target, animatorSet);

        final Animator animator;
        if(this.action instanceof Together)
        {
            Together togetherAnimator = (Together)this.action;
            animator = togetherAnimator.getAction1Duration() > togetherAnimator.getAction2Duration() ? animators[0] : animators[1];
        }else
        {
            animator = animators.length>=2 ? animators[1] : animators[0];
        }

        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("anim end");

                animator.removeAllListeners();
                if(repeatTimes>0)
                {
                    reset(target);
                    doPrepare(target, animatorSet);

                    animatorSet.start();
                    --repeatTimes;
                }
            }
        });
        return new Animator[]{animator};
    }
}

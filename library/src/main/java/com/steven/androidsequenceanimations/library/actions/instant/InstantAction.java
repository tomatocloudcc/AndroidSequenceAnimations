package com.steven.androidsequenceanimations.library.actions.instant;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/8.
 */
public abstract class InstantAction extends BaseAction
{
    public InstantAction()
    {
        this.initDuration(0);
    }

    @Override
    protected void onPrepared(View target, AnimatorSet animatorSet, Animator[] animators) {
        super.onPrepared(target, animatorSet, animators);
        Animator animator = animators[0];
        animator.setDuration(this.getDuration());
        animatorSet.play(animator);
    }
}

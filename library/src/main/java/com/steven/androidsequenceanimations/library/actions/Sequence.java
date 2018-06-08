package com.steven.androidsequenceanimations.library.actions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.instant.Empty;
import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/7.
 */

public class Sequence extends ContainerAction
{
    public Sequence(BaseAction... animators)
    {
        if(animators.length==0)
        {
            throw new RuntimeException("Sequence action params can't be empty");
        }

        BaseAction pre = animators[0];
        int last = animators.length-1;
        for(int i=1;i<last;++i)
        {
            BaseAction now = animators[i];
            BaseAction action1 = pre;
            pre = new Sequence(action1, now);
        }

        if(last>0)
        {
            this.initWithTwoActions(pre, animators[last]);
        }
        else
        {
            this.initWithTwoActions(pre, new Empty());
        }
    }

    private Sequence(BaseAction action1, BaseAction action2) {
        this.initWithTwoActions(action1, action2);
    }

    @Override
    protected void initWithTwoActions(BaseAction action1, BaseAction action2) {
        this.initDuration(action1.getDuration() + action2.getDuration());
        super.initWithTwoActions(action1, action2);
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {

        Animator[] animators1 = this.action1.doPrepare(target, animatorSet);
        Animator[] animators2 = this.action2.doPrepare(target, animatorSet);

        Animator animator1 =  animators1.length >= 2 ? animators1[1] : animators1[0];
        Animator animator2 =  animators2.length >= 2 ? animators2[1] : animators2[0];
        animatorSet.play(animator1).before(animator2);
        return new Animator[]{animator1, animator2};
    }


}

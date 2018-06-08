package com.steven.androidsequenceanimations.library.actions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.instant.Empty;
import com.steven.androidsequenceanimations.library.actions.interval.DelayTime;
import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Animations container, all wrapped animations will parallel running
 * Created by sulei on 2018/6/7.
 */
public class Together extends ContainerAction
{

    private Together(BaseAction action1, BaseAction action2) {
        this.initWithTwoActions(action1, action2);
    }

    private Together()
    {
    }

    public Together(BaseAction... animators) {
        if(animators.length==0)
        {
            throw new RuntimeException("Together action params can't be empty");
        }

        BaseAction pre = animators[0];
        int last = animators.length-1;
        for(int i=1;i<last;++i)
        {
            BaseAction now = animators[i];
            BaseAction action1 = pre;
            pre = new Together(action1, now);
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

    @Override
    protected void initWithTwoActions(BaseAction action1, BaseAction action2)  {
        this.initDuration(Math.max(action1.getDuration(), action2.getDuration())); // init duration

        // make action1 and action2 duration equal
        if(action1.getDuration() < action2.getDuration())
        {
            action1 = new Sequence(action1, new DelayTime(action2.getDuration() - action1.getDuration()));
        }else if(action1.getDuration() > action2.getDuration())
        {
            action2 = new Sequence(action2, new DelayTime(action1.getDuration() - action2.getDuration()));
        }

        super.initWithTwoActions(action1, action2);
    }

    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        Animator[] animators1 = this.action1.doPrepare(target, animatorSet);
        Animator[] animators2 = this.action2.doPrepare(target, animatorSet);

        Animator animator1 = (this.action1 instanceof ContainerAction) ? ((ContainerAction) this.action1).getPreparedFirstAnimator() : animators1[0];
        Animator animator2 = (this.action2 instanceof ContainerAction) ? ((ContainerAction) this.action2).getPreparedFirstAnimator() : animators2[0];

        animatorSet.playTogether(animator1, animator2);
        return new Animator[]{animator1, animator2};
    }

    public long getAction1Duration()
    {
        return this.action1.getDuration();
    }

    public long getAction2Duration()
    {
        return this.action2.getDuration();
    }
}

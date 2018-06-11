package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

import com.daimajia.easing.BaseEasingMethod;
import com.daimajia.easing.Skill;
import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/8.
 */
public abstract class IntervalAction extends BaseAction
{

    protected Skill mEasingType = null;

    IntervalAction(long duration)
    {
        this.initDuration(duration);
    }

    /**
     * set easing type
     * @param easingType
     * @return
     */
    public IntervalAction easing(Skill easingType)
    {
        this.mEasingType = easingType;
        return this;
    }

    protected ValueAnimator bindEasingType(ValueAnimator animator) {

        if(this.mEasingType != null)
        {
            BaseEasingMethod t = this.mEasingType.getMethod(this.getDuration());
            animator.setEvaluator(t);
        }
        return animator;
    }

    protected PropertyValuesHolder bindEasingType(PropertyValuesHolder propertyValuesHolder) {
        if(this.mEasingType != null)
        {
            BaseEasingMethod t = this.mEasingType.getMethod(this.getDuration());
            propertyValuesHolder.setEvaluator(t);
        }
        return propertyValuesHolder;
    }

    @Override
    protected void onPrepared(View target, AnimatorSet animatorSet, Animator[] animators) {
        super.onPrepared(target, animatorSet, animators);
        Animator animator = animators[0];

        // bind all easing
        if(animator instanceof ValueAnimator)
        {
            for( PropertyValuesHolder anim : ((ValueAnimator) animator).getValues())
            {
                this.bindEasingType(anim);
            }
        }
        animator.setDuration(this.getDuration());
        animatorSet.play(animator);
    }
}

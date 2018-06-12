/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.steven.androidsequenceanimations.library.base;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.FloatRange;
import android.view.View;

/**
 * the base action, all action extends from it
 * Created by sulei on 2018/6/7.
 */
public abstract class BaseAction {

    protected abstract Animator[] prepare(View target, AnimatorSet animatorSet);

    private long mDuration = 0;

    private Animator mPreparedAnimator = null;

    protected View mTarget = null;

    protected void initDuration(long duration)
    {
        this.mDuration = duration;
    }

    /**
     * prepare animations, called by play or inner actions
     * @param target
     * @param animatorSet
     * @return
     */
    public Animator[] doPrepare(View target, AnimatorSet animatorSet)
    {
        View realTarget;
        if(this.mTarget != null)
        {
            realTarget = this.mTarget;
        }
        else
        {
            realTarget = target;
        }
        Animator[] animators = this.prepare(realTarget, animatorSet);
        this.onPrepared(realTarget, animatorSet, animators);
        return animators;
    }

    protected Animator getPreparedAnimator()
    {
        return this.mPreparedAnimator;
    }

    /**
     * prepare finish
     * @param target
     * @param animatorSet
     * @param animators
     */
    protected void onPrepared(View target, AnimatorSet animatorSet, Animator[] animators)
    {
        this.mPreparedAnimator = animators[0];
    }

    /**
     * bind executor and prepare animations
     * @param target
     * @param animatorSet
     * @param pivotX
     * @param pivotY
     * @return
     */
    protected BaseAction bindTargetAndPrepare(View target, AnimatorSet animatorSet,@FloatRange(from = 0, to = 1)  float pivotX,@FloatRange(from = 0, to = 1) float pivotY) {
        this.setPivot(this.mTarget, pivotX, pivotY);
        doPrepare(target, animatorSet);
        return this;
    }

    /**
     * bind executor view
     * @param target
     * @return
     */
    public BaseAction bindTarget(View target)
    {
        return this.bindTarget(target, 0.5f, 0.5f);
    }

    /**
     * bind executor view
     * @param target
     * @param pivotX
     * @param pivotY
     * @return
     */
    public BaseAction bindTarget(View target, @FloatRange(from = 0, to = 1)  float pivotX,@FloatRange(from = 0, to = 1) float pivotY)
    {
        this.mTarget = target;
        this.setPivot(this.mTarget, pivotX, pivotY);
        return this;
    }


    public long getDuration() {
        return mDuration;
    }

    private void setPivot(View target, float pivotX, float pivotY)
    {
        if(null != target)
        {
            target.setPivotX(pivotX * target.getMeasuredWidth());
            target.setPivotY(pivotY * target.getMeasuredHeight());
        }
    }
}

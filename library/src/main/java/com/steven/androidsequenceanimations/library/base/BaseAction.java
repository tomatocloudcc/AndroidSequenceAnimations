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
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * the base action, all action extends from it
 * Created by sulei on 2018/6/7.
 */
public abstract class BaseAction {

    protected abstract Animator[] prepare(View target, AnimatorSet animatorSet);

    private long mDuration = 0;

    private Animator mPreparedAnimator = null;

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
        Animator[] animators = this.prepare(target, animatorSet);
        this.onPrepared(target, animatorSet, animators);
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
     * @return
     */
    public BaseAction bindTargetAndPrepare(View target, AnimatorSet animatorSet) {
        reset(target);
        doPrepare(target, animatorSet);

        return this;
    }

    /**
     * reset the view to default status
     *
     * @param target
     */
    public void reset(View target) {
        ViewCompat.setAlpha(target, 1);
        ViewCompat.setScaleX(target, 1);
        ViewCompat.setScaleY(target, 1);
        ViewCompat.setTranslationX(target, 0);
        ViewCompat.setTranslationY(target, 0);
        ViewCompat.setRotation(target, 0);
        ViewCompat.setRotationY(target, 0);
        ViewCompat.setRotationX(target, 0);
    }

    public long getDuration() {
        return mDuration;
    }

}

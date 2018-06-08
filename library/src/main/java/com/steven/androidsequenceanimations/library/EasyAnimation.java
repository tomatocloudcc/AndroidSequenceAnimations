
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

package com.steven.androidsequenceanimations.library;

import android.animation.AnimatorSet;
import android.support.annotation.FloatRange;
import android.view.View;

import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * EasyAnimation api, combine your anim with this
 */
public class EasyAnimation {

    private BaseAction animator;
    private float pivotX, pivotY;
    private View target;

    private EasyAnimation(AnimationComposer animationComposer) {
        animator = animationComposer.animator;
        pivotX = animationComposer.pivotX;
        pivotY = animationComposer.pivotY;
        target = animationComposer.target;
    }

    public static AnimationComposer with(BaseAction animator) {
        return new AnimationComposer(animator);
    }

    public static final class AnimationComposer {

        private BaseAction animator;

        private float pivotX = 0.5f, pivotY = 0.5f;
        private View target;

        private AnimationComposer(BaseAction animator) {
            this.animator = animator;
        }

        public AnimationComposer pivot(@FloatRange(from = 0f, to = 1f)float pivotX, @FloatRange(from = 0f, to = 1f) float pivotY) {
            this.pivotX = pivotX;
            this.pivotY = pivotY;
            return this;
        }

        public AnimationComposer pivotX(@FloatRange(from = 0f, to = 1f) float pivotX) {
            this.pivotX = pivotX;
            return this;
        }

        public AnimationComposer pivotY(@FloatRange(from = 0f, to = 1f)float pivotY) {
            this.pivotY = pivotY;
            return this;
        }

        public EasyAnimationWrapper playOn(View target) {
            this.target = target;
            return new EasyAnimation(this).play();
        }
    }

    public static final class EasyAnimationWrapper {

        private BaseAction animator;
        private View target;
        private AnimatorSet animatorSet;

        private EasyAnimationWrapper(BaseAction animator, View target, AnimatorSet animatorSet) {
            this.target = target;
            this.animator = animator;
            this.animatorSet = animatorSet;
        }

        /**
         * stop all animations
         */
        public void stop()
        {
            if(animatorSet.isRunning())
            {
                this.animatorSet.cancel();
            }
        }

        public boolean isRunning()
        {
            return this.animatorSet.isRunning();
        }
    }

    private EasyAnimationWrapper play() {
        AnimatorSet animSet = new AnimatorSet();
        animator.bindTargetAndPrepare(target, animSet);

        target.setPivotX(pivotX * target.getMeasuredWidth());
        target.setPivotY(pivotY * target.getMeasuredHeight());

        animSet.start();
        return new EasyAnimationWrapper(animator, target, animSet);
    }

}

package com.steven.androidsequenceanimations.library.base;

import android.animation.AnimatorSet;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.view.View;

import com.steven.androidsequenceanimations.library.actions.Sequence;
import com.steven.androidsequenceanimations.library.actions.Together;
import com.steven.androidsequenceanimations.library.actions.instant.CallFunc;
import com.steven.androidsequenceanimations.library.actions.instant.FlipX;
import com.steven.androidsequenceanimations.library.actions.instant.FlipY;
import com.steven.androidsequenceanimations.library.actions.instant.Hide;
import com.steven.androidsequenceanimations.library.actions.instant.Show;
import com.steven.androidsequenceanimations.library.actions.interval.DelayTime;
import com.steven.androidsequenceanimations.library.actions.interval.FadeIn;
import com.steven.androidsequenceanimations.library.actions.interval.FadeOut;
import com.steven.androidsequenceanimations.library.actions.interval.FadeTo;
import com.steven.androidsequenceanimations.library.actions.interval.MoveBy;
import com.steven.androidsequenceanimations.library.actions.interval.MoveTo;
import com.steven.androidsequenceanimations.library.actions.interval.RotateBy;
import com.steven.androidsequenceanimations.library.actions.interval.RotateTo;
import com.steven.androidsequenceanimations.library.actions.interval.ScaleTo;
import com.steven.androidsequenceanimations.library.actions.interval.update.ColorTo;
import com.steven.androidsequenceanimations.library.actions.interval.update.ValueFloatTo;
import com.steven.androidsequenceanimations.library.actions.interval.update.ValueIntTo;

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

        private BaseAction action;
        private View target;
        private AnimatorSet animatorSet;

        private EasyAnimationWrapper(BaseAction action, View target, AnimatorSet animatorSet) {
            this.target = target;
            this.action = action;
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
                this.action.cancel();
            }
        }

        public boolean isRunning()
        {
            return this.animatorSet.isRunning();
        }
    }


    private EasyAnimationWrapper play() {
        AnimatorSet animSet = new AnimatorSet();
        animator.bindTargetAndPrepare(target, animSet, this.pivotX, this.pivotY);

        animSet.start();
        return new EasyAnimationWrapper(animator, target, animSet);
    }


    // APIs
    public static Sequence sequence(@NonNull BaseAction... baseActions) {
        return new Sequence(baseActions);
    }

    public static Together together(@NonNull BaseAction... baseActions) {
        return new Together(baseActions);
    }

    public static MoveTo moveTo(long duration, float x, float y)
    {
        return new MoveTo(duration, x, y);
    }

    public static MoveBy moveBy(long duration, float x, float y)
    {
        return new MoveBy(duration, x, y);
    }

    public static RotateTo rotateTo(long duration, int rotationX,  int rotationY)
    {
        return new RotateTo(duration,rotationX,rotationY);
    }

    public static RotateTo rotateTo(long duration, int rotationXY)
    {
        return new RotateTo(duration,rotationXY);
    }

    public static RotateBy rotateBy(long duration, int rotationX,  int rotationY)
    {
        return new RotateBy(duration,rotationX,rotationY);
    }

    public static RotateBy rotateBy(long duration, int rotationXY)
    {
        return new RotateBy(duration,rotationXY);
    }

    public static ScaleTo scaleTo(long duration, float scaleXY)
    {
        return new ScaleTo(duration, scaleXY);
    }

    public static ScaleTo scaleTo(long duration, float scaleX,  float scaleY)
    {
        return new ScaleTo(duration, scaleX, scaleY);
    }

    public static FadeIn scaleTo(long duration)
    {
        return new FadeIn(duration);
    }

    public static FadeOut fadeOut(long duration)
    {
        return new FadeOut(duration);
    }

    public static FadeIn fadeIn(long duration)
    {
        return new FadeIn(duration);
    }

    public static FadeTo fadeTo(long duration, @FloatRange(from = 0, to = 1.0) float alpha)
    {
        return new FadeTo(duration, alpha);
    }

    public static DelayTime delayTime(long duration)
    {
        return new DelayTime(duration);
    }

    public static ValueFloatTo valueFloatTo(long duration, float fromValue, float toValue, ValueFloatTo.IValueChange callback)
    {
        return new ValueFloatTo(duration, fromValue, toValue, callback);
    }

    public static ValueIntTo valueIntTo(long duration, int fromValue, int toValue, ValueIntTo.IValueChange callback)
    {
        return new ValueIntTo(duration, fromValue, toValue, callback);
    }

    public static ColorTo colorTo(long duration, int fromColor, int toColor, ColorTo.IColorChange callback)
    {
        return new ColorTo(duration, fromColor, toColor, callback);
    }

    public static Hide hide()
    {
        return new Hide();
    }

    public static Show show()
    {
        return new Show();
    }

    public static FlipX flipX()
    {
        return new FlipX();
    }

    public static FlipY flipY()
    {
        return new FlipY();
    }

    public static CallFunc callFunc(@NonNull CallFunc.Caller caller)
    {
        return new CallFunc(caller);
    }



}

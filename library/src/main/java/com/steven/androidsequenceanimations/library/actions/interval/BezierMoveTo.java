package com.steven.androidsequenceanimations.library.actions.interval;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.view.View;

/**
 * Created by sulei on 2018/6/11.
 * TODO
 */
public class BezierMoveTo extends MoveTo  {


    public BezierMoveTo(long duration, float x, float y)
    {
        super(duration,x,y);
    }


    @Override
    protected Animator[] prepare(View target, AnimatorSet animatorSet) {
        Animator[] animator =  super.prepare(target, animatorSet);
        int pointX = (int) ((this.mX + this.mY) / 2) ;
        int pointY = (int) (this.mY - 100);
        final Point controlPoint = new Point(pointX, pointY);

        if(animator[0] instanceof ValueAnimator)
        {
            for( PropertyValuesHolder anim : ((ValueAnimator) animator[0]).getValues())
            {
                if("x".equalsIgnoreCase(anim.getPropertyName()) )
                {
                    anim.setEvaluator(new TypeEvaluator<Float>(){

                        @Override
                        public Float evaluate(float t, Float startValue, Float endValue) {
                            return  ((1 - t) * (1 - t) * startValue + 2 * t * (1 - t) * controlPoint.x + t * t * endValue);
                        }
                    });
                }else if("y".equalsIgnoreCase(anim.getPropertyName()))
                {
                    anim.setEvaluator(new TypeEvaluator<Float>(){

                        @Override
                        public Float evaluate(float t, Float startValue, Float endValue) {
                            return  ((1 - t) * (1 - t) * startValue + 2 * t * (1 - t) * controlPoint.y + t * t * endValue);
                        }
                    });
                }
            }
        }
        return animator;
    }

}

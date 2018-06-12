package com.steven.androidsequenceanimations;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;

import com.daimajia.easing.Skill;
import com.steven.androidsequenceanimations.library.base.EasyAnimation;

import static com.steven.androidsequenceanimations.library.base.EasyAnimation.delayTime;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeIn;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeOut;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.moveTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.scaleTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.sequence;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.together;

/**
 * Created by sulei on 2018/6/11.
 */
public class Anim2Activity extends Activity
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_layout2);

        final View layoutTimeDown = findViewById(R.id.layoutTimeDown);
        layoutTimeDown.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                resetView();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layoutTimeDown.getViewTreeObserver()
                            .removeOnGlobalLayoutListener(this);
                }
            }
        });

    }

    private void resetView()
    {
        View layoutTimeDown = findViewById(R.id.layoutTimeDown);

        View tvTime3 = findViewById(R.id.tvTime3);
        View tvTime2 = findViewById(R.id.tvTime2);
        View tvTime1 = findViewById(R.id.tvTime1);

        tvTime3.setAlpha(0);
        tvTime2.setAlpha(0);
        tvTime1.setAlpha(0);

        tvTime3.setScaleX(0);
        tvTime3.setScaleY(0);
        tvTime2.setScaleX(0);
        tvTime2.setScaleY(0);
        tvTime1.setScaleX(0);
        tvTime1.setScaleY(0);

        Point centPoint = new Point((((View)layoutTimeDown.getParent()).getMeasuredWidth() - layoutTimeDown.getMeasuredWidth()) / 2, (((View)layoutTimeDown.getParent()).getMeasuredHeight() - layoutTimeDown.getMeasuredHeight()) / 2);
        layoutTimeDown.setX(centPoint.x);
        layoutTimeDown.setY(((View)layoutTimeDown.getParent()).getMeasuredHeight());
        layoutTimeDown.setScaleX(1);
        layoutTimeDown.setScaleY(1);
        layoutTimeDown.setAlpha(1);
    }

    public void startAnim(View view) {

        resetView();

        View layoutTimeDown = findViewById(R.id.layoutTimeDown);
        View tvTime3 = findViewById(R.id.tvTime3);
        View tvTime2 = findViewById(R.id.tvTime2);
        View tvTime1 = findViewById(R.id.tvTime1);

        Point centPoint = new Point((((View)layoutTimeDown.getParent()).getMeasuredWidth() - layoutTimeDown.getMeasuredWidth()) / 2, (((View)layoutTimeDown.getParent()).getMeasuredHeight() - layoutTimeDown.getMeasuredHeight()) / 2);

        EasyAnimation.with(
                sequence(
                    moveTo(800, centPoint.x, centPoint.y).easing(Skill.BounceEaseOut).bindTarget(layoutTimeDown),
                    together(fadeIn(500), scaleTo(500,1).easing(Skill.BackEaseIn)).bindTarget(tvTime3),
                    delayTime(500),
                    together(fadeOut(500).bindTarget(tvTime3), scaleTo(500,0).bindTarget(tvTime3), fadeIn(500).bindTarget(tvTime2), scaleTo(500,1).easing(Skill.BackEaseIn).bindTarget(tvTime2)),
                    delayTime(500),
                    together(fadeOut(500).bindTarget(tvTime2), scaleTo(500,0).bindTarget(tvTime2), fadeIn(500).bindTarget(tvTime1), scaleTo(500,1).easing(Skill.BackEaseIn).bindTarget(tvTime1)),
                    delayTime(500),
                    together(scaleTo(500, 0.3f).easing(Skill.CubicEaseOut), fadeOut(500)).bindTarget(layoutTimeDown)
                )
        ).playOn(layoutTimeDown);


    }
}

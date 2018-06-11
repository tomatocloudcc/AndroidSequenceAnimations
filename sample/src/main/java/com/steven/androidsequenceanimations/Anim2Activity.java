package com.steven.androidsequenceanimations;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.steven.androidsequenceanimations.library.base.EasyAnimation;

import static com.steven.androidsequenceanimations.library.base.EasyAnimation.delayTime;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeIn;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeOut;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.moveTo;
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
    }

    public void startAnim(View view) {
        View layoutTimeDown = findViewById(R.id.layoutTimeDown);
        View tvTime3 = findViewById(R.id.tvTime3);
        View tvTime2 = findViewById(R.id.tvTime2);
        View tvTime1 = findViewById(R.id.tvTime1);

        tvTime3.setAlpha(0);
        tvTime2.setAlpha(0);
        tvTime1.setAlpha(0);

        Point centPoint = new Point(((View)layoutTimeDown.getParent()).getWidth() / 2, ((View)layoutTimeDown.getParent()).getHeight() / 2);



        EasyAnimation.with(
                sequence(
                moveTo(1000, centPoint.x, centPoint.y).bindTarget(layoutTimeDown),
                fadeIn(500).bindTarget(tvTime3),
                delayTime(500),
                together(fadeOut(500).bindTarget(tvTime3), fadeIn(500).bindTarget(tvTime2)),
                delayTime(500),
                together(fadeOut(500).bindTarget(tvTime2), fadeIn(500).bindTarget(tvTime1)),
                delayTime(500),
                moveTo(1000, centPoint.x, -layoutTimeDown.getMeasuredHeight()).bindTarget(layoutTimeDown)
                )
        ).playOn(layoutTimeDown);


    }
}

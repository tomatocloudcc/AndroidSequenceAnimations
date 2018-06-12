package com.steven.androidsequenceanimations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.easing.Skill;
import com.steven.androidsequenceanimations.library.actions.instant.CallFunc;
import com.steven.androidsequenceanimations.library.base.BaseAction;
import com.steven.androidsequenceanimations.library.base.EasyAnimation;
import com.steven.androidsequenceanimations.library.base.EasyAnimationHelper;

import static com.steven.androidsequenceanimations.library.base.EasyAnimation.EasyAnimationWrapper;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.callFunc;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.delayTime;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeIn;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeOut;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.moveTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.rotateTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.scaleTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.sequence;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.together;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View view) {
        if(view.getId()==R.id.btnStartAnim)
        {
            final TextView targetView = findViewById(R.id.txt);
            final TextView targetView2 = findViewById(R.id.txt2);

            final float targetViewX= targetView.getX();
            final float targetViewY= targetView.getY();
            final float targetView2X= targetView2.getX();
            final float targetView2Y= targetView2.getY();

            EasyAnimationHelper.reset(targetView);
            EasyAnimationHelper.reset(targetView2);

            BaseAction anim =
                    sequence(
                            sequence(
                                    rotateTo(2000, 720).easing(Skill.BounceEaseOut),
                                    fadeOut(1000),
                                    delayTime(1000),
                                    fadeIn(1000),
                                    together(scaleTo(1000, 3f, 1f).easing(Skill.BounceEaseInOut),
                                            moveTo(1000, 0, 0).easing(Skill.BounceEaseOut),
                                            moveTo(1000, ((View)(targetView2.getParent())).getMeasuredWidth() - targetView2.getMeasuredWidth(), ((View)(targetView2.getParent())).getMeasuredHeight() - targetView2.getMeasuredHeight()).easing(Skill.BounceEaseOut).bindTarget(targetView2)
                                    )
                            )
                            ,callFunc(new CallFunc.Caller() {
                                @Override
                                public void onCall(View target) {
                                    System.out.println("anim fun call");
                                }}),
                            delayTime(1000),
                            together(
                                    scaleTo(1000, 1),
                                    moveTo(1000, targetViewX, targetViewY),
                                    moveTo(1000, targetView2X, targetView2Y).bindTarget(targetView2)
                            )
                    );

            EasyAnimationWrapper animWrapper = EasyAnimation.with(anim)
                    .pivot(0.5f, 0.5f)
                    .playOn(targetView);
            //animWrapper.stop();
            //animWrapper.isRunning();
        }
        else if(view.getId()==R.id.btnGoToAnim2)
        {
            startActivity(new Intent(this, Anim2Activity.class));
        }
    }
}

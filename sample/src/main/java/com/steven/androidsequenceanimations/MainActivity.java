package com.steven.androidsequenceanimations;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.daimajia.easing.Skill;
import com.steven.androidsequenceanimations.library.actions.Repeat;
import com.steven.androidsequenceanimations.library.actions.RepeatForever;
import com.steven.androidsequenceanimations.library.actions.instant.CallFunc;
import com.steven.androidsequenceanimations.library.actions.interval.update.ColorTo;
import com.steven.androidsequenceanimations.library.actions.interval.update.ValueIntTo;
import com.steven.androidsequenceanimations.library.base.BaseAction;
import com.steven.androidsequenceanimations.library.base.EasyAnimation;
import com.steven.androidsequenceanimations.library.base.EasyAnimationHelper;

import static com.steven.androidsequenceanimations.library.base.EasyAnimation.EasyAnimationWrapper;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.callFunc;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.colorTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.delayTime;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeIn;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.fadeOut;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.moveTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.repeat;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.repeatForever;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.rotateTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.scaleTo;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.sequence;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.together;
import static com.steven.androidsequenceanimations.library.base.EasyAnimation.valueIntTo;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    EasyAnimationWrapper animWrapper;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void click(View view) {

        switch (view.getId())
        {
            case R.id.btnStartAnim:
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
                        repeatForever(
                                sequence(
                                        sequence(
                                                repeat(sequence(rotateTo(2000, 720).easing(Skill.BounceEaseOut), rotateTo(1000, 0)),1),
                                                fadeOut(1000),
                                                delayTime(500),
                                                fadeIn(1000),
                                                together(scaleTo(1000, 3f, 1f).easing(Skill.BounceEaseInOut),
                                                        moveTo(1000, 0, 0).easing(Skill.BounceEaseOut),
                                                        moveTo(1000, ((View)(targetView2.getParent())).getMeasuredWidth() - targetView2.getMeasuredWidth(), ((View)(targetView2.getParent())).getMeasuredHeight() - targetView2.getMeasuredHeight()).easing(Skill.BounceEaseOut).bindTarget(targetView2)
                                                ),
                                                colorTo(1000, Color.GRAY, Color.RED, new ColorTo.IColorChange() {
                                                    @Override
                                                    public void onChanged(View target, int color) {
                                                        ((TextView)target).setTextColor(color);
                                                    }
                                                })
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
                                        ),
                                        colorTo(1000, Color.RED, Color.GRAY, new ColorTo.IColorChange() {
                                            @Override
                                            public void onChanged(View target, int color) {
                                                ((TextView)target).setTextColor(color);
                                            }
                                        })
                                ));
                animWrapper = EasyAnimation.with(anim)
                        .pivot(0.5f, 0.5f)
                        .playOn(targetView);

//            EasyAnimationWrapper animWrapper = EasyAnimation.with(repeatForever(sequence(
//                    repeat(sequence(scaleTo(2000, 5), scaleTo(1000, 1)), 1),
//                    callFunc(new CallFunc.Caller() {
//                        @Override
//                        public void onCall(View target) {
//                            System.out.println("anim all end");
//                        }
//            }))))
//                    .pivot(0.5f, 0.5f)
//                    .playOn(targetView);

            }
                break;
            case R.id.btnPauseAnim:
                if(animWrapper!=null)
                {
                    animWrapper.pause();
                }
                break;
            case R.id.btnResumeAnim:
                if(animWrapper!=null)
                {
                    animWrapper.resume();
                }
                break;
            case R.id.btnGoToAnim2:
                startActivity(new Intent(this, Anim2Activity.class));
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();

        if(null!=animWrapper)
        {
            animWrapper.pause();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        if(null!=animWrapper)
        {
            animWrapper.resume();
        }
    }
}

package com.steven.androidsequenceanimations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.daimajia.easing.Skill;
import com.steven.androidsequenceanimations.library.EasyAnimation;
import com.steven.androidsequenceanimations.library.actions.Sequence;
import com.steven.androidsequenceanimations.library.actions.instant.CallFunc;
import com.steven.androidsequenceanimations.library.actions.interval.AlphaTo;
import com.steven.androidsequenceanimations.library.actions.interval.DelayTime;
import com.steven.androidsequenceanimations.library.actions.interval.RotateTo;
import com.steven.androidsequenceanimations.library.actions.interval.ScaleTo;
import com.steven.androidsequenceanimations.library.base.BaseAction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView targetView = findViewById(R.id.txt);
        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                BaseAction anim =
                new Sequence(
                        new Sequence( new RotateTo(2000, 720).easing(Skill.BounceEaseOut),new DelayTime(1000), new ScaleTo(1000, 3f, 10f).easing(Skill.BounceEaseOut))
                ,new CallFunc(new CallFunc.Caller() {
                        @Override
                        public void onCall() {
                            System.out.println("anim FunctionAnimator end");
                        }}),
                new AlphaTo(3000, 0.5f).easing(Skill.ExpoEaseInOut));

//                new RotateTo(1000, 180).easing(Skill.BounceEaseOut)
                EasyAnimation.with(new RotateTo(1000, 180).easing(Skill.BounceEaseOut))
                        .pivot(0.5f, 0.5f)
                        .playOn(targetView);
//                YoYo.with(SequenceAnimator.createSequenceAnimator(new RotateAnimator(), new ScaleAnimator(), new AlphaAnimator())).playOn(targetView);

//                YoYo.with(TogetherAnimator.createTogetherAnimator(SequenceAnimator.createSequenceAnimator(new RotateAnimator(), new ScaleAnimator()), new AlphaAnimator())).playOn(targetView);
            }
        });
    }
}

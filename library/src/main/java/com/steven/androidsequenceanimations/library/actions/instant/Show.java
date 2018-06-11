package com.steven.androidsequenceanimations.library.actions.instant;

import android.view.View;

/**
 * Created by sulei on 2018/6/8.
 */
public class Show extends CallFunc
{
    public Show()
    {
        super(new Caller() {
            @Override
            public void onCall(View target) {
                target.setVisibility(View.VISIBLE);
            }
        });
    }
}

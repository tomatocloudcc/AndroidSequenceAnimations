package com.steven.androidsequenceanimations.library.actions;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/25.
 */
public class RepeatForever extends Repeat
{
    public RepeatForever(BaseAction action) {
        super(action, -1);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void pause() {
        super.pause();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void resume() {
        super.resume();
    }
}

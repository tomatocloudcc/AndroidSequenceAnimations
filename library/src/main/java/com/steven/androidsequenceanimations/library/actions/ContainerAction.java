package com.steven.androidsequenceanimations.library.actions;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.steven.androidsequenceanimations.library.base.BaseAction;

/**
 * Created by sulei on 2018/6/8.
 */
public abstract class ContainerAction extends BaseAction
{
    protected BaseAction action1;
    protected BaseAction action2;

    protected void initWithTwoActions(BaseAction action1, BaseAction action2) {
        this.action1 = action1;
        this.action2 = action2;
    }

    @Override
    public void cancel() {
        super.cancel();
        this.action1.cancel();
        this.action2.cancel();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void pause() {
        super.pause();
        this.action1.pause();
        this.action2.pause();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void resume() {
        super.resume();
        this.action1.resume();
        this.action2.resume();
    }

    protected Animator getPreparedFirstAnimator() {

        if(this.action1 instanceof ContainerAction)
        {
            return ((ContainerAction) this.action1).getPreparedFirstAnimator();
        }
        return this.getPreparedAnimator();
    }

    protected Animator getPreparedLastAnimator() {
        if(this.action2 instanceof ContainerAction)
        {
            return ((ContainerAction) this.action2).getPreparedFirstAnimator();
        }
        return this.getPreparedAnimator();
    }
}

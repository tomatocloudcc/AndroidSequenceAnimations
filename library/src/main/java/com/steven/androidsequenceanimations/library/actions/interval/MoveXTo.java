package com.steven.androidsequenceanimations.library.actions.interval;

/**
 * Created by sulei on 2018/6/27.
 */
public class MoveXTo extends MoveTo
{

    public MoveXTo(long duration, float x)
    {
        super(duration, x, 0);
        this.isByX = false;
        this.isByY = true;
    }

}

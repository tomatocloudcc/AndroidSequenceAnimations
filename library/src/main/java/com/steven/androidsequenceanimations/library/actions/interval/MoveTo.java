package com.steven.androidsequenceanimations.library.actions.interval;

/**
 * Created by sulei on 2018/6/11.
 */
public class MoveTo extends MoveBy
{

    public MoveTo(long duration, float x, float y)
    {
        super(duration, x, y);
        this.isBy = false;
    }

}

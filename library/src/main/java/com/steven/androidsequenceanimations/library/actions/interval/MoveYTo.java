package com.steven.androidsequenceanimations.library.actions.interval;

/**
 * Created by sulei on 2018/6/27.
 */
public class MoveYTo extends MoveTo
{

    public MoveYTo(long duration, float y)
    {
        super(duration, 0, y);
        this.isByX = true;
        this.isByY = false;
    }

}

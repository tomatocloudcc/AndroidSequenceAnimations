package com.steven.androidsequenceanimations.library.actions.interval;

/**
 * Created by sulei on 2018/6/7.
 */
public class RotateTo extends RotateBy
{

    /**
     * @param duration
     * @param rotationXY 360 degrees equals a circle, default is 0
     */
    public RotateTo(long duration, int rotationXY)
    {
        this(duration, rotationXY, rotationXY);
    }

    /**
     *
     * @param duration
     * @param rotationX 360 degrees equals a circle, default is 0
     * @param rotationY 360 degrees equals a circle, default is 0
     */
    public RotateTo(long duration, int rotationX,  int rotationY)
    {
        super(duration, rotationX, rotationY);
        this.isBy = false;
    }
}

# Android Sequence Animations
The library can help you build complex animations quickly, which wrapped Android animation, supporting parallel and serial animation combination.

# Demo
![](https://github.com/suleisteven/AndroidSequenceAnimations/blob/master/capture/anim1.gif)

![](https://github.com/suleisteven/AndroidSequenceAnimations/blob/master/capture/anim2.gif)

# Usage

## Step 1

#### Gradle
```groovy
dependencies {
        compile "com.steven:AndroidSequenceaAimations:1.2.0"
}
```
## Step 2

```java
EasyAnimation.with(
                    sequence(
                            fadeIn(1000), 
                            together(rotateTo(500, 360), 
                                    scaleTo(500, 5).easing(Skill.BackEaseIn)
                            ))
            ).playOn(targetView);
```

# Actions
## Instant
`FlipX`, `FlipY`, `Show`, `Hide`, `CallFunc`

## Interval
`FadeIn`,`FadeOut`,`FadeTo`,`MoveBy`,`MoveTo`,`MoveXTo`,`MoveYTo`,`RotateBy`,`RotateTo`,`ScaleTo`,`DelayTime`

## Update
`ColorTo`,`ValueIntTo`,`ValueFloatTo`

## Container
`Sequence`,`Together`,`Repeat`,`RepeatForever`

# Todo
1.Interval Action:`BezierMoveTo`  
2.Interval Action:`Shake`  

# Thanks

- [AnimationEasingFunctions](https://github.com/daimajia/AnimationEasingFunctions)

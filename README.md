# Android Sequence Animations
The library can help you build complex animations quickly, which wrapped Android animation, supporting parallel and serial animation combination.

# Demo
![](https://github.com/suleisteven/AndroidSequenceAnimations/blob/master/capture/anim1.gif)

![](https://github.com/suleisteven/AndroidSequenceAnimations/blob/master/capture/anim2.gif)

# Example

```java
EasyAnimation.with(
                    sequence(
                            fadeIn(1000), 
                            together(rotateTo(500, 360), 
                                    scaleTo(500, 5).easing(Skill.BackEaseIn)
                            ))
            ).playOn(targetView);
```

# Thanks

- [AnimationEasingFunctions](https://github.com/daimajia/AnimationEasingFunctions)

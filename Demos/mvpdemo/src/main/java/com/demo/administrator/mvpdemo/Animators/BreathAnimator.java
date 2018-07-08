package com.demo.administrator.mvpdemo.Animators;

import android.animation.TimeInterpolator;

public class BreathAnimator implements TimeInterpolator {

  @Override
  public float getInterpolation(float input) {
    float x = input * 6;
    float k =  1.0f/3;
    float t =6;
    float n =1;
    float y =0 ;
    double PI = Math.PI;
    if(x >= ((n-1)*t) && x<(n-(1-k))*t)
    {
      return  y = (float) (1/2 * Math.sin((PI/(k*t)) * ((x-k/2*t)-(n-1)*t))+ 1/2);
    } else if(x>= (n-(1-k))*t && x<n*t)
    {
      return  y = (float) Math.sqrt(1/2 * Math.sin(PI/(1-k)*t*((x-(3-k)/2*t)-(n-1)*t)+1/2));
    }
    return (float) y;
  }
}

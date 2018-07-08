package com.dstl.hys.kotlindemo;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;

public interface Java8InterfaceDemo {

  @TargetApi(VERSION_CODES.N)
  static boolean isNull(String s){
    if(s!= null){
      return false;
    } else {
      return true;
    }
  }

}

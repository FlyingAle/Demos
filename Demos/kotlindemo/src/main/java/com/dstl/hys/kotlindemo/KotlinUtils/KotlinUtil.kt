package com.dstl.hys.kotlindemo.KotlinUtils

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class KotlinUtil {
     fun AppCompatActivity.toast(message : CharSequence){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
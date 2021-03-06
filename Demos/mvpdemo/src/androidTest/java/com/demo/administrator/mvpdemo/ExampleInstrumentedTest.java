package com.demo.administrator.mvpdemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import android.widget.Toast;
import com.demo.hys.myutillibrary.Cookie.Cookie;
import com.demo.hys.myutillibrary.Cookie.Cookie.CookieBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();
    assertEquals("com.demo.administrator.mvpdemo", appContext.getPackageName());
    Cookie cookie = new CookieBuilder().setContext(appContext).setName("1111111").build();
    cookie.setBooleanCookie("true",true)
        .setIntCookie("1",1)
        .setStringCookie("test","test");
    String s = cookie.getStringCookie("test");
    Toast.makeText(appContext,s,Toast.LENGTH_SHORT).show();
  }
}

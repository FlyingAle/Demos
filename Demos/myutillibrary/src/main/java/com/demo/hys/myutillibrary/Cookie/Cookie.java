package com.demo.hys.myutillibrary.Cookie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Set;

public class Cookie {
  private static volatile HashMap<String,Cookie> cookies;
  private SharedPreferences sharedPreferences;
  @SuppressLint("WrongConstant")
  private Cookie(Context context,String name)
  {
    sharedPreferences = context.getSharedPreferences(name,Context.MODE_APPEND);
  }

  public Cookie setStringCookie(String key,String value)
  {
    sharedPreferences.edit().putString(key,value).apply();
    return this;
  }

  public Cookie setIntCookie(String key,int value)
  {
    sharedPreferences.edit().putInt(key,value).apply();
    return this;
  }

  public Cookie setBooleanCookie(String key,boolean value)
  {
    sharedPreferences.edit().putBoolean(key, value).apply();
    return this;
  }

  public Cookie setLongCookie(String key,long value)
  {
    sharedPreferences.edit().putLong(key, value).apply();
    return this;
  }

  public Cookie setFloatCookie(String key,float value)
  {
    sharedPreferences.edit().putFloat(key, value).apply();
    return this;
  }

  public Cookie setStringSetCookie(String key,Set<String> value)
  {
    sharedPreferences.edit().putStringSet(key, value).apply();
    return this;
  }
  public String getStringCookie(String key)
  {
    return sharedPreferences.getString(key," ");
  }

  public int getIntCookie(String key)
  {
    return sharedPreferences.getInt(key,0);
  }

  public boolean getBooleanCookie(String key)
  {
    return sharedPreferences.getBoolean(key,false);
  }

  public long getLongCookie(String key)
  {
    return sharedPreferences.getLong(key,0);
  }

  public float getFloatCookie(String key)
  {
    return sharedPreferences.getFloat(key,0f);
  }

  public Set<String> getStringSetCookie(String key)
  {
    return sharedPreferences.getStringSet(key,null);
  }

  public SharedPreferences getSharedPreferences() {
    return sharedPreferences;
  }

  public static class CookieBuilder extends Builader{

    private Context context;
    private String name;

    public CookieBuilder()
    {
      cookies = new HashMap<>();
    }

    public Cookie build()
    {
      Cookie cookie = cookies.get(name);
      if(cookie == null) {
        cookie = new Cookie(context, name);
        cookies.put(name, cookie);
      }
      return cookie;
    }

    @Override
    public CookieBuilder setContext(Context context) {
      this.context = context;
      return this;
    }

    @Override
    public CookieBuilder setName(String name) {
      this.name = name;
      return this;
    }
  }
   static abstract class Builader{
    public abstract CookieBuilder setContext(Context context);
    public abstract CookieBuilder setName(String name);
  }
}

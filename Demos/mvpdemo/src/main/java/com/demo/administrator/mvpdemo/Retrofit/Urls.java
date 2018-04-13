package com.demo.administrator.mvpdemo.Retrofit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Urls {
  static Date date = new Date(System.currentTimeMillis());
  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
  static String  today = dateFormat.format(date);

  public static final String baseUrl = "http://gank.io/";
  public static final String getTodayUrl = "api/day/" + today;

}

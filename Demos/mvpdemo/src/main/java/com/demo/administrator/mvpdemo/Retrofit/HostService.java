package com.demo.administrator.mvpdemo.Retrofit;

import com.demo.administrator.mvpdemo.Beans.TodayDataBean;
import io.reactivex.Observable;
import java.text.SimpleDateFormat;
import java.util.Date;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Url;

public interface HostService {
  @GET("api/day/2015/08/06")
  Observable<TodayDataBean> getToday();

}

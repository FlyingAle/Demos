package com.demo.administrator.mvpdemo.Models;

import com.demo.administrator.mvpdemo.Beans.TodayDataBean;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.AndroidBean;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.IOSBean;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.ImageBean;
import com.demo.administrator.mvpdemo.Retrofit.HostRetrofit;
import com.demo.administrator.mvpdemo.Retrofit.HostService;
import com.demo.administrator.mvpdemo.Retrofit.Urls;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.adapter.rxjava2.Result;

public class TodayModel {

  private HostService hostService;
  private Observable<TodayDataBean> result;
  private TodayDataBean dataBean;
  private static TodayModel todayModel;

  private TodayModel()
  {
    hostService = HostRetrofit.getInstance();
    getTodayData();
  }

  public static TodayModel getInstances() {
    if(todayModel == null)
    {
      todayModel = new TodayModel();
    }
    return todayModel;
  }

  private void  getTodayData()
  {
    result = hostService.getToday().subscribeOn(Schedulers.io());
  }

  public Observable<List<AndroidBean>> getAndroidBean()
  {
    return result.map(new Function<TodayDataBean, List<AndroidBean>>() {
      @Override
      public List<AndroidBean> apply(TodayDataBean todayDataBean) throws Exception {
        return todayDataBean.getResults().getAndroid();
      }
    }).subscribeOn(Schedulers.io());
  }

  public Observable<List<IOSBean>> getIosBean()
  {
    return result.map(new Function<TodayDataBean, List<IOSBean>>() {
      @Override
      public List<IOSBean> apply(TodayDataBean todayDataBean) throws Exception {
        return todayDataBean.getResults().getIOS();
      }
    }).subscribeOn(Schedulers.io());
  }

  public Observable<List<String>> getImages()
  {
    return result.map(new Function<TodayDataBean, List<String>>() {
      @Override
      public List<String> apply(TodayDataBean todayDataBean) throws Exception {
        List<String> imageUrls = new ArrayList<>();
        List<ImageBean> imageBeans = todayDataBean.getResults().getImage();
        for(ImageBean b:imageBeans)
        {
          imageUrls.add(b.getUrl());
        }
        return imageUrls;
      }
    }).subscribeOn(Schedulers.io());
  }

  public Observable<ResultsBean> getAllData()
  {
    return result.map(new Function<TodayDataBean, ResultsBean>() {
      @Override
      public ResultsBean apply(TodayDataBean todayDataBean) throws Exception {
        return todayDataBean.getResults();
      }
    }).subscribeOn(Schedulers.io());
  }
}

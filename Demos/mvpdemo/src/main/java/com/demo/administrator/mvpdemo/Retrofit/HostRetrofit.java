package com.demo.administrator.mvpdemo.Retrofit;

import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HostRetrofit {

  private static Retrofit retrofit;
  private static Gson gson;
  private static HostRetrofit hostRetrofit;
  private static HostService hostService;

  private HostRetrofit()
  {
    if(retrofit == null)
    {
      gson = new Gson();
      retrofit = new Retrofit.Builder()
          .baseUrl(Urls.baseUrl)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
    }
  }

  public static HostService getInstance()
  {
    if(hostRetrofit == null)
    {
      hostRetrofit = new HostRetrofit();
      hostService = retrofit.create(HostService.class);
    }
    return hostService;
  }
}

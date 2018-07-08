package com.demo.hys.myutillibrary.baseLibrarys;

import android.support.annotation.NonNull;
import com.demo.hys.myutillibrary.Utils.DESEncryption;
import com.demo.hys.myutillibrary.Utils.Encryptions;
import com.demo.hys.myutillibrary.Utils.RequestEncryptInterceptor;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.CustomConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create By Hys ${Date}
 */
public class RetrofitBuilder {

  private Converter.Factory factory;
  private OkHttpClient okHttpClient;
  private String baseUrl;
  private Gson mGson;
  private long defaultTimeout = 0;
  private Interceptor[] interceptors;
  private String key;
  private String iv;
  private Encryptions encryptions;


  public RetrofitBuilder() {
    mGson = new Gson();
    factory = GsonConverterFactory.create(mGson);
  }

  private Retrofit createRetrofit() {
    Retrofit.Builder build = new Retrofit.Builder().baseUrl(baseUrl)
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()).client(okHttpClient);
    if (encryptions != null) {
      build.addConverterFactory(CustomConverterFactory.create(mGson, encryptions));
    }
    return build.build();
  }

  public RetrofitBuilder setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
    return this;
  }

  public RetrofitBuilder setGson(Gson gson) {
    this.mGson = gson;
    return this;
  }

  public <T> T build(Class<T> c) {
    return createRetrofit().create(c);
  }

  public RetrofitBuilder setOkHttpClicnt(OkHttpClient okHttpClicnt) {
    this.okHttpClient = okHttpClicnt;
    return this;
  }

  public RetrofitBuilder setCallAdapterFactory(Converter.Factory factory) {
    this.factory = factory;
    return this;
  }

  private Builder createSimpleBuilder() {
    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
    okHttpClientBuilder.connectTimeout(defaultTimeout, TimeUnit.SECONDS)
        .writeTimeout(defaultTimeout, TimeUnit.SECONDS)
        .readTimeout(defaultTimeout, TimeUnit.SECONDS);
    return okHttpClientBuilder;
  }

  @NonNull
  private OkHttpClient getOkHttpClient() {
    OkHttpClient.Builder builder = createSimpleBuilder()
        .addInterceptor(new RequestEncryptInterceptor(new DESEncryption(key, iv)));
    return builder.build();
  }

  private OkHttpClient getOkHttpClient(Interceptor... interceptors) {
    OkHttpClient.Builder builder = createSimpleBuilder();
    for (Interceptor interceptor : interceptors) {
      builder.addInterceptor(interceptor);
    }
    return builder.build();
  }

  public RetrofitBuilder setInterceptors(Interceptor... interceptors) {
    this.interceptors = interceptors;
    return this;
  }

  public RetrofitBuilder setDESEncryptionKey(String key) {
    this.key = key;
    return this;
  }

  public RetrofitBuilder setDESEncryptionIv(String iv) {
    this.iv = iv;
    return this;
  }

  public RetrofitBuilder setDefaultTimeout(long defaultTimeout) {
    this.defaultTimeout = defaultTimeout;
    return this;
  }

  public RetrofitBuilder setEncruption(Encryptions encruption) {
    this.encryptions = encruption;
    return this;
  }

}

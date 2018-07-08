package com.demo.hys.myutillibrary.Utils;

import android.util.Log;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class RequestEncryptInterceptor implements Interceptor {

  private static final String FORM_NAME = "data"; // 字段名称
  private static final String TAG = "RequestEncryptIntercept";
  private Encryptions encryptions;

  public RequestEncryptInterceptor(Encryptions encryptions) {
    this.encryptions = encryptions;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {

//    Request request = builder.build();
    Request request = chain.request();
    RequestBody oldBody = request.body();
    Log.i(TAG, "intercept: " + oldBody.toString());
    Buffer buffer = new Buffer();
    oldBody.writeTo(buffer);
    String strOldBody = buffer.readUtf8();
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    String strNewBody = null;
    try {
      strNewBody = encryptions.encrpy(strOldBody);
    } catch (Exception e) {
      e.printStackTrace();
    }
    RequestBody newBody = new  FormBody.Builder().add("data",strNewBody).build();
    request = request.newBuilder().post(newBody).build();
    Log.i(TAG, "intercept: "+request.body().toString());
    return chain.proceed(request);
  }
}

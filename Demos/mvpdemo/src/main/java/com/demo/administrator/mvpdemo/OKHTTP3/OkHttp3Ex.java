package com.demo.administrator.mvpdemo.OKHTTP3;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp3Ex {

  public void createGet()
  {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder().url("").method("GET",null).build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {

      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {

      }
    });
  }

  public void createPost()
  {
    //MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//类型 字节码
    MediaType mediaType = MediaType.parse("application/octet-stream");//表示文件是任意的二进制数据流
    String value = "{username:admin;password:admin}";//实际的json字段
    File file = new File(Environment.getExternalStorageDirectory(),""); // 获取要上传的文件
    OkHttpClient okHttpClient = new OkHttpClient();
//    RequestBody requestBody = new FormBody.Builder().add("name","value").build(); 键值对请求体
//    RequestBody requestBody = RequestBody.create(mediaType,value);//传递json字符串的请求体创建
    RequestBody requestBody = RequestBody.create(mediaType,file); //上传文件的请求体
    Request request = new Request.Builder().post(requestBody).url("").build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {

      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {

      }
    });
  }

  public void downloadFile()
  {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder().url("https://www.baidu.com/img/bd_logo1.png").get().build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {

      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        InputStream inputStream = response.body().byteStream(); //获取数据流
        int len = 0;
        File file = new File(Environment.getExternalStorageDirectory(),"baidu.png"); //创建保存文件的地址
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buf = new byte[128]; //创建一个128字节的缓冲区
        while((len = inputStream.read(buf)) != -1)
        {
          fileOutputStream.write(len); //读取图片
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
      }
    });
  }

  public void createMultipart() //上传表单
  {
    File file = new File(Environment.getExternalStorageDirectory(),""); // 获取要上传的文件
    OkHttpClient okHttpClient = new OkHttpClient();
    RequestBody requestBody = new MultipartBody.Builder()
        .setType(MultipartBody.FORM) //设置表单类型 非常重要
        .addFormDataPart("name","name")
        .addFormDataPart("img","baidu.png",RequestBody.create(MediaType.parse("image/png"),file))
        .build();
    Request request = new Request.Builder()
        .url("").post(requestBody).build();
  }

}

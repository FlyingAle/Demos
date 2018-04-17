package com.demo.administrator.mvpdemo.Views.MainActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.demo.administrator.mvpdemo.Adapters.AndroidListAdapter;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.AndroidBean;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.ImageBean;
import com.demo.administrator.mvpdemo.Presenters.MainPresenter;
import com.demo.administrator.mvpdemo.R;
import com.demo.administrator.mvpdemo.Utils.GlideImageLoader;
import com.youth.banner.Banner;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

  private MainPresenter mainPresenter;
  private Unbinder unbinder;
  @BindView(R.id.imageBanner)
  Banner banner;
  @BindView(R.id.main_list)
  RecyclerView mainList;
  private AndroidListAdapter androidListAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    unbinder =ButterKnife.bind(this);
    new MainPresenter(this);
    mainList.setLayoutManager(new LinearLayoutManager(this));
    banner.setImageLoader(new GlideImageLoader());
  }

  @Override
  protected void onResume() {
    super.onResume();
    mainPresenter.onStart();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mainPresenter.onDestroy();
    unbinder.unbind();
  }

  @Override
  public void setBannerImages(Observable<List<String>> listData) {
    listData.observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<String>>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(List<String> strings) {
            banner.setImages(strings)
                .start();
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }

  @Override
  public void setPresenter(MainPresenter presenter) {
    mainPresenter = presenter;
  }

  @Override
  public void setAndroidListData(Observable<List<AndroidBean>> listData) {
    listData.observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<AndroidBean>>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(List<AndroidBean> androidBeans) {
            androidListAdapter = new AndroidListAdapter(androidBeans,getApplicationContext());
            mainList.setAdapter(androidListAdapter);
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }
}

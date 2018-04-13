package com.demo.administrator.mvpdemo.Views.MainActivity;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import com.demo.administrator.mvpdemo.BaseClasses.BaseView;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.AndroidBean;
import com.demo.administrator.mvpdemo.Presenters.MainPresenter;
import io.reactivex.Observable;
import java.util.List;

public interface MainView extends BaseView<MainPresenter>{
  void setAndroidListData(Observable<List<AndroidBean>> listData);

}

package com.demo.administrator.mvpdemo.Presenters;

import com.demo.administrator.mvpdemo.BaseClasses.BasePresenter;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.AndroidBean;
import com.demo.administrator.mvpdemo.Models.TodayModel;
import com.demo.administrator.mvpdemo.Views.MainActivity.MainView;
import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import java.util.List;

public class MainPresenter implements BasePresenter{

  private MainView mainView;
  private TodayModel todayModel;
  public MainPresenter(@Nullable MainView mainView)
  {
    this.mainView = mainView;
    mainView.setPresenter(this);
    todayModel = TodayModel.getInstances();
  }
  @Override
  public void onStart() {
    setTodayModel();
  }

  public void onDestroy()
  {
    mainView = null;
    todayModel = null;
  }

  private Observable<List<AndroidBean>> getAndroidList()
  {
    return todayModel.getAndroidBean();
  }

  private void setTodayModel()
  {
    getAndroidList();
    mainView.setAndroidListData(getAndroidList());
  }
}

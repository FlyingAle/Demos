package com.demo.administrator.rxbusdemo.RxAnimations;

import android.view.View;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

public class RxViewAnimation extends Observable<Object> {

  private final View view;

  public RxViewAnimation(View view) {
    this.view = view;
  }

  @Override
  protected void subscribeActual(Observer<? super Object> observer) {
    ViewAnimation viewAnimation = new ViewAnimation(view,observer);
    observer.onSubscribe(viewAnimation);
  }

  static final class ViewAnimation extends MainThreadDisposable{
    private final View view;
    private final Observer<? super Object> observer;

    ViewAnimation(View view, Observer<? super Object> observer) {
      this.view = view;
      this.observer = observer;
    }

    @Override
    protected void onDispose() {

    }

    public void animationPlay(){
      observer.onNext(0);
    }
  }
}

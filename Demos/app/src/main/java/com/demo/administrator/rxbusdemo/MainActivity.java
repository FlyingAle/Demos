package com.demo.administrator.rxbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.administrator.rxbusdemo.RxBus.RxBus;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //仅限普通的RXBUS使用
    private void subscribeEvent(){
        RxBus.getDefaultInstance().toObservable(Event.class)
                // 使用操作符过程中，无需try,catch，直接使用
                .subscribe(new Observer<Event>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Event event) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        subscribeEvent();
                    }

                    @Override
                    public void onComplete() {
                        subscribeEvent();
                    }
                });
    }

    //适用于Sticky的方法
    private void subscribeEventForSticky()
    {
        RxBus.getDefaultInstance().toObservableSticky(EventSticky.class)        // 建议在Sticky时,在操作符内主动try,catch        
                .map(new Function<EventSticky, EventSticky>() {
                    @Override
                    public EventSticky apply(EventSticky eventSticky) throws Exception {
                        try{

                        }catch (Exception e)
                        {

                        }
                        return eventSticky;
                    }
                }).subscribe(new Consumer<EventSticky>() {
            @Override
            public void accept(EventSticky eventSticky) throws Exception {
                try{
                    //处理接受事件
                }
                catch (Exception e)
                {

                }
            }
        });
    }
}

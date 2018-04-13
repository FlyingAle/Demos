package com.demo.administrator.rxbusdemo.RxBus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private static volatile RxBus defaultInstance;
    private final Subject<Object> mBus;
    private final Map<Class<?>,Object> mStickyEventMap;

    private RxBus()
    {
        mBus = PublishSubject.create().toSerialized();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    public static RxBus getDefaultInstance() {
        if(defaultInstance == null)
        {
            synchronized (RxBus.class)
            {
                if(defaultInstance == null)
                {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    public void postStickyEvent(Object event)
    {
        synchronized (mStickyEventMap)
        {
            mStickyEventMap.put(event.getClass(),event);
        }
        post(event);
    }

    public <T>Observable<T> toObservableSticky(final Class<T> eventType)
    {
        synchronized (mStickyEventMap)
        {
            Observable<T> observable = mBus.ofType(eventType);
            final Object event = mStickyEventMap.get(eventType);
            if(event != null)
            {
                return observable.mergeWith(Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        emitter.onNext(eventType.cast(event));
                    }
                }));
            } else {
                return observable;
            }
        }
    }

    public boolean hasObservers()
    {
        return mBus.hasObservers();
    }

    public void reset()
    {
        defaultInstance = null;
    }

    public <T>T getStickyEvent(Class<T> eventType)
    {
        synchronized (mStickyEventMap)
        {
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    public <T>T removeStickyEvent(Class<T> eventType)
    {
        synchronized (mStickyEventMap)
        {
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    public void removeAllStickyEvent()
    {
        synchronized (mStickyEventMap)
        {
            mStickyEventMap.clear();
        }
    }

    public void post(Object action)
    {
        mBus.onNext(action);
    }

    public void post(int code,Object action)
    {
        mBus.onNext(new Action(code,action));
    }

    public <T>Observable<T> toObservable(Class<T> eventType)
    {
        return mBus.ofType(eventType);
    }

    public <T>Observable<T> toObservableWithCode(final int code,Class<T> eventType)
    {
        return mBus.ofType(Action.class)
                .filter(new Predicate<Action>() {
                    @Override
                    public boolean test(Action action) throws Exception {
                        return action.code == code;
                    }
                })
                .map(new Function<Action, Object>() {
            @Override
            public Object apply(Action action) throws Exception {
                return action.data;
            }
        })
                .cast(eventType);
    }

    public class Action{
        int code;
        Object data;

        public Action(int code, Object data) {
            this.code = code;
            this.data = data;
        }
    }
}

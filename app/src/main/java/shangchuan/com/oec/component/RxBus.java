package shangchuan.com.oec.component;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import shangchuan.com.oec.model.event.RxMessage;
import shangchuan.com.oec.util.RxUtil;

/**
 * Created by codeest on 2016/8/2.
 */
public class RxBus {
    // 主题
    private final Subject<Object, Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();

    }


    // 提供了一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
    // 封装默认订阅
    public <T> Subscription toDefaultObservable(Class<T> eventType, Action1<T> act) {
        return bus.ofType(eventType).compose(RxUtil.<T>scheduleRxHelper()).subscribe(act);
    }
    /**
     * 提供了一个新的事件,根据code进行分发
     * @param code 事件code
     * @param o
     */
    public void post(int code, Object o){
        bus.onNext(new RxMessage(code,o));
    }

    /**
     * 根据传递的code和 eventType 类型返回特定类型(eventType)的 被观察者
     * @param code 事件code
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(final int code, final Class<T> eventType) {
        return bus.ofType(RxMessage.class)
                .filter(new Func1<RxMessage,Boolean>() {
                    @Override
                    public Boolean call(RxMessage o) {
                        //过滤code和eventType都相同的事件
                        return o.getCode() == code && eventType.isInstance(o.getObject());
                    }
                }).map(new Func1<RxMessage,Object>() {
                    @Override
                    public Object call(RxMessage o) {
                        return o.getObject();
                    }
                }).cast(eventType);
    }

}

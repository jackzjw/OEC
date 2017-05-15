package shangchuan.com.oec.util;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import shangchuan.com.oec.component.ApiException;
import shangchuan.com.oec.model.bean.HttpDataResult;

/**
 * Created by sg280 on 2016/12/7.
 */

public class RxUtil {

    public static  <T>Observable.Transformer<T,T> scheduleRxHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T>Observable.Transformer<HttpDataResult<T>,T> handleResult(){
        return new Observable.Transformer<HttpDataResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpDataResult<T>> httpListResultObservable) {
                return httpListResultObservable.flatMap(new Func1<HttpDataResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpDataResult<T> result) {
                        if(result.getStatus()==200){
                            return createData(result.getData());
                        }else {
                            return Observable.error(new ApiException(result.getMessage()));
                        }
                    };
                });
            }
        };
    }
private static  <T>Observable<T> createData(final T data){

    return Observable.create(new Observable.OnSubscribe<T>() {
        @Override
        public void call(Subscriber<? super T> subscriber) {
            try {
                subscriber.onNext(data);
                subscriber.onCompleted();
            }catch (Exception e){
                subscriber.onError(e);
            }
        }
    });


}


}
package shangchuan.com.oec.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sg280 on 2017/3/3.
 */

public class RxPresent<T extends BaseView> implements BasePresent<T>{

    protected T mView;
    protected CompositeSubscription mSubscription;

    protected void add(Subscription subscription){
        if(mSubscription==null){
            mSubscription=new CompositeSubscription();
        }
        mSubscription.add(subscription);
    }
    protected void unSubscribe(){
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }


    @Override
    public void attachView(T view) {
        this.mView=view;
    }

    @Override
    public void deatchView() {
        mView=null;
        unSubscribe();
    }
}

package shangchuan.com.oec.base;

/**
 * Created by sg280 on 2017/3/3.
 */

public interface BasePresent<T extends BaseView> {
    void attachView(T view);
    void deatchView();
}

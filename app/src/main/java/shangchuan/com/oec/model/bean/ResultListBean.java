package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/5/5.
 */

public class ResultListBean<T> {
    private ArrayList<T> result;

    public ArrayList<T> getResult() {
        return result;
    }

    public void setResult(ArrayList<T> result) {
        this.result = result;
    }
}

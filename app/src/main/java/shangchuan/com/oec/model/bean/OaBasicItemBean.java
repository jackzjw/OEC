package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/3/30.
 */

public class OaBasicItemBean<T> {

    private int page;
    private int size;
    private int PageCount;
    private int TotalItemCount;
    private int TotalCount;
    private ArrayList<T> Items;

    public ArrayList<T> getItems() {
        return Items;
    }

    public void setItems(ArrayList<T> items) {
        Items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public int getTotalItemCount() {
        return TotalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        TotalItemCount = totalItemCount;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }


}

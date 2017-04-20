package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/20.
 */

public class ApproveListBean {
    private int Id;
    private String OrderType;
    private String OrderTitle;
    private String OrderContent;
    private String OrderTime;
    private String CreateTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getOrderTitle() {
        return OrderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        OrderTitle = orderTitle;
    }

    public String getOrderContent() {
        return OrderContent;
    }

    public void setOrderContent(String orderContent) {
        OrderContent = orderContent;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @Override
    public String toString() {
        return "ApproveListBean{" +
                "Id=" + Id +
                ", OrderType='" + OrderType + '\'' +
                ", OrderTitle='" + OrderTitle + '\'' +
                ", OrderContent='" + OrderContent + '\'' +
                ", OrderTime='" + OrderTime + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}

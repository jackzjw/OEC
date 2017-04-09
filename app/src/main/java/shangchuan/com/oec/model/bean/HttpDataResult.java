package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/27.
 */

public class HttpDataResult<T> {
    private int Status;
    private String usetime;
    private String Message;
    private T Data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}

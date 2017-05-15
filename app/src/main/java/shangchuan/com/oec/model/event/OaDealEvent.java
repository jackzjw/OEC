package shangchuan.com.oec.model.event;

/**
 * Created by sg280 on 2017/5/11.
 */

public class OaDealEvent {
    private int status;
    private int position;
    private boolean isDelete;
    public OaDealEvent(){}
    public OaDealEvent(int pos){
        this.position=pos;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

package shangchuan.com.oec.model.event;

/**
 * Created by sg280 on 2017/5/9.
 */

public class DealEvent {
    private int status;
    private int position;

    public DealEvent(){}
    public DealEvent(int status,int pos){
        this.status=status;
        this.position=pos;
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

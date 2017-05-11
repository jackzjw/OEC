package shangchuan.com.oec.model.event;

/**
 * Created by sg280 on 2017/5/11.
 */

public class WithDrawEvent {
    private int position;
   //撤回
    public WithDrawEvent(int pos){
        this.position=pos;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

package shangchuan.com.oec.model.bean;

import java.io.Serializable;

/**
 * Created by sg280 on 2017/4/13.
 */

public class SelectOwnerBean implements Serializable {
    private int Id;
    private String ownerName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

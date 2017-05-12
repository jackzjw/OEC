package shangchuan.com.oec.model.event;

/**
 * Created by sg280 on 2017/5/12.
 */

public class KeyWordEvent {
    private String keyword;
  public KeyWordEvent(String key){
      this.keyword=key;
  }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

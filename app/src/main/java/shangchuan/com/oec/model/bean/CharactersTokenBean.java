package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/30.
 */

public class CharactersTokenBean
{
    private String token;
    private CharacterInfoBean userinfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CharacterInfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(CharacterInfoBean userinfo) {
        this.userinfo = userinfo;
    }
}

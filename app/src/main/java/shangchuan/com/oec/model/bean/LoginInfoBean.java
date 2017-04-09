package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/27.
 */

public class LoginInfoBean
{
    private String token;
    private UserInfoBean userinfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfoBean userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "LoginInfoBean{" +
                "token='" + token + '\'' +
                ", userinfo=" + userinfo +
                '}';
    }
}

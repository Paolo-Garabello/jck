package types;

import java.sql.ResultSet;

public class User {
    private String token;
    private String publicName;
    private ResultSet privateName;
    public int conn_id;
    private static int id;

    public User(String token, String publicName, ResultSet privateName){
        this.token = token;
        this.privateName = privateName;
        this.publicName = publicName;
        this.conn_id = id++;
    }

    public User(User user){
        this.token = user.getToken();
        this.privateName = user.getPrivateName();
        this.publicName = user.getPublicName();
        this.conn_id = id++;
    }

    public ResultSet getPrivateName() {
        return privateName;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getToken() {
        return token;
    }

    public void setPrivateName(ResultSet privateName) {
        this.privateName = privateName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

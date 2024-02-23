package types;

import java.sql.ResultSet;

public class User {
    private String token;
    private String publicName;
    private ResultSet privateName;

    public User(String token, String publicName, ResultSet privateName){
        this.token = token;
        this.privateName = privateName;
        this.publicName = publicName;
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

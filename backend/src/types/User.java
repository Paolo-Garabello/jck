package types;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String token;
    private String publicName;
    private PrivateUser privateUser;
    private long closedAt;

    public User(String token, String publicName, ResultSet privateUser) {
        this.token = token;
        this.publicName = publicName;
        try {
            this.privateUser = new PrivateUser(privateUser.getInt("id"), privateUser.getString("username"));
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public User(String token, String publicName) {
        this.token = token;
        this.publicName = publicName;
        this.privateUser = null;
    }

    public User(User user) {
        this.token = user.getToken();
        this.privateUser = user.getPrivateUser();
        this.publicName = user.getPublicName();
        if(user.getPrivateUser() != null) {
            this.privateUser = user.getPrivateUser();
        }
    }

    public PrivateUser getPrivateUser() {
        return privateUser;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getToken() {
        return token;
    }

    public long getClosedAt() {
        return closedAt;
    }

    public void setPrivateUser(PrivateUser privateUser) {
        this.privateUser = new PrivateUser(privateUser.getId(), privateUser.getUsername());
    }

    public void setPrivateUser(ResultSet privateUser) {
        try{    
            this.privateUser = new PrivateUser(privateUser.getInt("id"), privateUser.getString("username"));
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setClosedAt(long closedAt) {
        this.closedAt = closedAt;
    }

    @Override
    public String toString() {
        return "token:" + token + " public:" + publicName; 
    }
}

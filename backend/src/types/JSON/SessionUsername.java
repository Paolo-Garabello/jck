package types.JSON;
public class SessionUsername {

    private String username;

    public SessionUsername(String username){
        this.username = "@" + username;
    }

    public String getUsername() {
        return username;
    }
}

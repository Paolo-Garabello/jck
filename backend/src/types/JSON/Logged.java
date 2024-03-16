package types.JSON;

public class Logged {
    private String username;
    private int id;

    public Logged(String username, int id) {
        this.username = username;
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

}

package types;

public class PrivateUser {
    private int id;
    private String username;

    public PrivateUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "id:" + this.id + " name:" + this.username;
    }
}

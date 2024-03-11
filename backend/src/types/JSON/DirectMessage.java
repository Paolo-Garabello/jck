package types.JSON;

public class DirectMessage extends Message{
    private int recipient;
    private int id;
    private int sender;
    private String username;

    /**
     * 
     * @param message
     * @param username
     * @param sender
     * @param recipient
     * @param id
     */
    public DirectMessage(String message, String username, int sender, int recipient, int id){
        super(message);
        this.username = username;
        this.recipient = recipient;
        this.setChat("DM");
        this.id = id;
        this.sender = sender;
    }

    public String getUsername() {
        return username;
    }

    public int getSender() {
        return sender;
    }

    public int getId() {
        return id;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

}
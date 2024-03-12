package types.JSON;

public class DirectMessage extends Message{
    private int recipient;
    private int id;
    private int sender;
    private String username;
    private String recipientUsername;

    /**
     * 
     * @param message
     * @param username
     * @param recipientUsername
     * @param sender
     * @param recipient
     * @param id
     */
    public DirectMessage(String message, String username, String recipientUsername, int sender, int recipient, int id) {
        super(message);
        this.username = username;
        this.recipientUsername = recipientUsername;
        this.recipient = recipient;
        this.setChat("DM");
        this.id = id;
        this.sender = sender;
    }

    /**
     * 
     * @param message
     * @param username
     * @param sender
     * @param recipient
     * @param id
     */
    public DirectMessage(String message, String username, int sender, int recipient, int id) {
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

    public String getRecipientUsername() {
        return recipientUsername;
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

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
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
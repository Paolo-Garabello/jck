package types.JSON;

public class DirectMessage extends Message{
    private int recipient;
    private int id;
    private int sender;

    /**
     * 
     * @param message
     * @param sender
     * @param recipient
     * @param id
     */
    public DirectMessage(String message, int sender, int recipient, int id){
        super(message);
        this.recipient = recipient;
        this.setChat("DM");
        this.id = id;
        this.sender = sender;
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
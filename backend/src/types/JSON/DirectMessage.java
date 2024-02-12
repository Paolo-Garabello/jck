package types.JSON;

public class DirectMessage extends Message{
    private String destination;

    public DirectMessage(String message, String name, String destination){
        super(message, name);
        this.destination = destination;
        this.setChatType("DM");
    }

    public String getDestination() {
        return destination;
    }
}
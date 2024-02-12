package types.JSON;

public class GlobalMessage extends Message{

    public GlobalMessage(String message, String name){
        super(message, name);
        this.setChatType("global");
    }
}

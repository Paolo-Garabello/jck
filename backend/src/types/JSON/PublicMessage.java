package types.JSON;

public class PublicMessage extends Message{

    public PublicMessage(String message, String name){
        super(message, name);
        this.setChat("public");
    }
}

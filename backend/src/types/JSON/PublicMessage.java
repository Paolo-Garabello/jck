package types.JSON;

public class PublicMessage extends Message{

    private String name;

    public PublicMessage(String message, String name){
        super(message);
        this.setName("@" + name);
        this.setChat("public");
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

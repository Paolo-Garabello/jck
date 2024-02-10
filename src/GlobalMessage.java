import java.util.Date;

public class GlobalMessage extends Message{
    
    GlobalMessage(String message, String name){
        this.setMessage(message);
        this.setName("@" + name);
        this.setDate(new Date().getTime());
        this.setChatType("gloal");
    }
}

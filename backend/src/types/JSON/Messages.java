package types.JSON;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Messages {
    private DirectMessage[] messages;

    public Messages(ResultSet res) {
        ArrayList<DirectMessage> arr = new ArrayList<DirectMessage>();
        try {
            while (res.next()) {
                arr.add(new DirectMessage(res.getString(2), res.getString(6), res.getString(5), res.getInt(3), res.getInt(4), res.getInt(1)));
            };
        } catch(SQLException e){
           e.printStackTrace();
        }
        messages = new DirectMessage[arr.size()];
        messages = arr.toArray(messages);
    }

    public Message[] getMessages() {
        return messages;
    }
    
    @Override
    public String toString() {
        String str = "";
        for(DirectMessage msg : messages) {
            str += msg + "\n";
        }
        return str;
    }
}
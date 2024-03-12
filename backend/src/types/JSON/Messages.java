package types.JSON;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Messages {
    private DirectMessage[] messages;

    public Messages(ResultSet res, String username) {
        ArrayList<DirectMessage> arr = new ArrayList<DirectMessage>();
        try {
            while (res.next()) {
                arr.add(new DirectMessage(res.getString("text"), username, res.getString("username"), res.getInt("sender"), res.getInt("recipient"), res.getInt("id")));
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
    
}
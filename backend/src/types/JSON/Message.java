package types.JSON;

import java.util.Date;

public class Message {
    private String message;
    private String chat;
    private long date;

    public Message(){}

    public Message(String message){
        this.setMessage(message);
        this.setDate(new Date().getTime());
    }

    public String getChat() {
        return chat;
    }

    public long getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

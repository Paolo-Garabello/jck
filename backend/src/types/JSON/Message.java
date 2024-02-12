package types.JSON;

import java.util.Date;

public class Message {
    private String message;
    private String name;
    private String chatType;
    private long date;

    public Message(){}

    public Message(String message, String name){
        this.setMessage(message);
        this.setName("@" + name);
        this.setDate(new Date().getTime());
    }

    public String getChatType() {
        return chatType;
    }

    public long getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package types.JSON;

public class Response {
    private boolean ok;
    private int statusCode;
    private String message = null;
    private Messages messages = null;

    public Response(boolean ok, int statusCode){
        this.ok = ok;
        this.statusCode = statusCode;
    }

    public Response(boolean ok, int statusCode, String message){
        this(ok, statusCode);
        this.message = message;
    }

    public Response(boolean ok, int statusCode, Messages messages){
        this(ok, statusCode);
        this.messages = messages;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Messages getMessages() {
        return messages;
    }
    
    public boolean getOk() {
        return this.ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }
}

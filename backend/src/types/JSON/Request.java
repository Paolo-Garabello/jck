package types.JSON;

public class Request {
    private String request;
    private Message content;
    private Login data;

    public void setContent(Message content) {
        this.content = content;
    }

    public void setData(Login data) {
        this.data = data;
    }

    public Message getContent() {
        return content;
    }

    public Login getData() {
        return data;
    }

    public String getRequest() {
        return request;
    }
}

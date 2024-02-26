package types.JSON;

public class Request {
    private String request;
    private Message content;
    private Login userInfo;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setContent(Message content) {
        this.content = content;
    }

    public void setUserInfo(Login userInfo) {
        this.userInfo = userInfo;
    }

    public Message getContent() {
        return content;
    }

    public Login getUserInfo() {
        return userInfo;
    }

    public String getRequest() {
        return request;
    }
}

package types.JSON;

public class Response<T> {
    private boolean ok;
    private int statusCode;
    private T data;

    public Response(boolean ok, int statusCode){
        this.ok = ok;
        this.statusCode = statusCode;
    }

    public Response(boolean ok, int statusCode, T data){
        this(ok, statusCode);
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public boolean getOk() {
        return this.ok;
    }

    public T getData() {
        return data;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setData(T data) {
        this.data = data;
    }
}

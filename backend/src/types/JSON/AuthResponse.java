package types.JSON;

public class AuthResponse {
    private String token;
    private String publicUsername;

    public AuthResponse(String token, String publicUsername) {
        this.token = token;
        this.publicUsername = "@" + publicUsername;
    }

    public String getpublicUsername() {
        return publicUsername;
    }

    public void setpublicUsername(String publicUsername) {
        this.publicUsername = publicUsername;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package erik.soekov.svenska_fest_back.dto;

public class LoginRequest implements VerifiableRequest{

    private String username;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void verifyFields() throws RequestVerificationException {
        if (username == null || username.trim().isEmpty()) {
            throw new RequestVerificationException("Username is required and must not be composed of whitespace characters.");
        }
        if (password == null || password.length() < 8) {
            throw new RequestVerificationException("Password of at least 8 characters is required.");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

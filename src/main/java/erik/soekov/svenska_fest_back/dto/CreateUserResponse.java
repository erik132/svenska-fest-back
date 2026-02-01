package erik.soekov.svenska_fest_back.dto;

public class CreateUserResponse {

    private String status;
    private String message;
    private String username;

    public CreateUserResponse() {
    }

    public CreateUserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public CreateUserResponse(String status, String message, String username) {
        this.status = status;
        this.message = message;
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

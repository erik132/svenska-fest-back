package erik.soekov.svenska_fest_back.dto;

public class CreateUserRequest implements VerifiableRequest{

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String estonianIdCode;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String username, String email, String password, String firstName, String lastName, String estonianIdCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.estonianIdCode = estonianIdCode;
    }

    @Override
    public void verifyFields() throws RequestVerificationException {
        if (username == null || username.trim().isEmpty()) {
            throw new RequestVerificationException("Username is required and must not be composed of whitespace characters.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new RequestVerificationException("Proper email is required.");
        }
        if (password == null || password.length() < 8) {
            throw new RequestVerificationException("Password of at least 8 characters is required.");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RequestVerificationException("First name is required for registration.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RequestVerificationException("Last name is required for registration.");
        }
        if (estonianIdCode == null || estonianIdCode.length() != 11) {
            throw new RequestVerificationException("Correct Estonian ID code is required.");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEstonianIdCode() {
        return estonianIdCode;
    }

    public void setEstonianIdCode(String estonianIdCode) {
        this.estonianIdCode = estonianIdCode;
    }


}

package erik.soekov.svenska_fest_back.dto;

public class RegisterEventParticipantRequest implements VerifiableRequest{

    private Integer eventId;
    private String firstName;
    private String lastName;
    private String estonianIdCode;

    public RegisterEventParticipantRequest() {
    }

    public RegisterEventParticipantRequest(Integer eventId, String firstName, String lastName, String estonianIdCode) {
        this.eventId = eventId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.estonianIdCode = estonianIdCode;
    }

    @Override
    public void verifyFields() throws RequestVerificationException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RequestVerificationException("Participant first name is required and must NOT be composed of whitespace characters.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RequestVerificationException("Participant last name is required and must NOT be composed of whitespace characters.");
        }
        if (estonianIdCode == null || estonianIdCode.length() != 11) {
            throw new RequestVerificationException("Correct participant Estonian ID code is required.");
        }
        if (eventId == null || eventId < 1) {
            throw new RequestVerificationException("Correct event ID is required for registration.");
        }
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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

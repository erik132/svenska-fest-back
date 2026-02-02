package erik.soekov.svenska_fest_back.dto;

import erik.soekov.svenska_fest_back.entity.EventParticipant;

public class EventParticipantResponse {

    private String firstName;
    private String lastName;

    public EventParticipantResponse() {
    }

    public EventParticipantResponse(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EventParticipantResponse(EventParticipant participant){
        firstName = participant.getFirstName();
        lastName = participant.getLastName();
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
}

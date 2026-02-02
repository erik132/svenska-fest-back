package erik.soekov.svenska_fest_back.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CreateEventRequest implements VerifiableRequest{

    private String name;
    private String address;
    private String description;
    private LocalDateTime dateTime;
    private Integer maxParticipants;
    private boolean isSurstromming;

    public CreateEventRequest() {
    }

    public CreateEventRequest(String name, String address, String description, LocalDateTime dateTime, Integer maxParticipants, boolean isSurstromming) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.dateTime = dateTime;
        this.maxParticipants = maxParticipants;
        this.isSurstromming = isSurstromming;
    }

    @Override
    public void verifyFields() throws RequestVerificationException {
        if (name == null || name.trim().isEmpty()) {
            throw new RequestVerificationException("Event name is required and must not be composed of whitespace characters.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new RequestVerificationException("Event requires a proper address.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new RequestVerificationException("Please provide a description for your event.");
        }
        if (dateTime == null) {
            throw new RequestVerificationException("Please provide a date and time for your event.");
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new RequestVerificationException("Please provide a date and time for your event that is in the future.");
        }
        if (maxParticipants == null || maxParticipants < 1) {
            throw new RequestVerificationException("Your party should have participants. Even 1 is fine if you want glamour in solitude.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public boolean isSurstromming() {
        return isSurstromming;
    }

    public void setSurstromming(boolean surstromming) {
        isSurstromming = surstromming;
    }
}

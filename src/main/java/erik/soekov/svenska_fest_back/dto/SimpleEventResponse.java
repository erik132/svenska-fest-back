package erik.soekov.svenska_fest_back.dto;

import erik.soekov.svenska_fest_back.entity.SimpleEvent;

import java.time.LocalDateTime;

public class SimpleEventResponse {

    private Integer id;
    private String name;
    private String address;
    private LocalDateTime dateTime;
    private Integer maxParticipants;
    private Integer participantsCount;

    public SimpleEventResponse() {
    }

    public SimpleEventResponse(Integer id, String name, String address, LocalDateTime dateTime, Integer maxParticipants, Integer participantsCount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateTime = dateTime;
        this.maxParticipants = maxParticipants;
        this.participantsCount = participantsCount;
    }

    public SimpleEventResponse(SimpleEvent event) {
        id = event.getId();
        name = event.getName();
        address = event.getAddress();
        dateTime = event.getDateTime();
        maxParticipants = event.getMaxParticipants();
        participantsCount = event.getParticipantsCount();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(Integer participantsCount) {
        this.participantsCount = participantsCount;
    }
}

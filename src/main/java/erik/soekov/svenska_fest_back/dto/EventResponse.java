package erik.soekov.svenska_fest_back.dto;

import erik.soekov.svenska_fest_back.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public class EventResponse {

    private Integer id;
    private String name;
    private String address;
    private String description;
    private LocalDateTime dateTime;
    private Integer maxParticipants;
    private boolean isSurstromming;

    private List<EventParticipantResponse> eventParticipants;

    public EventResponse() {
    }

    public EventResponse(Integer id, String name, String address, String description, LocalDateTime dateTime, Integer maxParticipants, boolean isSurstromming, List<EventParticipantResponse> eventParticipants) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.dateTime = dateTime;
        this.maxParticipants = maxParticipants;
        this.isSurstromming = isSurstromming;
        this.eventParticipants = eventParticipants;
    }

    public EventResponse(Event event) {
        id = event.getId();
        name = event.getName();
        address = event.getAddress();
        description = event.getDescription();
        dateTime = event.getDateTime();
        maxParticipants = event.getMaxParticipants();
        isSurstromming = event.isSurstromming();
        eventParticipants = event.getParticipants().stream().map(EventParticipantResponse::new).toList();
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

    public List<EventParticipantResponse> getEventParticipants() {
        return eventParticipants;
    }

    public void setEventParticipants(List<EventParticipantResponse> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }
}

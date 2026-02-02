package erik.soekov.svenska_fest_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EventParticipantId implements Serializable {

    @Column(name = "event_id")
    private Integer eventId;
    @Column(name = "id")
    private String id;

    public EventParticipantId() {
    }

    public EventParticipantId(Integer eventId, String id) {
        this.eventId = eventId;
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package erik.soekov.svenska_fest_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EventParticipantId implements Serializable {

    @Column(name = "event_id")
    private Integer EventId;
    @Column(name = "id")
    private String id;

    public EventParticipantId() {
    }

    public Integer getEventId() {
        return EventId;
    }

    public void setEventId(Integer eventId) {
        EventId = eventId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

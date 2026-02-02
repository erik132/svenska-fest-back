package erik.soekov.svenska_fest_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "event_participants")
public class EventParticipant implements Persistable<EventParticipantId> {

    @EmbeddedId
    private EventParticipantId id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public EventParticipant() {
    }

    //Prevents updates. Only inserts possible.
    @Override
    public boolean isNew() {
        return true;
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

    public EventParticipantId getId() {
        return id;
    }

    public void setId(EventParticipantId id) {
        this.id = id;
    }
}

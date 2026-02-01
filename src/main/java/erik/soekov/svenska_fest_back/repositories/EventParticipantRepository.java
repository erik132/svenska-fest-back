package erik.soekov.svenska_fest_back.repositories;

import erik.soekov.svenska_fest_back.entity.EventParticipant;
import erik.soekov.svenska_fest_back.entity.EventParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, EventParticipantId> {
}

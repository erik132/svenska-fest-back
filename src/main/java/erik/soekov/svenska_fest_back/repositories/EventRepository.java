package erik.soekov.svenska_fest_back.repositories;

import erik.soekov.svenska_fest_back.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}

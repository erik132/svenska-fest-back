package erik.soekov.svenska_fest_back.repositories;

import erik.soekov.svenska_fest_back.entity.SimpleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleEventRepository extends JpaRepository<SimpleEvent, Integer> {
}

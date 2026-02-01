package erik.soekov.svenska_fest_back.service;

import erik.soekov.svenska_fest_back.entity.Event;
import erik.soekov.svenska_fest_back.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /*public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }*/

    public List<Event> getAvailableEvents() {
        return eventRepository.findAll();
    }
}

package erik.soekov.svenska_fest_back.service;

import erik.soekov.svenska_fest_back.dto.CreateEventRequest;
import erik.soekov.svenska_fest_back.dto.EventResponse;
import erik.soekov.svenska_fest_back.dto.SimpleEventResponse;
import erik.soekov.svenska_fest_back.entity.Event;
import erik.soekov.svenska_fest_back.entity.SimpleEvent;
import erik.soekov.svenska_fest_back.repositories.EventRepository;
import erik.soekov.svenska_fest_back.repositories.SimpleEventRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SimpleEventRepository simpleEventRepository;

    /*public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }*/

    public List<SimpleEventResponse> getAvailableEvents() {
        List<SimpleEvent> simpleEvents = simpleEventRepository.findAll();
        return simpleEvents.stream().map(SimpleEventResponse::new).toList();
    }

    public EventResponse getEvent(Integer eventId) throws EventNotFoundException {
        return eventRepository.findById(eventId).map(EventResponse::new).orElseThrow(EventNotFoundException::new);
    }

    public void createEvent(CreateEventRequest request) throws EventCreationException {
        try {
            Event event = new Event();
            event.setName(request.getName());
            event.setAddress(request.getAddress());
            event.setDateTime(request.getDateTime());
            event.setDescription(request.getDescription());
            event.setMaxParticipants(request.getMaxParticipants());
            event.setSurstromming(request.isSurstromming());
            eventRepository.save(event);
        } catch (DataIntegrityViolationException de) {
            if (de.getCause() instanceof ConstraintViolationException cause) {
                String constraintName = cause.getConstraintName();
                if (constraintName != null && constraintName.equals("events_name_key")) {
                    throw new EventCreationException("This name is taken. Please choose another.");
                }
            }
            throw de;
        }
    }
}

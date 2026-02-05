package erik.soekov.svenska_fest_back.service;

import erik.soekov.svenska_fest_back.dto.CreateEventRequest;
import erik.soekov.svenska_fest_back.dto.EventResponse;
import erik.soekov.svenska_fest_back.dto.RegisterEventParticipantRequest;
import erik.soekov.svenska_fest_back.dto.SimpleEventResponse;
import erik.soekov.svenska_fest_back.entity.Event;
import erik.soekov.svenska_fest_back.entity.EventParticipant;
import erik.soekov.svenska_fest_back.entity.EventParticipantId;
import erik.soekov.svenska_fest_back.entity.SimpleEvent;
import erik.soekov.svenska_fest_back.repositories.EventParticipantRepository;
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

    @Autowired
    private EventParticipantRepository eventParticipantRepository;

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

    public void registerEventParticipant(RegisterEventParticipantRequest request) throws ParticipantRegistrationException {
        try {
            SimpleEvent event = simpleEventRepository.findById(request.getEventId()).orElseThrow(() -> new ParticipantRegistrationException("Event not found."));
            if(event.getParticipantsCount() >= event.getMaxParticipants()){
                throw new ParticipantRegistrationException("Can not register. The event is full.");
            }
            EventParticipant participant = new EventParticipant();
            participant.setFirstName(request.getFirstName());
            participant.setLastName(request.getLastName());
            participant.setId(new EventParticipantId(request.getEventId(), request.getEstonianIdCode()));
            eventParticipantRepository.save(participant);
        } catch (DataIntegrityViolationException de) {
            if (de.getCause() instanceof ConstraintViolationException cause) {
                String constraintName = cause.getConstraintName();
                if (constraintName != null && constraintName.equals("event_participants_pkey")) {
                    throw new ParticipantRegistrationException("You can not register to the same event twice.");
                }
            }
            throw de;
        }
    }
}

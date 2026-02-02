package erik.soekov.svenska_fest_back.service;

import erik.soekov.svenska_fest_back.dto.RegisterEventParticipantRequest;
import erik.soekov.svenska_fest_back.entity.EventParticipant;
import erik.soekov.svenska_fest_back.entity.EventParticipantId;
import erik.soekov.svenska_fest_back.repositories.EventParticipantRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    private EventParticipantRepository eventParticipantRepository;

    public void registerEventParticipant(RegisterEventParticipantRequest request) throws ParticipantRegistrationException {
        try {
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

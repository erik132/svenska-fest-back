package erik.soekov.svenska_fest_back.controller;

import erik.soekov.svenska_fest_back.dto.RegisterEventParticipantRequest;
import erik.soekov.svenska_fest_back.dto.RequestVerificationException;
import erik.soekov.svenska_fest_back.service.EventService;
import erik.soekov.svenska_fest_back.service.ParticipantRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participants")
@CrossOrigin(origins = "http//localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
public class ParticipantController {

    @Autowired
    private EventService eventService;

    @PostMapping("/register_participant")
    public ResponseEntity registerParticipant(@RequestBody RegisterEventParticipantRequest request) {
        try {
            request.verifyFields();
            eventService.registerEventParticipant(request);
            return ResponseEntity.ok("Registration successful.");
        } catch (RequestVerificationException | ParticipantRegistrationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.internalServerError().body("Unknown error happened");
        }
    }
}

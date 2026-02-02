package erik.soekov.svenska_fest_back.controller;

import erik.soekov.svenska_fest_back.dto.CreateEventRequest;
import erik.soekov.svenska_fest_back.dto.RequestVerificationException;
import erik.soekov.svenska_fest_back.service.EventCreationException;
import erik.soekov.svenska_fest_back.service.EventNotFoundException;
import erik.soekov.svenska_fest_back.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http//localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/get_event")
    public ResponseEntity getEvent(@RequestParam("event_id") Integer eventId) {
        if (eventId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event ID must be provided.");
        }
        try {
            return ResponseEntity.ok(eventService.getEvent(eventId));
        } catch (EventNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.internalServerError().body("Unknown error happened");
        }
    }

    @GetMapping("/get_events")
    public ResponseEntity getEvents() {
        try {
            return ResponseEntity.ok(eventService.getAvailableEvents());
        } catch (RuntimeException re) {
            return ResponseEntity.internalServerError().body("Unknown error happened");
        }
    }

    @PostMapping("/create_event")
    public ResponseEntity createEvent(@RequestBody CreateEventRequest request) {
        try {
            request.verifyFields();
            eventService.createEvent(request);
            return ResponseEntity.ok("Event created successfully");
        } catch (RequestVerificationException | EventCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.internalServerError().body("Unknown error happened");
        }

    }
}

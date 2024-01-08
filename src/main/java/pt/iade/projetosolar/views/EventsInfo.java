package pt.iade.projetosolar.views;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.EventController;
import pt.iade.projetosolar.controllers.repositories.EventRepository;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.exportInfo.EventInfo;
import pt.iade.projetosolar.models.exportInfo.RSVPInfo;
import pt.iade.projetosolar.models.importInfo.Id;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path="/events")
@RestController
public class EventsInfo {

    private final UserRepository userRepository;
    private final EventRepository eventsRepository;

    public EventsInfo(UserRepository userRepository, EventRepository eventsRepository) {
        this.userRepository = userRepository;
        this.eventsRepository = eventsRepository;
    }

    @PostMapping(path = "/getUserEvents", produces= MediaType.APPLICATION_JSON_VALUE)
    public EventInfo[] getUserRSVP(@RequestBody Id id){
        User user = userRepository.findById(id.id()).get();
        List<Event> events = user.getEvents();
        List<EventInfo> eventsInfo = EventInfo.getEventsInfo(events);
        return eventsInfo.toArray(new EventInfo[0]);
    }

    @PostMapping(path = "/getAvailableEvents", produces= MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<EventInfo> getAvailableEvents(@RequestBody Id id){
        EventController eventController = new EventController(eventsRepository, userRepository);
        User user = userRepository.findById(id.id()).get();
        ArrayList<Event> events = eventController.getUserAvailableEvents(user);
        ArrayList<EventInfo> eventsInfo = new ArrayList<>();
        for(Event event : events)
            eventsInfo.add(new EventInfo(event));

        return eventsInfo;
    }

    @PostMapping(path = "/attendEvent/{eventId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public RSVPInfo attendEvent(@PathVariable int eventId, @RequestBody Id userId){
        EventController eventController = new EventController(eventsRepository, userRepository);
        RSVPInfo rsvpInfo = eventController.attendEvent(userId.id(), eventId);
        return rsvpInfo;
    }

    @PostMapping(path = "/cancelEvent", produces= MediaType.APPLICATION_JSON_VALUE)
    public RSVPInfo cancelEvent(@RequestBody int userId, int eventId){
        EventController eventController = new EventController(eventsRepository, userRepository);
        User user = userRepository.findById(userId).get();
        Event event = eventController.removeRSVP(user, eventId);
        if(event != null)
            return new RSVPInfo("success", true, eventId);
        else
            return new RSVPInfo("error", false, eventId);
    }

    @PostMapping(path = "/setNotifyUser/{eventId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public void setNotifyUser(@PathVariable int eventId, @RequestBody Id userId){
        EventController eventController = new EventController(eventsRepository, userRepository);
        User user = userRepository.findById(userId.id()).get();
        Event event = eventController.setNotifyUser(user, eventId);
    }
}

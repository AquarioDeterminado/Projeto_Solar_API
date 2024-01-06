package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.EventController;
import pt.iade.projetosolar.controllers.repositories.EventRepository;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.users.User;

@RestController
@RequestMapping(path="/Solar/events")
public class EventsInfo {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventsRepository;

    @PostMapping(path = "/getRSVP", produces= MediaType.APPLICATION_JSON_VALUE)
    public Event[] getUserRSVP(@RequestBody int userId){
        User user = userRepository.findById(userId).get();
        return user.getEvents().toArray(new Event[0]);
    }

    @PostMapping(path = "/getAvailableEvents", produces= MediaType.APPLICATION_JSON_VALUE)
    public Event[] getAvailableEvents(@RequestBody int userId){
        EventController eventController = new EventController(eventsRepository);
        User user = userRepository.findById(userId).get();
        return eventController.getUserAvailableEvents(user);
    }

    @PostMapping(path = "/attendEvent", produces= MediaType.APPLICATION_JSON_VALUE)
    public Event attendEvent(@RequestBody int userId, int eventId){
        EventController eventController = new EventController(eventsRepository);
        User user = userRepository.findById(userId).get();
        return eventController.attendEvent(user, eventId);
    }

    @PostMapping(path = "/cancelEvent", produces= MediaType.APPLICATION_JSON_VALUE)
    public Event cancelEvent(@RequestBody int userId, int eventId){
        EventController eventController = new EventController(eventsRepository);
        User user = userRepository.findById(userId).get();
        return eventController.removeRSVP(user, eventId);
    }

    @PostMapping(path = "/setNotifyUser", produces= MediaType.APPLICATION_JSON_VALUE)
    public Event setNotifyUser(@RequestBody int userId, int eventId){
        EventController eventController = new EventController(eventsRepository);
        User user = userRepository.findById(userId).get();
        return eventController.setNotifyUser(user, eventId);
    }
}

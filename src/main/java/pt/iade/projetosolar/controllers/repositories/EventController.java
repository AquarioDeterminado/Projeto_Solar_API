package pt.iade.projetosolar.controllers.repositories;

import pt.iade.projetosolar.models.dao.CoWork;
import pt.iade.projetosolar.models.dao.User;

import java.util.ArrayList;

public class EventController {

    private final UserRepository userRepository;

    public EventController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static Event attendEvent (int usrId, int eventId) {
        User user = userRepository.findById(usrId).get();
        Event event = eventRepository.findById(eventId).get();
        user.addEvent(event);
        return event;
    }

    public static Event cancelEvent (int userId, int eventId) {
        User user = userRepository.findById(userId).get();
        Event event = eventRepository.findById(eventId).get();
        user.removeEvent(event);
        return event;
    }

    public static Event setNotifyUser(int userId, int eventId
        User user = userRepository.findById(usrId).get();
        Event event = eventRepository.findById(eventId).get();
        user.notifyAboutEvent(event);
        return event;
    }

    public Event[] getUserRSVPEvents(int userId) {
        return userRepository.findById(userId).get().getEvents();
    }

    public Event[] getUserAvailableEvents(int userID) {
        ArrayList<Event> availableEvents = new ArrayList<>();

        ArrayList<Event> subEvents = getSubbedCoworkEvents(userID);
        ArrayList<Event> publicEvents = getPublicEvents();

        availableEvents.addAll(publicEvents);
        availableEvents.addAll(subEvents);

        return availableEvents.toArray(new Event[availableEvents.size()]);
    }

    private ArrayList<Event> getSubbedCoworkEvents(int userID) {
        User user = userRepository.findById(userID).get();
        ArrayList<Subscription> subscriptions = user.getSubscriptions();

        CoWork coWork = null;
        ArrayList<Event> subEvents = null;
        for(Subscription subscription : subscriptions) {
            coWork = subscription.getCoWork();
            subEvents.addAll(coWork.getEvents());
        }
        return subEvents;
    }

    private static ArrayList<Event> getPublicEvents() {
        ArrayList<Event> publicEvents = new ArrayList<>();
        publicEvents = eventRepository.findAllWherePublicIsTrue();
        return publicEvents;
    }

}

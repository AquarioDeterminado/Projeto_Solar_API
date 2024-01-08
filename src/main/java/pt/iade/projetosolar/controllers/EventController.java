package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.controllers.repositories.EventRepository;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.exportInfo.RSVPInfo;

import java.util.ArrayList;
import java.util.List;

public class EventController {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public RSVPInfo attendEvent (int userId, int eventId) {
        RSVPInfo rsvp = new RSVPInfo(eventId);
        Event event = eventRepository.findById(eventId).get();
        User user = userRepository.findById(userId).get();

        if(event.isFull()) {
            rsvp.setError("Event is full");
        } else {
            rsvp.isSuccess();
            user.addEvent(event, true, true);
            userRepository.save(user);
        }

        return rsvp;
    }

    public Event removeRSVP(User user, int eventId) {
        Event event = eventRepository.findById(eventId).get();
        user.removeEvent(event);
        return event;
    }

    public Event setNotifyUser(User user, int eventId){
        Event event = eventRepository.findById(eventId).get();
        user.addEvent(event, false, true);
        return event;
    }

    public ArrayList<Event> getUserAvailableEvents(User user) {
        ArrayList<Event> availableEvents = new ArrayList<>();

        ArrayList<Event> subEvents = getSubbedCoworkEvents(user);
        ArrayList<Event> publicEvents = getPublicEvents();

        availableEvents.addAll(subEvents);
        for (Event event : publicEvents) {
            if (!availableEvents.contains(event)) {
                availableEvents.add(event);
            }
        }

        //availableEvents.removeAll(user.getEvents()); //App needs place holder for no events
        return availableEvents;
    }

    private ArrayList<Event> getSubbedCoworkEvents(User user) {
        List<SubscriptionRecord> subscriptions = user.getSubscriptions();

        CoWork coWork = null;
        ArrayList<Event> subEvents = new ArrayList<>();
        for(SubscriptionRecord subscription : subscriptions) {
            coWork = subscription.getCoWork();
            subEvents.addAll(coWork.getEvents());
        }
        return subEvents;
    }

    private ArrayList<Event> getPublicEvents() {
        ArrayList<Event> publicEvents = new ArrayList<>();
        publicEvents = eventRepository.findByIsPublicIs(true);
        return publicEvents;
    }

}

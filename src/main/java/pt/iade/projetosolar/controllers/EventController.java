package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.controllers.repositories.EventRepository;
import pt.iade.projetosolar.models.dao.CoWork;
import pt.iade.projetosolar.models.dao.Event;
import pt.iade.projetosolar.models.dao.Subscription;
import pt.iade.projetosolar.models.dao.User;

import java.util.ArrayList;
import java.util.List;

public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event attendEvent (User user, int eventId) {
        Event event = eventRepository.findById(eventId).get();
        user.addEvent(event, true, true);
        return event;
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

    public Event[] getUserAvailableEvents(User user) {
        ArrayList<Event> availableEvents = new ArrayList<>();

        ArrayList<Event> subEvents = getSubbedCoworkEvents(user);
        ArrayList<Event> publicEvents = getPublicEvents();

        availableEvents.addAll(publicEvents);
        availableEvents.addAll(subEvents);

        return availableEvents.toArray(new Event[0]);
    }

    private ArrayList<Event> getSubbedCoworkEvents(User user) {
        List<Subscription> subscriptions = user.client().getSubscriptions();

        CoWork coWork = null;
        ArrayList<Event> subEvents = null;
        for(Subscription subscription : subscriptions) {
            coWork = subscription.getCoWork();
            subEvents.addAll(coWork.getEvents());
        }
        return subEvents;
    }

    private ArrayList<Event> getPublicEvents() {
        ArrayList<Event> publicEvents = new ArrayList<>();
        publicEvents = eventRepository.findAllWherePublicIsTrue();
        return publicEvents;
    }

}

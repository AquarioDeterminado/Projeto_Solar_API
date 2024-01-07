package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.controllers.repositories.EventRepository;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;
import pt.iade.projetosolar.models.dao.users.User;

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

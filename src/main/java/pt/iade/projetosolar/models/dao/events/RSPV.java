package pt.iade.projetosolar.models.dao.events;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.users.User;

@Entity
@Table(name = "rsvp")
public class RSPV {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "rsvp_id") private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rsvp_ind_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rsvp_evt_id")
    Event event;

    @Column(name = "rsvp_is_goin") private boolean userIsGoing;

    @Column(name = "rsvp_wants_notification") private boolean userWantsNotification;

    @Column(name = "rsvp_was_notified") private boolean userWasNotified;

    public RSPV(User user, Event event, boolean userIsGoing, boolean userWantsNotification) {
        this.user = user;
        this.event = event;
        this.userIsGoing = userIsGoing;
        this.userWantsNotification = userWantsNotification;
        this.userWasNotified = false;
    }

    public RSPV() {
        this.userWasNotified = false;
    }

    public Event getEvent() { return event;}

    public boolean isUserIsGoing() {return userIsGoing;}

    public boolean isUserWantsNotification() {return userWantsNotification;}

    public boolean isUserWasNotified() {return userWasNotified;}

    public void setUserWasNotified(boolean wasNotified) {this.userWasNotified = wasNotified;}

}

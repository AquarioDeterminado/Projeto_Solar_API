package pt.iade.projetosolar.models.dao.users;

import jakarta.persistence.*;
import pt.iade.projetosolar.controllers.LogInController;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.events.RSPV;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;
import pt.iade.projetosolar.models.dao.subscriptions.Susbcribed;
import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "individual")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ind_id") private int id;
    @Column(name = "ind_email") private String email;
    @Column(name = "ind_password") private String password;
    @Column(name = "ind_name") private String userName;
    @Column(name = "ind_bDate") private Date birthDate;
    @Column(name = "ind_account_creation_date") private Date creationDate;
    @Column(name = "ind_active") private boolean isActive;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RSPV> rsvp = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ind_ent_id")
    private EntityDBO entityDBO;

    public User() {
        this.creationDate = new Date(System.currentTimeMillis());
        this.isActive = true;
    }

    public User(String userName, String email, String password, String phone, Date bDate) {
        this.creationDate = new Date(System.currentTimeMillis());
        this.isActive = true;
        this.userName = userName;
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) { this.userName = name; }

    public void setPassword(String password) {
        if(LogInController.verifyPassword(password))
            this.password = password;
    }

    public List<RSPV> getRSVPs() { return rsvp; }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for (RSPV r : rsvp)
            events.add(r.getEvent());
        return events;
    }

    public void addEvent(Event event, boolean userIsGoing, boolean userWantsNotification) {
        for(RSPV r : rsvp)
            if(r.getEvent().equals(event))
                return;
        RSPV rsvp = new RSPV(this, event, userIsGoing, userWantsNotification);
        this.rsvp.add(rsvp);
    }


    public void removeEvent(Event event) {
        for (RSPV r : rsvp) {
            if (r.getEvent().equals(event)) {
                rsvp.remove(r);
                break;
            }
        }
    }

    public List<SubscriptionRecord> getOwnedSubscriptions() {
        List<SubscriptionRecord> subscriptions = new ArrayList<>();
        List<Susbcribed> subscribedGroups = entityDBO.getSubscribedGroups();
        for (Susbcribed s : subscribedGroups)
            if (s.isActive())
                subscriptions.add(s.getSubscriptionRecord());
        return subscriptions;
    }

    public List<SubscriptionRecord> getSubscriptions() {
        List<SubscriptionRecord> subscriptions = new ArrayList<>();
        List<Susbcribed> subscribedGroups = this.entityDBO.getSubscribedGroups();
        for (Susbcribed s : subscribedGroups)
            if (s.isActive())
                if (s.getSubscriptionRecord().isActive())
                    subscriptions.add(s.getSubscriptionRecord());
        return subscriptions;
    }

    public ArrayList<WorkStationsSpace> getAcessibleSpaces() {
        ArrayList<WorkStationsSpace> spaces = new ArrayList<>();

        List<SubscriptionRecord> subscriptions = getSubscriptions();

        List<WorkStationsSpace> coworkSpaces = new ArrayList<>();
        for (SubscriptionRecord s : subscriptions) {
            coworkSpaces = s.getCoWork().getCoworkSpaces();
        }
        if (!coworkSpaces.isEmpty())
            spaces.addAll(coworkSpaces);

        return spaces;
    }
}

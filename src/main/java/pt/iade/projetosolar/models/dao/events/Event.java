package pt.iade.projetosolar.models.dao.events;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.utils.Facility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "evt_id") private int id;
    @Column(name = "evt_name") private String name;
    @Column(name = "evt_description") private String description;
    @Column(name = "evt_location") private String location;
    @Column(name = "evt_start") private Date start;
    @Column(name = "evt_finish") private Date end;
    @Column(name = "evt_max_capacity") private int maxParticipants;
    @Column(name = "evt_min_capacity") private int minParticipants;
    @Column(name = "evt_was_canceled") private boolean wasCanceled;
    @Column(name = "evt_create") private Date creationDate;
    @Column(name = "evt_is_public") private boolean isPublic;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RSPV> rsvps;

    @ManyToOne
    @JoinColumn(name = "evt_space_id")
    private CoWork coWork;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventsFacilities> facilities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evt_ety_id", referencedColumnName = "ety_id")
    private EventType type;

    public Event() {
        this.creationDate = new Date(System.currentTimeMillis());
        this.wasCanceled = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public EventType getType() {
        return type;
    }

    public boolean isFull() {
        int rsvpsCount = rsvps.size();
        return rsvpsCount >= maxParticipants;
    }

    public boolean reachedMinGoal() {
        int rsvpsCount = rsvps.size();
        return rsvpsCount >= minParticipants;
    }

    public List<EventsFacilities> getFacilities() {return facilities;}
}

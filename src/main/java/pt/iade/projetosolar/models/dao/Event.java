package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

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
    @Column(name = "evt_start") private String start;
    @Column(name = "evt_finish") private String end;
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

    public Event() {
        this.creationDate = new Date(System.currentTimeMillis());
        this.wasCanceled = false;
    }


}

package pt.iade.projetosolar.models.dao.events;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_type")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ety_id")
    private int id;

    @Column(name = "ety_type")
    private String type;

    @OneToMany(mappedBy = "event_type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evt_ety_id", referencedColumnName = "ety_id")
    private List<Event> event = new ArrayList<>();

    public String getType() {return type;}
}

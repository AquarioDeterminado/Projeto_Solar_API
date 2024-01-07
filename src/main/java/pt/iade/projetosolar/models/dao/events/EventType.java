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

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> event = new ArrayList<>();

    public String getName() {return type;}
}

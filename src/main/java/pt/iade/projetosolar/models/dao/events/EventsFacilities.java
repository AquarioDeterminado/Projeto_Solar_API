package pt.iade.projetosolar.models.dao.events;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.utils.Facility;

@Entity
@Table(name = "events_facilities")
public class EventsFacilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evf_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "evf_evt_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "evf_fac_id")
    private Facility facility;

}

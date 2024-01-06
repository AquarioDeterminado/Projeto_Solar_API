package pt.iade.projetosolar.models.dao.utils;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.subscriptions.Subscription;

import java.util.ArrayList;

@Entity
@Table(name = "facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fac_id")
    private int id;

    @Column(name = "fac_name")
    private String name;

    @ManyToMany(mappedBy = "facilities")
    private ArrayList<CoWork> coWorks;

    @ManyToMany(mappedBy = "facilities")
    private ArrayList<Event> events;

    @ManyToMany(mappedBy = "facilities")
    private ArrayList<Subscription> subscriptions;

    public String getName() {return name;}
}

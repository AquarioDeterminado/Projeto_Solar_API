package pt.iade.projetosolar.models.dao.utils;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.coworks.CoWorksFacilities;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.events.EventsFacilities;
import pt.iade.projetosolar.models.dao.subscriptions.Subscription;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionFacilities;

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

    @OneToMany(mappedBy = "facility")
    private ArrayList<CoWorksFacilities> coWorksFacilities = new ArrayList<>();

    @OneToMany(mappedBy = "facility")
    private ArrayList<EventsFacilities> eventsFacilities = new ArrayList<>();

    @OneToMany(mappedBy = "facility")
    private ArrayList<SubscriptionFacilities> subscriptions = new ArrayList<>();

    public String getName() {return name;}
}

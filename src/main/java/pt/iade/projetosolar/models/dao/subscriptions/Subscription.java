package pt.iade.projetosolar.models.dao.subscriptions;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id") private int id;

    @Column(name = "sub_name") private String name;

    @Column(name = "sub_price") private double price;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_spc_id")
    private CoWork coWork;

    @OneToMany(mappedBy = "subscription")
    private List<SubscriptionFacilities> facilities = new ArrayList<>();

    public int getId() {return id;}

    public String getName() {return name;}

    public double getPrice() {return price;}

    public CoWork getCoWork() {return coWork;}

    public List<SubscriptionFacilities> getFacilities() {return facilities;}
}

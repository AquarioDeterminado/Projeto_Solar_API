package pt.iade.projetosolar.models.dao.subscriptions;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.utils.Facility;

import java.util.ArrayList;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id") private int id;

    @Column(name = "sub_name") private String name;

    @Column(name = "sub_price") private String price;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_spc_id")
    private CoWork coWork;

    @ManyToMany
    @JoinTable(name = "subscriptions_facilities",
        joinColumns = @JoinColumn(name = "suf_sub_id"),
        inverseJoinColumns = @JoinColumn(name = "suf_fac_id"))
    private ArrayList<Facility> facilities;

    public int getId() {return id;}

    public String getName() {return name;}

    public String getPrice() {return price;}

    public CoWork getCoWork() {return coWork;}

    public ArrayList<Facility> getFacilities() {return facilities;}
}

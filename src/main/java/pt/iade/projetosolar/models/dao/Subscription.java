package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

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

    public int getId() {return id;}

    public String getName() {return name;}

    public String getPrice() {return price;}

    public CoWork getCoWork() {return coWork;}
}

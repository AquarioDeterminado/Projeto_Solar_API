package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "cli_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cli_ent_id", referencedColumnName = "ent_id")
    private EntityDBO entityDBO;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubscriptionRecord> subscriptions = new ArrayList<>();

    public List<SubscriptionRecord> getSubscriptions() {return subscriptions;}
}

package pt.iade.projetosolar.models.dao.subscriptions;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.users.EntityDBO;

@Entity
@Table(name = "subscribed")
public class Susbcribed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sud_id")
    private int id;

    @Column(name = "sud_is_active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sud_sus_id")
    private SubscriptionRecord subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sud_ent_id")
    private EntityDBO entity;




}
